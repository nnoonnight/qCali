<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<link href="<c:url value='/resources/static/css/dropdown.css'/> " rel="stylesheet" type="text/css">

<meta charset="UTF-8" name="viewport" content="width=device-width, initial-scale=1">
<title>Insert title here</title>
	<style>
		#reply{
			display: block;
			width:400px;
			border-bottom : 1px solid black;
		}
		#reply_content{
			border : 1px solid black;
		}
	</style>
</head>	
<body>
<jsp:include page="/WEB-INF/views/main/header.jsp"></jsp:include>
<jsp:include page="/WEB-INF/views/main/sidebar_board.jsp"></jsp:include>
<div class="container">
	<table border="1">
		<tr>
			<th>NO</th>
			<th>TITLE</th>
			<th>작성자</th>
			<th>내용</th>
			<th>작성날짜</th>
			<th>좋아요</th>
			<th>카운트</th>


		</tr>
		<c:if test="${ empty boardList}">
			<tr>
				<td colspan="7">게시판에 저장된 글이 없습니다.</td>
			</tr>
		</c:if>

		<c:if test="${ !empty boardList}">

			<tr>
				<td>${boardList.rn}</td>

				<td>${boardList.boardTitle}</td>
				<td>${boardList.boardContent}</td>
				
			
				
				
					<td>
					<c:if test="${empty boardList.memberNickname }">
						탈퇴 회원
					</c:if>
					<c:if test="${!empty boardList.memberNickname }">
						<div class="dropdown">
							<a href="#" class="dropbtn">${ boardList.memberNickname}</a>
							<div class="dropdown-content">
								<a href="<c:url value='/board/memberArticle?memberSeq=${boardList.memberSeq }'/> ">게시물 보기</a>
								<a href=# onclick="popUpInfo();">회원 정보 보기</a>
									<script type="text/javascript">
										function popUpInfo(){
										let url = "${pageContext.request.contextPath}/member/popup?memberSeq=${boardList.memberSeq}";
										let name = "Member 정보";
										let specs = "height=300, width= 250, status = no, location= no, top=100, left=100";
										window.open(url, name, specs);}
									</script>
								
							</div>
						</div>
					</c:if></td>
					
				<td>${boardList.boardRegday}</td>
				<td>${boardList.boardLike}</td>
				<td>${boardList.boardCount}</td>
			</tr>

			<div style="text-align: right;">
				<a class="text-dark heart" style="text-decoration-line: none;">

					<img id="heart" src="" height="30px">
				</a>
			</div>
				<script type="text/javascript">
					function popUpInfo(){
						let url = "${pageContext.request.contextPath}/member/popup?memberSeq=${boardlist.memberSeq}";
						let name = "Member 정보";
						let specs = "height=300, width= 250, status = no, location= no, top=100, left=100";
						window.open(url, name, specs);}
				</script>



			<c:if test="${myArticle == true}">

				<a href="<c:url value='/board/edit?boardSeq=${boardList.boardSeq}'/>"><button>글
						수정</button></a>


				<a href="<c:url value='/board/delete?boardSeq=${boardList.boardSeq}'/>"><button
						onclick="button_event();">글 삭제</button></a>


			</c:if>




		</c:if>


	</table>
	
		<!-- 댓글 입력 폼 -->
   <br>
	
	
	<br/>
	<hr/>
	<h5>댓글 : [ ${replyTotal} ] 개</h5>&nbsp;&nbsp;
	<div id="replyList"></div>
	
	<div class="col-md-6">
		<label for="memberNickname" id="memberNickname">작성자 : ${memberLogin.memberNickname}</label><br/>
		<label for="replyContent"> 댓글 : </label>
		<textarea class="form-control" id="replyContent" name="replyContent"></textarea>
		<button type="button" class="btn btn-outline-success" id="replywriteBtn" name="replywriteBtn">댓글 작성</button>
	</div>

	
	
	
	
	
	
	
	
	<%--댓글 / 좋아요 관련 자바스크립트 --%>
	<script src="https://code.jquery.com/jquery-3.6.0.js"></script>
	<script>
		$(document).ready(function() {
			var heartval = ${boardHeart};
			
			if (heartval > 0) {
				console.log(heartval);
			    $("#heart").prop("src", '<c:url value="/resources"/>'+"/static/images/like2.png");
				$(".heart").prop('name', heartval)
			} else {
				console.log(heartval);
				$("#heart").prop("src", '<c:url value="/resources"/>'+"/static/images/like1.png");
				$(".heart").prop('name', heartval)
			}
			$(".heart").on("click", function() {
				var that = $(".heart");
				console.log(that.prop('name'));
				var sendData = {
					'boardSeq' : '${boardSeq}',
					'heart' : that.prop('name'),
				};
				$.ajax({
					url : '<c:url value="/board/heart"/>',
					type : 'POST',
					data : JSON.stringify(sendData),
					contentType: 'application/json',
					success : function(data) {
						that.prop('name', data);
						console.log("success:" + that.prop('name', data));
				
						if (data == 1) {
							 $('#heart').prop("src",'<c:url value="/resources"/>'+"/static/images/like2.png");
						} else {
							 $('#heart').prop("src",'<c:url value="/resources"/>'+"/static/images/like1.png");
						}
					}
				});
			});
		});
	
		//댓글 리스트 호출 함수
		$(document).ready(function() {
			getreplylist();
		});
		
		
		//댓글 리스트 함수
		function getreplylist() {
			var replyurl = "${root}reply/";
			var boardSeq = ${boardList.boardSeq};
			var memberSeq = ${memberLogin.memberSeq};
			
			$.ajax({
				url : replyurl+boardSeq,
				type : 'POST',
				dataType : 'json',
				
				success: function(result){
					console.log(result);
					var htmls = "";
					
					if(result.length < 1) {
						htmls = "등록된 댓글이 없습니다.";
						
					} else {
						$(result).each(function() {
							htmls += '<div id="reply">'
							htmls += '<div id="replySeq'+this.replySeq+'">';
							htmls += '<br>작성자 : ' + '<div class = "dropdown"> '
							htmls += '<a href="#" class="dropbtn">'+ this.memberNickname;
							htmls += '</a> <div class="dropdown-content">'
							htmls += '<a href="${pageContext.request.contextPath}/board/memberArticle?memberSeq='+this.memberSeq +'">게시물 보기</a>'
							htmls += '<a href="#" onclick ="popUpInfo();">회원 정보 보기</a>'
							htmls += '</div></div>'
							htmls += '&nbsp;&nbsp;&nbsp;&nbsp;';
							htmls += '작성 날짜 : ' + this.replyRegDay;
							htmls += '<br/><p>';
							htmls += '댓글 내용 : &nbsp;&nbsp; <div id="reply_content">' + this.replyContent;
							htmls += '</div></p>';
							if(memberSeq ==  this.memberSeq){
							htmls += '<button type="button" class="btn btn-outline-success" onclick="updateviewBtn(\'' + this.replySeq + '\', \'' + this.replyContent + '\', \''+ this.memberNickname + '\')">수정</button>&nbsp;&nbsp;';
							htmls += '<button type="button" class="btn btn-outline-success" onclick="replyDeleteConfirm(\'' + this.replySeq + '\')">삭제</button>';
							}
							htmls += '</div><br/>';
							htmls += '</div>'
						});
					};
						$("#replyList").html(htmls);
				}
			});
		}
		
		//댓글 저장 함수
		$(document).on('click', '#replywriteBtn', function() {
			var replyContent = $('#replyContent').val();	
			var paramData = JSON.stringify({
				'replyContent': replyContent, 'boardSeq':'${boardList.boardSeq}', 'memberSeq':'${memberLogin.memberSeq}'});
			var headers = {"Content-Type" : "application/json", "X-HTTP-Method-Override" : "POST"};
				
			$.ajax({
				url: '<c:url value="/board/replyInsert"/>',
				headers : headers,
				data : paramData,
				type : 'POST',
				contentType : 'application/json',
				
				success: function(result){
					getreplylist();
					console.log("댓글이 입력됐습니다.");
				
				}, error: function(error) {
					console.log("에러 : " + JSON.stringify(error));
				}
			});
		});
		
		
		//댓글 수정 폼 불러오기 함수
		function updateviewBtn(replySeq, replyContent, memberNickname) {
			var htmls = "";
				
			htmls += '<div id="replySeq'+replySeq+'">';
			htmls += '<strong>';
			htmls += '작성자 : '+memberNickname;
			htmls += '</strong>&nbsp;&nbsp;&nbsp;&nbsp;';
			htmls += '<br/><p>';
			htmls += '<textarea class="form-control" id="replyUpdateContent">';
			htmls += replyContent;
			htmls += '</textarea></p><br/>';
			htmls += '<button type="button" class="btn btn-outline-success" onclick="replyUpdateConfirm(\'' + replySeq + '\')">수정 완료</button>&nbsp;&nbsp;';
			htmls += '<button type="button" class="btn btn-outline-success" onclick="getreplylist()">수정 취소</button>';
			htmls += '</div><br/>';
			$('#replySeq'+replySeq).replaceWith(htmls);
			$('#replySeq'+replySeq+'#replyContent').focus();
		}
			
		
		//댓글 수정 호출 함수
		function replyUpdateConfirm(replySeq) {	
			var delConfirm = confirm('댓글 수정을 완료하시겠습니까?');
			
				if (delConfirm) {
			    	alert('수정되었습니다.');
			    	replyUpdateBtn(replySeq);
			   	} else {
			      	alert('수정이 취소되었습니다.');
			      	getreplylist();
			   	}	
		}
		
		
		//댓글 수정 함수
		function replyUpdateBtn(replySeq) {	
			var replyUpdateurl = "${root}replyUpdate/";				
			var replyContent = $('#replyUpdateContent').val();
			var paramData = JSON.stringify({"replyContent": replyContent});
			var headers = {"Content-Type" : "application/json", "X-HTTP-Method-Override" : "POST"};
			
			$.ajax({
				url : replyUpdateurl + replySeq,				
				headers : headers,
				data : paramData,
				dataType : 'text',
				type : 'POST',
				contentType : 'application/json',
				
				success: function(result){
					getreplylist();
					console.log("댓글이 수정됐습니다.");
				
				}, error: function(error){
					console.log("에러 : " + JSON.stringify(error));
				}
			});
		}
		
		
		//댓글 삭제 호출 함수
		function replyDeleteConfirm(replySeq) {	
			var delConfirm = confirm('정말 댓글을 삭제하시겠습니까?');
			
				if (delConfirm) {
			    	alert('삭제되었습니다.');
			    	replydelete(replySeq);
			   	} else {
			      	alert('삭제가 취소되었습니다.');
			      	getreplylist();
			   	}	
		}
		
			
		//댓글 삭제 함수
		function replydelete(replySeq) {				
			var replyDeleteurl = "${root}replydelete/";
			var headers = {"Content-Type" : "application/json", "X-HTTP-Method-Override" : "POST"};
			
			$.ajax({
				url: replyDeleteurl+replySeq,
				headers : headers,
				type: 'POST',
				dataType : 'text',
				contentType : 'application/json',
				
				success: function(result){
					getreplylist();
				
				}, error: function(error){
					console.log("에러 : " + JSON.stringify(error));
				}
			});
		}

		//글 삭제시 이벤트 처리
		function button_event() {

			if (confirm("정말 삭제하시겠습니까??") == true) { //확인

				document.form.submit();

			} else { //취소

				return;

			}

		}
	</script>

</body>