<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>BoardByDate</title>
	 	<style type="text/css">
			li {list-style: none; float: left; padding: 6px;}
		</style>
</head>
<body>
	<h2>${date }일 게시물</h2>
	
	<c:if test="${empty boards }">
		<h2>등록된 글이 없습니다.</h2>
	</c:if>
	<c:if test="${not empty boards }">
		<table border="1">
		<tr>
			<th>NO</th>
			<th>제목</th>
			<th>닉네임</th>
			<th>조회수</th>
			<th>좋아요</th>
		</tr>
	
	<c:forEach var="b" items="${boards}">
		<tr>
			<td>${b.rn }</td>
			<td><a href="<c:url value='/admin/board/detail/${b.boardSeq }' /> ">
				${b.boardTitle }</a></td>
			<c:choose>
			<c:when test="${empty b.memberNickname }">
				<td>(null)</td>
			</c:when>
			<c:otherwise>
			<td><div class="dropdown">
					<a href="#" class="dropbtn">${ b.memberNickname}</a>
					<div class="dropdown-content">
						<a href="<c:url value='/board/mylist?memberSeq=${b.memberSeq }'/> ">게시물 보기</a>
						<a href=# onclick="popUpInfo();">회원 정보 보기</a>
					</div>
				</div></td>
						
				<script type="text/javascript">
					function popUpInfo(){
						let url = "${pageContext.request.contextPath}/member/popup?memberSeq=${b.memberSeq}";
						let name = "Member 정보";
						let specs = "height=300, width= 250, status = no, location= no, top=100, left=100";
						window.open(url, name, specs);}
						</script>
				
				
				
			</c:otherwise>
			</c:choose>
			<td>${b.boardCount }</td>
			<td>${b.boardLike }</td>
		</tr>	
		
	</c:forEach>
	</table>
		<div>
	  <ul>
	    <c:if test="${pageMaker.prev}">
	    	<li><a href="listDay${pageMaker.makeQuery(pageMaker.startPage - 1)}&date=${date}">이전</a></li>
	    </c:if> 
	
	    <c:forEach begin="${pageMaker.startPage}" end="${pageMaker.endPage}" var="idx">
	    	<li><a href="listDay${pageMaker.makeQuery(idx)}&date=${date}">${idx}</a></li>
	    </c:forEach>
	
	    <c:if test="${pageMaker.next && pageMaker.endPage > 0}">
	    	<li><a href="listDay${pageMaker.makeQuery(pageMaker.endPage + 1)}&date=${date}">다음</a></li>
	    </c:if> 
	  </ul>
	</div>
	
	</c:if>
	
</body>
</html>