<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script>
	alert("질문 추가 에러! \n 메인 화면으로 돌아갑니다.");
	document.location.href="<c:url value='/'/>";
</script>