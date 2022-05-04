<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%> 

<!DOCTYPE html>
<html>
<head>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">

<meta charset="UTF-8">
<title>${diaryList.diaryTitle}</title>
</head>
<body>
<jsp:include page="/WEB-INF/views/main/header.jsp"></jsp:include>
<jsp:include page="/WEB-INF/views/main/sidebar_board.jsp"></jsp:include>
<div class="container">

	<c:if test="${!empty memberLogin}">
		<a href="<c:url value='/member/logout'/>"><button>로그아웃</button></a>
		<a href="<c:url value='/diary/write/${memberLogin.memberSeq}'/>"><button>일기쓰기</button></a>
	</c:if>

	<table class="table">
  	<thead>
		<tr>
			<!-- <th>NO</th>-->
			<th>일기제목</th>
			<th>일기내용</th>
			<th>이미지</th>
			<th>닉네임</th>
			<th>일기 쓴 날짜</th>
			<th>일기 좋아요</th>
			<th>일기 카운트</th>
			<th>공개여부</th>
		</tr>
	</thead>
		<c:if test="${ empty diaryList}">
			<tr>
				<td colspan="7">게시판에 저장된 글이 없습니다.</td>
			</tr>
		</c:if>

		<c:if test="${ !empty diaryList}">

			<tr>
				<!-- <td>${diaryList.diarySeq}</td> -->

				<td>${diaryList.diaryTitle}</td>
				<td>${diaryList.diaryContent}</td>
				<c:if test="${!empty diaryList.diaryImg }">
				<td><img src="/diaryImg${diaryList.diaryImg }" width="200" ></td>
				</c:if>
				<c:if test="${empty diaryList.diaryImg }">
				<td>	</td>
				</c:if>
				<td>${diaryList.memberNickname}</td>
				<td>${diaryList.diaryRegday}</td>
				<td>${diaryList.diaryLike}</td>
				<td>${diaryList.diaryCount}</td>
				<td>${diaryList.diaryOpen}</td>
				
			</tr>

			<div style="text-align: right;">
				<a class="text-dark heart" style="text-decoration-line: none;">
					<img id="heart" src="" height="30px">
				</a>
			</div>


			<c:if test="${myArticle == true}">

				<a href="<c:url value='/diary/edit/${diaryList.diarySeq}'/>"><button>일기
						수정</button></a>


				<button onclick="delete_button()">일기 삭제</button>
				
				<button onclick="deleteImg_button()">첨부파일 삭제</button>
				

			</c:if>




		</c:if>


	</table>
	
	
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
					'heart' : that.prop('name'),
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