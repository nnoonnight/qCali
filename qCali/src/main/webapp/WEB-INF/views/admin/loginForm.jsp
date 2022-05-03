<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>QCali :: 관리자 로그인</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">

</head>
<body>
	<jsp:include page="/WEB-INF/views/admin/main/adminHeader.jsp"></jsp:include>
	
		<form:form commandName = "AdminLoginCommand">
			<div class="mb-3">
				<label class="form-label">이메일 입력 </label>
					<form:input class="form-control" path="adminId"/>
					<form:errors path="adminId"/>
			</div>
			<div class="mb-3">
				<label class="form-label">비밀번호 </label>
					<form:password class="form-control" path="adminPassword"/>
					<form:errors path="adminPassword"/>
			</div>
			
			<div class="mb-3 form-check">
				<button type="submit" class="btn btn-primary">로그인</button>
			</div>
		</form:form>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
</body>
</html>