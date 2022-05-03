<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Q&A reply</title>
<script type="text/javascript"
	src="<c:url value='/resources/static/js/ckeditor/ckeditor.js'/>"></script>
</head>
<body>
	<form action="<c:url value='/qna/reply' />" method="POST" enctype="multipart/form-data"> 
		<input type="hidden" name="qnaRoot" value="${vo.qnaRoot }" />
		<input type="hidden" name="qnaStep" value="${vo.qnaStep }" />
		<input type="hidden" name="qnaIndent" value="${vo.qnaIndent }" />
		
		<table border="1">
			<tr>
				<th>제목</th>
				<td><input type="text" name="qnaTitle" class="need" /></td>
			</tr>
			<tr>
				<th>작성자</th>
				<td>
					<c:if test="${!empty adminAuthInfoCommand}">
						${adminAuthInfoCommand.adminNickname}
					</c:if>
				</td>
			</tr>
			<tr>
				<th>내용</th>
				<td><textarea rows="" cols="" name="qnaContent" class="need"></textarea></td>
				<script>CKEDITOR.replace('qnaContent', {filebrowserUploadUrl:'${pageContext.request.contextPath}/qna/fileupload'});</script>
				
			</tr>
			<tr>
				<th>파일 첨부</th>
				<td><label>
						<input type="file" name="uploadfile" />
					</label>
					<span id="file-name"> </span>
			<input type="submit" value="등록하기" >		
		</table>
	</form>
</body>
</html>