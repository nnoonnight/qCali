<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">

<meta charset="UTF-8">
<title>QCali :: 일기 수정</title>
<script type="text/javascript"
	src="<c:url value='/resources/static/js/ckeditor/ckeditor.js'/>"></script>

</head>
</head>
<body>
<jsp:include page="/WEB-INF/views/main/header.jsp"></jsp:include>
<jsp:include page="/WEB-INF/views/main/sidebar_board.jsp"></jsp:include>
<div class="container">
	<form:form commandName="DiaryUpdateCommand"  enctype="multipart/form-data">
		<table>
			<tr>
				<td>제목</td>
				<td><form:input path="diaryTitle" value="${diaryList.diaryTitle }"/> 
				<form:errors path="diaryTitle" /></td>

			</tr>
			<tr>
				<td>내용</td>
				<td><textarea name="diaryContent">${diaryList.diaryContent}</textarea>
				<script>CKEDITOR.replace('diaryContent');</script> 
				<form:errors path="diaryContent" /></td>

			</tr>
			<tr>
				<td>파일 업로드</td>
				<td>
				<c:if test="${ empty diaryList.diaryImg ||  diaryList.diaryImg eq 'deleted'}">
					<input class="form-control" type="file" name="img">
				</c:if>
				<c:if test="${ !empty diaryList.diaryImg}">
					<img src="/diaryImg${diaryList.diaryImg }" width="200" >
					<input class="form-control" type="file" name="img">
				</c:if>

				</td>
		
			</tr>
			<tr>
				<td>공개여부</td>
				<td>
				<c:if test="${diaryList.diaryOpen == 'T' }">
					<input type="radio" name="open" value="T" checked/>공개 
					<input type="radio" name="open" value="F" />비공개	
				</c:if>	
				<c:if test="${diaryList.diaryOpen == 'F' }">
					<input type="radio" name="open" value="T" />공개 
					<input type="radio" name="open" value="F" checked/>비공개	
				</c:if>			
				</td>

			</tr>
			<tr>
				<td colspan="2" align="right"><input type="submit" value="글쓰기" /></td>
			</tr>
		</table>

		
	</form:form>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>

</body>
</html>