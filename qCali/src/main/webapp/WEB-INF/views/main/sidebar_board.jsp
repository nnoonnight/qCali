<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<script
	src="https://funyphp.com/js/jquery-1.8.3.min.js.pagespeed.jm.0IhQ85x_cu.js"></script>
<%--사이드바 구현 --%>
<link href="<c:url value='/resources/static/css/sidebar_board.css'/> "
	rel="stylesheet" type="text/css">
<script src="<c:url value='/resources/static/js/sidebar_board.js'/>"></script>
<link
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.8.2/css/all.min.css"
	rel="stylesheet" type="text/css">
<script src="https://code.jquery.com/jquery-2.2.3.min.js"></script>



<meta charset="UTF-8">
<title>Insert title here</title>
</head>

<div id="wrapper">
	<div class="topbar" style="position: absolute; top: 0;">
		<!-- 왼쪽 메뉴 -->
		<div class="left side-menu">
			<div class="sidebar-inner">
				<div id="sidebar-menu">
					<ul>
						<li class="has_sub"><a href="javascript:void(0);"
							class="waves-effect"> <i class="fas fa-bars"></i>
						</a></li>
					</ul>
				</div>
			</div>
		</div>
		<!-- 왼쪽 서브 메뉴 -->
		<div class="left_sub_menu">
			<div class="sub_menu">
				<!-- <input type="search" name="SEARCH" placeholder="SEARCH"> -->
				<h2>QCali</h2>

				<c:if test="${empty memberLogin}">
					<ul class="big_menu">
						<li><a href="${pageContext.request.contextPath }/member/login">Login&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</a></li>
						<li><a href="${pageContext.request.contextPath }/member/insert">회원가입</a></li>
					</ul>
				</c:if>

				<c:if test="${!empty memberLogin}">
				
					<ul class="big_menu">
					<li>회원정보<i class="arrow fas fa-angle-right"></i></li>
						<ul class="small_menu">
							<li>회원 아이디 : ${memberLogin.memberId}</li>
							<li>회원 닉네임 : ${memberLogin.memberNickname}</li>
							<li>회원 레벨 : ${memberLogin.memberLevel}</li>

						</ul>
					</ul>
				

					<ul class="big_menu">
						<li>Menu&nbsp;&nbsp;<i class="arrow fas fa-angle-right"></i></li>
						<ul class="small_menu">
							<li><a
								href="${pageContext.request.contextPath }/board/memberArticle?memberSeq=${memberLogin.memberSeq}">내가
									쓴 글 모아보기</a></li>
							<li><a
								href="${pageContext.request.contextPath }/member/questionAdd">일문일답 질문 등록하기</a></li>
							<li><a
								href="${pageContext.request.contextPath }/diary/list/${memberLogin.memberSeq}">Diary</a></li>


						</ul>
					</ul>
					<ul class="big_menu">
						<li><a
							href="${pageContext.request.contextPath }/member/mypage/confirmPwd?memberSeq=${memberLogin.memberSeq}">MY
								PAGE&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</a></li>

					</ul>



				</c:if>




			</div>
		</div>

	</div>

	</body>
</html>