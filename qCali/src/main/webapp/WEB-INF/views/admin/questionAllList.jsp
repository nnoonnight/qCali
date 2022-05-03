<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>총 질문 리스트</title>
</head>
<body>
	<jsp:include page="/WEB-INF/views/admin/main/adminHeader.jsp"></jsp:include>

	<h3>총 질문 수  : ${questionTotal }</h3>
	<table border="1">
		<tr>
			<th>NO</th>
			<th>질문 내용</th>
			<th>작성자</th>
		</tr>
		<c:forEach var="q" items="${questions }">
			<tr>
				<td>${q.no}</td>
				<td>${q.questionContent }</td>
				<c:choose>
					<c:when test="${empty q.memberNickname}">
						<td>(null)</td>
					</c:when>
					<c:otherwise>
						<td>${q.memberNickname }</td>
					</c:otherwise>
				</c:choose>
			</tr>
		
		</c:forEach>
	</table>
	<div>
	  <ul>
	    <c:if test="${pageMaker.prev}">
	    	<li><a href="questionAll${pageMaker.makeQuery(pageMaker.startPage - 1)}">이전</a></li>
	    </c:if> 
	
	    <c:forEach begin="${pageMaker.startPage}" end="${pageMaker.endPage}" var="idx">
	    	<li><a href="questionAll${pageMaker.makeQuery(idx)}">${idx}</a></li>
	    </c:forEach>
	
	    <c:if test="${pageMaker.next && pageMaker.endPage > 0}">
	    	<li><a href="questionAll${pageMaker.makeQuery(pageMaker.endPage + 1)}">다음</a></li>
	    </c:if> 
	  </ul>
	</div>
	<br> <a href="<c:url value='/admin/logout'/> ">로그아웃 하기</a><br>   
	<br> <a href="<c:url value='/admin/question/list'/> ">질문 추가/승인</a><br>
	<br> <a href="<c:url value='/admin/board/list'/> ">게시물 보기</a><br>   	
</body>
</html>