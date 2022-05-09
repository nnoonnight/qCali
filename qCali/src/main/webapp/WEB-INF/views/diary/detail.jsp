<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%> 

<!DOCTYPE html>
<html>
<head>
<style>
p { text-align: center; }
</style>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">

<meta charset="UTF-8">
<title>QCali :: ${diaryList.diaryTitle}</title>
</head>
<body>
<jsp:include page="/WEB-INF/views/main/header.jsp"></jsp:include>
<jsp:include page="/WEB-INF/views/main/sidebar_board.jsp"></jsp:include>
<div class="container">
	<div class="row">
		<div class="col">
			<h3>${diaryNickname} 님의 일기장</h3>
		</div>
		
	<table class="table">
		
	<c:if test="${ empty diaryList}">
			<tr>
				<td colspan="7">없는 일기입니다.</td>
			</tr>
	</c:if>
		
	<c:if test="${ !empty diaryList}">

  	<thead>
		<tr>
			<th>일기제목</th><td>${diaryList.diaryTitle}</td>
			<th>닉네임</th><td>${diaryList.memberNickname}</td>
			<th>공개여부</th><td>${diaryList.diaryOpen}</td>
		</tr>
		<tr>
			<th>작성일</th>	<td>${diaryList.diaryRegday}</td>
			<th>조회수</th><td>${diaryList.diaryCount}</td>
			<th>좋아요</th><td>${diaryList.diaryLike}</td>
			<td> <!-- 하트버튼 -->
				<div style="text-align: right;">
				<a class="text-dark heart" style="text-decoration-line: none;">
					<img id="heart" src="" height="30px">
				</a>
				</div>
			</td>
		</tr>	

	</thead>
		


		


		<c:if test="${myArticle == true}">
		<div class="col-2">
			<a href="<c:url value='/diary/edit/${diaryList.diarySeq}'/>">
			<button class="btn btn-outline-info">일기 수정</button></a>
		</div>
		<div class="col-2">
			<button class="btn btn-outline-info" onclick="delete_button()">일기 삭제</button>
		</div>
		<div class="col-2">	
			<button class="btn btn-outline-info" onclick="deleteImg_button()">첨부파일 삭제</button>
		</div>	

			</c:if>
		<div class="col-2">	
			<c:if test="${!empty memberLogin}">
				<c:set var ="memberLogin.memberSeq" value="${memberLogin.memberSeq}"/>
				<c:set var ="testMemberSeq" value="${testMemberSeq}"/>							
				<c:if test="${memberLogin.memberSeq != testMemberSeq}">
					<a href="<c:url value='/diary/list/${memberLogin.memberSeq}'/>"><button class="btn btn-outline-info">내 일기장 가기</button></a>
				</c:if>
			</c:if>
		</div>



		</c:if>


	</table>
	
			<p>
		<c:if test="${!empty diaryList.diaryImg }"> <!-- 이미지 있으면 -->
		
			<img src="/diaryImg${diaryList.diaryImg }" width="200">		
		
		</c:if>
		</p>
<%-- 		<c:if test="${empty diaryList.diaryImg }">
		<tr>
			<td>	</td>
		</tr>
		</c:if> --%>
		<p>
			${diaryList.diaryContent}
		</p>
	</div>
	
	<script src="https://code.jquery.com/jquery-3.6.0.js"></script>
	<script>
		$(document).ready(function() {
			var heartval = ${diaryHeart};
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
					'diarySeq' : '${diarySeq}',
					'diaryHeart' : that.prop('name'),
				};
				$.ajax({
					url : '<c:url value="/diary/heart"/>',
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
	</script>

	<script type="text/javascript">
		function delete_button() {

			if (!confirm("정말 삭제하시겠습니까??")) { //아니오
				return false;			

			} else { //예

				return location.href="<c:url value='/diary/delete?diarySeq='/>"+${diaryList.diarySeq};

			}

		}
		
		function deleteImg_button() {

			if (!confirm("정말 삭제하시겠습니까??")) { //아니오
				return false;			

			} else { //예

				return location.href="<c:url value='/diary/deleteImg?diarySeq='/>"+${diaryList.diarySeq};

			}

		}
	</script>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
</div>
</body>