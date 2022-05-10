<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%> 

<!DOCTYPE html>
<html>
<head>
<style>
.box {
   width: 1300px;
   padding-top: 3%;
   padding-left: 15%;
}

.board_title {
   font-weight: 700;
   font-size: 25pt;
   margin: 10pt;
}

.board_info_box {
   color: #6B6B6B;
   margin: 10pt;
}

.board_tag {
   color: #6B6B6B;
   font-size: 9pt;
   margin: 10pt;
   padding-bottom: 10pt;
}
</style>
<meta charset="UTF-8">
<title>QCali :: ${diaryList.diaryTitle}</title>
<!-- Option 1: Bootstrap Bundle with Popper -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
<!-- jQuery -->
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<!-- bootstrap css -->
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
<link href="<c:url value='/resources/static/css/dropdown.css'/> " rel="stylesheet" type="text/css">
</head>
<body>
<jsp:include page="/WEB-INF/views/main/header.jsp"></jsp:include>
<jsp:include page="/WEB-INF/views/main/sidebar_board.jsp"></jsp:include>

<div class="container">
	<div class="box">	
		<c:if test="${ empty diaryList}">
			<h3>없는 일기입니다.</h3>
		</c:if>
		<c:if test="${ !empty diaryList}">
		 <table class="table table-sm caption-top">
            <caption>${diaryList.memberNickname} 의 일기장</caption>
         </table>
		
		 <div>
	         <p class="board_title">${diaryList.diaryTitle}</p>
	         <p class="board_info_box">${diaryList.diaryRegday}</p>
	         <div style=" position: relative;  display: inline-block;">
		         <p class="board_info_box">by
		            <c:if test="${empty diaryList.memberNickname}">
		                  	탈퇴 회원
		            </c:if>
		            <c:if test="${!empty diaryList.memberNickname}">
		               <div class="dropdown" >
		                  <a href="#" class="dropbtn">${diaryList.memberNickname}</a>
		                  <div class="dropdown-content">
		                     <a href="<c:url value='/board/memberArticle?memberSeq=${diaryList.memberSeq}'/> ">게시물 보기</a> 
		                     <a href="<c:url value='/diary/list/${diaryList.memberSeq}'/> ">일기장 보기</a> 
		                     <a href=# onclick="popUpInfo();">회원 정보 보기</a>
		                  </div>
		               </div>
		            </c:if>
	        	 </p>
	         </div>
         </div>
         
		 <p class="board_tag">조회수 : ${diaryList.diaryCount}, 좋아요 : ${diaryList.diaryLike}</p>
         <hr>       
	     <p style="text-align: center;">
			 <c:if test="${!empty diaryList.diaryImg }"> <!-- 이미지 있으면 -->	
				<img src="/diaryImg${diaryList.diaryImg }" width="400">
				<hr>
			 </c:if>		 
		 </p>		
         <p>${diaryList.diaryContent}</p>
         
          <div style="text-align: right;">
			<a class="text-dark heart" style="text-decoration-line: none;">
				<img id="heart" src="" height="30px">
			</a>
		</div>
                  
         <div style="margin-top: 2%; padding-left: 20%; float: right;">
        
	         <c:if test="${myArticle == true}">
	            <button type="button" class="btn btn-outline-info"
	               onclick="location.href='<c:url value="/diary/edit/${diaryList.diarySeq}"/>'">일기 수정</button>
	            <button type="button" class="btn btn-outline-info"
	               onClick="delete_button();">일기 삭제</button>
	            <c:if test="${!empty diaryList.diaryImg }">
	               <button type="button" class="btn btn-outline-info"
	               onClick="deleteImg_button();">이미지 삭제</button>     
	            </c:if>    
	         </c:if>
	         <button type="button" class="btn btn-outline-info"
	               onclick="location.href='<c:url value="/diary/list/${diaryList.memberSeq}"/>'">일기장 가기</button>
       	</div>     
		
		
		</c:if> 
    
     </div>
</div>
<jsp:include page="/WEB-INF/views/main/footer.jsp"></jsp:include>
	
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

</body>