<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript"
	src="<c:url value='/resources/'/>static/js/ckeditor/ckeditor.js"></script>

	<script>
		function nullCheck(){
			if(writeForm.qnaTitle.value==""){
				alert("제목을 입력해주세요.");
				writeForm.qnaTitle.focus();
				return false;
			}else if(writeForm.qnaContent.value==""){
				alert("내용을 입력해주세요.");
				writeForm.qnaContent.focus();
				return false;
			}
		}
	</script>
</head>
<body>
<!-- 파일 첨부 시 form 태그의 필요 속성 1. 반드시 method가 post 2. enctype을 지정 ▶ enctype='multipart/form-data' --> 
<form action="<c:url value='/qna/write' />" method="POST" enctype="multipart/form-data"
	name ="writeForm" onsubmit="return nullCheck()" >
	
	
	<table border="1">
		<tr>
			<th>제목</th>
			<td><input type="text" name="qnaTitle" /></td>
		</tr>
		
		<tr>
			<th>작성자</th>
			<td>
				<c:choose>
					<c:when test="${!empty adminAuthInfoCommand}">
						${adminAuthInfoCommand.adminNickname}
					</c:when>
					<c:otherwise>
						${memberLogin.memberNickname }
					</c:otherwise>
				</c:choose> </td>
		</tr>
		<tr>
			<th>내용</th>
			<td><textarea rows="" cols="" name="qnaContent"></textarea>
			<script>CKEDITOR.replace('qnaContent', {filebrowserUploadUrl:'${pageContext.request.contextPath}/qna/fileupload'});</script>
			</td>
		</tr>
		<tr>
			<th>파일 첨부</th>
			<td>
				<label>
					<input type="file" name="uploadfile" />
				</label>
		
	</table>
	<input type="submit" value="등록하기" >
</form>





</body>
</html>