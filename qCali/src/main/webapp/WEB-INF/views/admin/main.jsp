<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<jsp:include page="/WEB-INF/views/admin/main/adminHeader.jsp"></jsp:include>

Main페이지
 <c:if test= "${adminAuthInfoCommand == null }">
	<a href="<c:url value='/admin/login'/> ">로그인 하기</a> 
 </c:if>
 
 <c:if test="${adminAuthInfoCommand != null }">
 	
 	<br> ${adminAuthInfoCommand.adminNickname } 님 안녕하세요 <br>
	<br> <a href="<c:url value='/admin/logout'/> ">로그아웃 하기</a><br>   
	<br> <a href="<c:url value='/admin/question/list'/> ">질문 추가/승인</a><br>
	<br> <a href="<c:url value='/admin/board/list'/> ">게시물 보기</a><br>  
	<br> <a href="<c:url value='/notice/list'/> ">공지사항 보기</a><br>   	 
	 	 
 </c:if>


</body>
</html>