<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib  prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="<c:url value='/resources/static/css/dropdown.css'/> " rel="stylesheet" type="text/css">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">

<title>QClia :: Q&A</title>
</head>
<body>
	<jsp:include page="/WEB-INF/views/main/header.jsp"></jsp:include>
	

	
	<!-- 목록 -->
	
	
	
	<table class ="table table-striped" >
		<thead>
		<tr>
			<th>번호</th>
			<th>제목</th>
			<th>작성자</th>
			<th>날짜</th>

		</tr>
		</thead>
		<tbody>
		<c:if test="${boardtotal == 0 }">
			등록된 글이 없습니다.
		</c:if>
		<c:forEach items="${qnaList }" var="list">
		<tr>
			<td>${list.qnaNo }</td>
			<td>
				<c:forEach var="i" begin="1" end="${list.qnaIndent }">
					<c:choose>
					<c:when test="${i eq list.qnaIndent }">
						ㄴ[답변]:
					</c:when>
					<c:otherwise>
						&nbsp;&nbsp;
					</c:otherwise>		
					</c:choose>			
				</c:forEach>
				
				<c:choose>
					<c:when test="${list.qnaTitle eq 'none' }">
						삭제된 글입니다.
					</c:when>
					<c:otherwise>
						<a href="<c:url value='/qna/detail/${list.qnaSeq} '/> ">${list.qnaTitle }</a>
					</c:otherwise>
				</c:choose>
			</td>
			<!-- 관리자 일 경우 -->
			
			<c:if test="${!empty list.qnaWriter }">	
				<td>
					${list.qnaWriter }
				</td>
			</c:if>
			
			<c:if test="${empty list.qnaWriter }">
				<c:if test="${empty list.memberSeq }">
					<!-- on delete set null로 회원이 null로 바뀔 경우 -->
					<td>탈퇴회원</td>
				</c:if>
				
				<c:if test="${!empty list.memberNickname }">
					<!-- 회원의 닉네임이 있을 경우 -->
					<td><div class="dropdown">
					
							<a class="dropbtn">${ list.memberNickname}</a>
							<div class="dropdown-content">
								<a href="<c:url value='/board/mylist?memberSeq=${list.memberSeq }'/> ">게시물 보기</a>
								<a href=# onclick="popUpInfo();">회원 정보 보기</a>
							</div>
						</div></td>
						
						<script type="text/javascript">
							function popUpInfo(){
								let url = "${pageContext.request.contextPath}/member/popup?memberSeq=${list.memberSeq}";
								let name = "Member 정보";
								let specs = "height=300, width= 250, status = no, location= no, top=100, left=100";
								window.open(url, name, specs);
							}
						</script>
				</c:if>
			</c:if>
			
			<td>${list.qnaRegDay }</td>

			</tr>
		</c:forEach>
		</tbody>
	</table>
	
		<div>
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
	</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
</body>
</html>