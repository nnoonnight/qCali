<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시물 리스트</title>
</head>
<body>
	<jsp:include page="/WEB-INF/views/admin/main/adminHeader.jsp"></jsp:include>

	<h2>총 게시물 수 : ${boardTotal }</h2>
	<table border="1">
		<tr>
			<th>NO</th>
			<th>제목</th>
			<th>닉네임</th>
			<th>작성일</th>
			<th>조회수</th>
			<th>좋아요</th>
		</tr>
		
		<c:forEach var="b" items="${boards}">
		<tr>
			<td>${b.rn }</td>
			<td><a href="<c:url value='/admin/board/detail/${b.boardSeq }' /> "> ${b.boardTitle }</a></td>
				<c:choose>
				<c:when test="${empty b.memberNickname }">
					<td>(null)</td>
				</c:when>
				<c:otherwise>
					<td>${b.memberNickname }</td>
				</c:otherwise>
				</c:choose>
			<td>${b.boardRegDay }</td>
			<td>${b.boardCount }</td>
			<td>${b.boardLike }</td>
		</tr>	
		</c:forEach>
	</table>
	  <ul>
	    <c:if test="${pageMaker.prev}">
	    	<li><a href="list${pageMaker.makeQuery(pageMaker.startPage - 1)}">이전</a></li>
	    </c:if> 
	
	    <c:forEach begin="${pageMaker.startPage}" end="${pageMaker.endPage}" var="idx">
	    	<li><a href="list${pageMaker.makeQuery(idx)}">${idx}</a></li>
	    </c:forEach>
	
	    <c:if test="${pageMaker.next && pageMaker.endPage > 0}">
	    	<li><a href="list${pageMaker.makeQuery(pageMaker.endPage + 1)}">다음</a></li>
	    </c:if> 
	  </ul>

</body>
</html>