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
<style>
ul {
	list-style: none;
	width: 30%;
	display: inline-block;
}

li {
	float: left;
	margin-left: 5px;
}
</style>
<title>QCali :: 일기 작성</title>
<script type="text/javascript"
	src="<c:url value='/resources/static/js/ckeditor/ckeditor.js'/>"></script>

</head>
<body>
<jsp:include page="/WEB-INF/views/main/header.jsp"></jsp:include>
<jsp:include page="/WEB-INF/views/main/sidebar_board.jsp"></jsp:include>
<div class="container">


	<form:form commandName="diaryData" enctype="multipart/form-data">
		<table>
			<tr>
				<td>제목</td>
				<td><form:input path="diaryTitle" /> 
				<form:errors path="diaryTitle" /></td>

			</tr>
			<tr>
				<td>내용</td>
				<td><form:textarea path="diaryContent" />
				<script>CKEDITOR.replace('diaryContent');</script>
				<form:errors path="diaryContent" /></td>

			</tr>
			<tr>
				<td>파일 업로드</td>
				<td><input class="form-control" type="file" name="img"></td>
			</tr>
			<tr>
				<td>공개여부</td>
				<td>
					<input type="radio" name="open" value="T" checked/>공개 
					<input type="radio" name="open" value="F" />비공개				
				</td>

			</tr>
			<tr>
				<td colspan="2" align="right"><input type="submit" value="글쓰기" class="btn btn-outline-info"/></td>
			</tr>
		</table>
				<input type="hidden" name="memberSeq" value="${memberLogin.memberSeq}">

		
	</form:form>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
</div>
</body>
</html>