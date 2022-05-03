package com.group.exam.member.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.group.exam.member.command.InsertCommand;
import com.group.exam.member.command.LoginCommand;
import com.group.exam.member.service.MemberService;

@Controller
public class MemberKakaoController {
	private MemberService memberService;
	@Autowired
	public MemberKakaoController(MemberService memberService) {
		this.memberService= memberService;
	}
	
	private final static String K_CLIENT_ID = "1443aea9ec4b8f7043928e43ec2810e7";
	private final static String K_REDIRECT_URI = "http://localhost:8080/exam/login/kakaoLogin";

	// 카카오 로그인 클릭 할 때 요청
//	@RequestMapping(value = "/member/memberloginform", method = RequestMethod.GET)
//	public ModelAndView memberLoginForm(HttpSession session) {
//		ModelAndView mav = new ModelAndView();
//		/* 카카오로 인증 URL을 생성하기 위하여 getAuthorizationUrl을 호출 */
//		String kakaoUrl = getAuthorizationUrl(session);
//
//		/* 생성한 인증 URL을 View로 전달 */
//		mav.setViewName("/member/kakaoLogin");
//		// 카카오 로그인
//		mav.addObject("kakao_url", kakaoUrl);
//
//		return mav;
//	}// end memberLoginForm()

	@RequestMapping(value = "/login/kakaoLogin", produces = "application/json", method = { RequestMethod.GET,
			RequestMethod.POST })
	public String kakaoLogin(@RequestParam("code") String code, HttpServletRequest request,
			HttpServletResponse response, HttpSession session) throws Exception {
		// 결과값을 node에 담아줌
		JsonNode node = getAccessToken(code);
		// accessToken에 사용자의 로그인한 모든 정보가 들어있음
		JsonNode accessToken = node.get("access_token");
		// 사용자의 정보
		JsonNode userInfo = getKakaoUserInfo(accessToken);
		String kemail = null;
		String kbirthday = null;
		// 유저정보 카카오에서 가져오기 Get properties
		JsonNode properties = userInfo.path("properties");
		JsonNode kakao_account = userInfo.path("kakao_account");

		kemail = kakao_account.path("email").asText();
		kbirthday = kakao_account.path("birthday").asText();
		//Command에 kakaoId 넣은 후 중복 체크 한 후 중복 없으면 임의로 닉네임 생성
		
		LoginCommand member = memberService.login(kemail);
		if(memberService.idDup(kemail) == 0) {
			InsertCommand insertCommand = new InsertCommand();
			insertCommand.setMemberBirthDay(kbirthday);
			insertCommand.setMemberId(kemail);
			
			//닉네임 랜덤 설정
			Random random = new Random();
			StringBuffer buffer = new StringBuffer();
			
			int num = 0;
			while(buffer.length() < 6) {
				num = random.nextInt(10);
				buffer.append(num);
			}
			String memberNickname = "USER_"+buffer.toString();
			
			if(memberService.nicknameDup(memberNickname) == 0) {
				insertCommand.setMemberNickname(memberNickname);
			}else if(memberService.nicknameDup(memberNickname) != 0) {
				memberNickname ="USER_"+buffer.toString()+"_K";
			}
			
			insertCommand.setMemberPassword("kakaoLogin_tempPw");
			System.out.println("카카오 로그인 : "+insertCommand);
			memberService.memberInsert(insertCommand);
			
			member = memberService.login(kemail);
			System.out.println(member);
			memberService.updateApiStatus("kakao", member.getMemberSeq());
			
		}
		
		session.setAttribute("memberLogin", member);

		return "/main";
	}// end kakaoLogin()

	public String getAuthorizationUrl(HttpSession session) {
		String kakaoUrl = "https://kauth.kakao.com/oauth/authorize?" + "client_id=" + K_CLIENT_ID + "&redirect_uri="
				+ K_REDIRECT_URI + "&response_type=code";
		return kakaoUrl;
	}

	public static JsonNode getAccessToken(String autorize_code) {
		final String RequestUrl = "https://kauth.kakao.com/oauth/token";
		final List<NameValuePair> postParams = new ArrayList<NameValuePair>();
		postParams.add(new BasicNameValuePair("grant_type", "authorization_code"));
		postParams.add(new BasicNameValuePair("client_id", "1443aea9ec4b8f7043928e43ec2810e7")); // REST API KEY
		postParams.add(new BasicNameValuePair("redirect_uri", "http://localhost:8080/exam/login/kakaoLogin")); // 리다이렉트																										// URI
		postParams.add(new BasicNameValuePair("code", autorize_code)); // 로그인 과정중 얻은 code 값
		
		final HttpClient client = HttpClientBuilder.create().build();
		final HttpPost post = new HttpPost(RequestUrl);

		JsonNode returnNode = null;
		try {
			post.setEntity(new UrlEncodedFormEntity(postParams));
			final HttpResponse response = client.execute(post);
			final int responseCode = response.getStatusLine().getStatusCode();
			
			System.out.println("\nSending 'POST' request to URL : "+ RequestUrl);
			System.out.println("Post parameters : "+ postParams);
			System.out.println("Response Code : "+responseCode);
			
			// JSON 형태 반환값 처리
			ObjectMapper mapper = new ObjectMapper();
			returnNode = mapper.readTree(response.getEntity().getContent());
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			// clear resources
		}
		return returnNode;
	}

	public static JsonNode getKakaoUserInfo(JsonNode accessToken) {
		final String RequestUrl = "https://kapi.kakao.com/v2/user/me";
		final HttpClient client = HttpClientBuilder.create().build();
		final HttpPost post = new HttpPost(RequestUrl);
		// add header
		post.addHeader("Authorization", "Bearer " + accessToken);
		JsonNode returnNode = null;
		try {
			final HttpResponse response = client.execute(post);
			final int responseCode = response.getStatusLine().getStatusCode();

			System.out.println("\nSending 'POST' request to URL : " + RequestUrl);
			System.out.println("Response Code : " + responseCode);

			ObjectMapper mapper = new ObjectMapper();
			returnNode = mapper.readTree(response.getEntity().getContent());
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			// clear resources
		}
		return returnNode;
	}

}
