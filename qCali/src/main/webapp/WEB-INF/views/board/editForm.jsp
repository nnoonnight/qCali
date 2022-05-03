<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<script type="text/javascript"
	src="<c:url value="/resources"/>/static/js/ckeditor/ckeditor.js"></script>
</head>
<body>

	<c:if test="${!empty memberLogin}">
		<h2>로그인 성공</h2>
		<table border="1">
			<tr>
				<th>회원 번호</th>
				<th>회원 ID</th>
				<th>회원 닉네임</th>
				<th>회원 생일</th>
				<th>회원 가입 날짜</th>
				<th>회원 인증 여부</th>
				<th>회원 레벨</th>
			</tr>
			<tr>
				<td>${memberLogin.memberSeq}</td>
				<td>${memberLogin.memberId}</td>
				<td>${memberLogin.memberNickname}</td>
				<td>${memberLogin.memberBirthDay}</td>
				<td>${memberLogin.memberRegDay}</td>
				<td>${memberLogin.memberAuth}</td>
				<td>${memberLogin.memberLevel}</td>


			</tr>
		</table>
		<a href="<c:url value='/member/logout'/>"><button>로그아웃</button></a>
	</c:if>
	<form:form commandName="boardEditData">
		<table border="1">
			<tr>
				<td>제목</td>
				<td><input name="boardTitle" value="${ articleInfo.boardTitle}"/> 
				<form:errors path="boardTitle" /></td>

			</tr>
			<tr>
				<td>내용</td>
				<td><textarea name="boardContent" id="boardContent"> ${ articleInfo.boardContent }</textarea>
						<script type="text/javascript">	
			CKEDITOR.replace('boardContent',
			{filebrowserUploadUrl:'${pageContext.request.contextPath}/board/ckUpload'
			});
		</script>
				
				<form:errors path="boardContent" /></td>

			</tr>
		</table>

		
		<input type="submit" value="수정하기" />
	</form:form>

</body>
</html>