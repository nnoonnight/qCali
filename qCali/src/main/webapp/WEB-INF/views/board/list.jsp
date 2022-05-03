<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head>

<meta charset="UTF-8" name="viewport"
	content="width=device-width, initial-scale=1">
<%--닉네임 선택시, 드롭박스 구현 --%>
<link href="<c:url value='/resources/static/css/dropdown.css'/> "
	rel="stylesheet" type="text/css">
<!-- Bootstrap CSS -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3"
	crossorigin="anonymous">
<!-- Option 1: Bootstrap Bundle with Popper -->
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
	crossorigin="anonymous"></script>

<style>
input[type="search"] {
	width: 180px;
	margin: 0 auto;
	margin-left: 9px;
	border: 2px solid #797979;
	font-size: 14px;
	margin-top: 10px;
	padding: 4px 0 4px 14px;
	border-radius: 50px;
}

thead {
	display: table-header-group;
	vertical-align: middle;
	border-color: inherit;
	background: #e9ecef;
}

.balloon {  
 position:relative; 
 margin : 20px; 
 width:600px; 
 height:60px;
 background:#add8e6; 
 border-radius: 10px;
}
.balloon:after { 
 border-top:10px solid transparent; 
 border-left: 10px solid transparent; 
 border-right: 10px solid #add8e6; 
 border-bottom: 10px solid transparent; 
 content:""; 
 position:absolute;
 top: 0px;
 left:-20px;  
 margin-top: 20px;
 
}

.balloonReverse {  
 position:relative; 
 left: 50%;
 width:600px; 
 height:60px;
 background:gray; 
 border-radius: 10px;
}
.balloonReverse:after { 
  border-top: 10px solid transparent;
  border-right: 10px solid transparent;
  border-bottom: 10px solid transparent;
  border-left: 10px solid gray;
  content: '';
  position: absolute;
  right: -20px;
  bottom: 20px;
  margin-top: -20px;
}
</style>

<title>QCali :: BoardList</title>
</head>
<body>
	<jsp:include page="/WEB-INF/views/main/header.jsp"></jsp:include>
	<jsp:include page="/WEB-INF/views/main/sidebar_board.jsp"></jsp:include>
	<jsp:include page="/WEB-INF/views/calendar/calendar.jsp"></jsp:include>
	<div class="container">

		<%-- 질문 출력 및 글쓰기 버튼 --%>
		<h4>Today Question</h4>
		<div class="balloon">
		<h3> <small class="text-muted">&nbsp;&nbsp;${boardQuestion.questionContent}</small></h3>
	</div>
			
		<div class="balloonReverse">	
		<br>
		<h3> <figure class="text-end">
  <blockquote class="blockquote">
  <a href="<c:url value='/board/write?questionSeq=${boardQuestion.questionSeq }'/>">글쓰기answer&nbsp;&nbsp;</a> </blockquote></figure></h3>
	</div>
	

		<%--공지사항 출력 --%>
		<h4>공지사항</h4>
		<table class="table table-hover">
			<thead>
				<tr>
					<th>제목</th>
					<th>작성일</th>
				</tr>
			</thead>
			<c:forEach var="n" items="${notice }">
				<tr>
					<td><a href="<c:url value='/notice/read/${n.noticeSeq}' />">${n.noticeTitle}</a></td>
					<td>${n.noticeRegDay }</td>
				</tr>
			</c:forEach>
		</table>

		

		<%-- 검색 bar --%>
		<div class="search row">
			<div class="col-xs-2 col-sm-2">
				<form action="<c:url value='/board/search'/>">

					<select name="searchOption" class="form-select form-select-sm"
						aria-label=".form-select-sm example">
						<option value="boardTitle">제목</option>
						<option value="memberNickname">닉네임</option>
					</select>
			</div>
			<div class="col-xs-10 col-sm-10">
				<div class="input-group">
					<input type="text" name="searchWord" placeholder="SEARCH" class="form-control"/>
					<span class="input-group-btn"> 
						<input type="submit" value="검색" class="btn btn-default" />
					</span>
				</div>
				</form>
			</div>
		</div>
		
		<%--게시글 부븐 --%>

<br>
	<figure class="text-end">
			
  				 <figcaption class="blockquote-footer">
					게시글 수 : ${boardTotal }
						</figcaption>
	</figure>
	<figure>
			<a href="${pageContext.request.contextPath }/board/list">전체 목록 최신순&nbsp;</a>
			<a href="${pageContext.request.contextPath }/board/todayArticle">&nbsp; 오늘 올라온 글 보기</a>
	</figure>
		

		<table class="table table-hover">
			<thead>
				<tr>
					<th>NO</th>
					<th>TITLE</th>
					<th>작성자</th>
					<th>작성날짜</th>
					<th>좋아요</th>
					<th>카운트</th>
				</tr>
			</thead>

			<c:if test="${ empty boardList}">
				<tr>
					<td colspan="7">게시판에 저장된 글이 없습니다.</td>
				</tr>
			</c:if>

			<c:if test="${ !empty boardList}">
				<c:forEach var="list" items="${boardList}">
					<tr>
						<td>${list.rn}</td>

						<td><a
							href="<c:url value='/board/detail?boardSeq=${list.boardSeq}'/>">${list.boardTitle}</a>

						</td>

						<td><c:if test="${empty list.memberNickname }">
                  			탈퇴 회원
            		   </c:if> <c:if test="${!empty list.memberNickname }">

								<div class="dropdown">
									<a href="#" class="dropbtn">${ list.memberNickname}</a>
									<div class="dropdown-content">
										<a
											href="<c:url value='/board/memberArticle?memberSeq=${list.memberSeq }'/> ">게시물
											보기</a> <a href=# onclick="popUpInfo();">회원 정보 보기</a>
										<script type="text/javascript">
											function popUpInfo() {
												let url = "${pageContext.request.contextPath}/member/popup?memberSeq=${list.memberSeq}";
												let name = "Member 정보";
												let specs = "height=300, width= 250, status = no, location= no, top=100, left=100";
												window.open(url, name, specs);
											}
										</script>
									</div>
								</div>
							</c:if></td>
						<td>${list.boardRegday}</td>
						<td>${list.boardLike}</td>
						<td>${list.boardCount}</td>
					</tr>

				</c:forEach>

			</c:if>
		</table>
		<nav aria-label="Page navigation example">
			<ul class="pagination justify-content-center">

				<c:if test="${pageMaker.prev }">
					<li class="page-item disabled"><a class="page-link"
						href="${lastUrl}${pageMaker.makeQuery(pageMaker.startPage - 1) }">이전</a></li>
				</c:if>

				<c:forEach var="currentPage" begin="${pageMaker.startPage }"
					end="${pageMaker.endPage }">
					<li class="page-item"
						<c:out value="${pageMaker.cri.page == currentPage ? 'class=active' : ''}"/>><a
						class="page-link" href="${lastUrl}${pageMaker.makeQuery(currentPage) }">${currentPage }</a></li>
				</c:forEach>

				<c:if test="${pageMaker.next && pageMaker.endPage > 0 }">
					<li class="page-item"><a class="page-link"
						href="${lastUrl}${pageMaker.makeQuery(pageMaker.endPage + 1) }">다음</a></li>
				</c:if>
			</ul>
		</nav>


	</div>
</body>
</html>