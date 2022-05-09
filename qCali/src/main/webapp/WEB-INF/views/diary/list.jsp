<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
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
thead {
	display: table-header-group;
	vertical-align: middle;
	border-color: inherit;
	background: #e9ecef;
}
</style>

<title>QCali :: 일기장</title>
</head>
<body>
<jsp:include page="/WEB-INF/views/main/header.jsp"></jsp:include>
<jsp:include page="/WEB-INF/views/main/sidebar_board.jsp"></jsp:include>
<div class="container">
	<div class="container">
		<div class="row">
			<div class="col">
				<h3>${diaryNickname} 님의 일기장</h3>			
			</div>
			<div class="col-2">		
			<c:if test="${!empty memberLogin}">
				<c:set var ="memberLogin.memberSeq" value="${memberLogin.memberSeq}"/>
				<c:set var ="testMemberSeq" value="${testMemberSeq}"/>			
				<c:if test="${memberLogin.memberSeq == testMemberSeq}">
					<a href="<c:url value='/diary/write/${memberLogin.memberSeq}'/>"><button class="btn btn-outline-info">일기쓰기</button></a>
				</c:if>
				<c:if test="${memberLogin.memberSeq != testMemberSeq}">
					<a href="<c:url value='/diary/list/${memberLogin.memberSeq}'/>"><button class="btn btn-outline-info">내 일기장 가기</button></a>
				</c:if>
			</c:if>
			</div>
		</div>
		등록된 일기 수 : ${diaryTotal }
		</div>


	<table class="table table-hover">
		<thead>
		<tr>
			<th scope="col">글번호</th>
			<th scope="col">제목</th>
			<th scope="col">닉네임</th>
			<th scope="col">작성일</th>
			<th scope="col">좋아요</th>
			<th scope="col">조회수</th>
			<th scope="col">공개여부</th>
		</tr>
		</thead>
	
		<c:if test="${ empty diaryList}">
			<tr>
				<td colspan="7">게시판에 저장된 글이 없습니다.</td>
			</tr>
		</c:if>

		<c:if test="${ !empty diaryList}">
			<c:forEach var="list" items="${diaryList}">
				
				<tr>
					<td scope="row">${list.rn}</td>
						<c:if test="${memberLogin.memberSeq == testMemberSeq}" >
					<td><a href="<c:url value='/diary/detail?diarySeq=${list.diarySeq}'/>">${list.diaryTitle}</a>
					</td>
					<td>${list.memberNickname}</td>
					<td>${list.diaryRegday}</td>
					<td>${list.diaryLike}</td>
					<td>${list.diaryCount}</td>
					<td>${list.diaryOpen}</td>
					
					</c:if>
					
					<c:if test="${memberLogin.memberSeq != testMemberSeq}" >
					<c:if test="${list.diaryOpen eq 'F' }" >
					<td colspan="6">비공개 글입니다.</td>
					</c:if>
					<c:if test="${list.diaryOpen eq 'T' }" >
						<td><a href="<c:url value='/diary/detail?diarySeq=${list.diarySeq}'/>">${list.diaryTitle}</a>
					</td>
					<td>${list.memberNickname}</td>
					<td>${list.diaryRegday}</td>
					<td>${list.diaryLike}</td>
					<td>${list.diaryCount}</td>
					<td>${list.diaryOpen}</td>
					</c:if>
					</c:if>					
				</tr>
			</c:forEach>
		</c:if>
		
	</table>
	
		<div>
			<ul>
				<c:if test="${pageMaker.prev }">
					<li><a
						href="${pageMaker.makeQuery(pageMaker.startPage - 1) }">Previous</a>
					</li>
				</c:if>
				<c:forEach var="currentPage" begin="${pageMaker.startPage }"
					end="${pageMaker.endPage }">
					<li><a
						href="${pageMaker.makeQuery(currentPage) }">${currentPage }</a></li>
				</c:forEach>
				<c:if test="${pageMaker.next }">
					<li ><a
						href="${pageMaker.makeQuery(pageMaker.endPage + 1) }">Next</a>
					</li>
				</c:if>
			</ul>
		</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
</div>
</body>
</html>