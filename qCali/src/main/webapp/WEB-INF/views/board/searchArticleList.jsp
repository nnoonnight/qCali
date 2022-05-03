<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<%--닉네임 선택시, 드롭박스 구현 --%>
<link href="<c:url value='/resources/static/css/dropdown.css'/> "
	rel="stylesheet" type="text/css">

<meta charset="UTF-8" name="viewport" content="width=device-width, initial-scale=1">

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
	
<title>QCali :: BoardList</title>
</head>
<body>
<jsp:include page="/WEB-INF/views/main/header.jsp"></jsp:include>
<jsp:include page="/WEB-INF/views/main/sidebar_board.jsp"></jsp:include>
<div class="container">
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
	<%-- 해당 회원의 게시글을 보여줌 --%>
		
	게시글 수 : ${boardTotal }
	<table class="table table-hover">
		<thead>
		<tr>
			<th>NO</th>
			<th>제목</th>
			<th>닉네임</th>
			<th>작성일</th>
			<th>좋아요</th>
			<th>조회수</th>
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

					<td><a href="<c:url value='/board/detail?boardSeq=${list.boardSeq}'/>">${list.boardTitle}</a>

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
						href="search${pageMaker.makeQuery(pageMaker.startPage - 1) }&searchOption=${searchOption}&searchWord=${searchWord}">Previous</a>
					</li>
				</c:if>

				<c:forEach var="currentPage" begin="${pageMaker.startPage }"
					end="${pageMaker.endPage }">
					<li class="page-item" <c:out value="${pageMaker.cri.page == currentPage ? 'class=active' : ''}"/>><a class="page-link"
						href="search${pageMaker.makeQuery(currentPage) }&searchOption=${searchOption}&searchWord=${searchWord}">${currentPage }</a></li>
				</c:forEach>

				
			<c:if test="${pageMaker.next && pageMaker.endPage > 0 }">
				<li class="page-item"><a class="page-link"
						href="search${pageMaker.makeQuery(pageMaker.endPage + 1) }&searchOption=${searchOption}&searchWord=${searchWord}'/>">Next</a>
					</li>
				</c:if>
			</ul>
		</nav>




</div>
</body>
</html>