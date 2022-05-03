<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>QCali :: 질문</title>
	<script>
	function nullCheck(){
		if(questionAdd.questionContent.value==""){
			alert("질문을 입력해주세요.");
			questionAdd.questionContent.focus();
			return false;
		}
	}
	</script>
</head>
<body>
	<jsp:include page="/WEB-INF/views/admin/main/adminHeader.jsp"></jsp:include>

	<h2>질문 추가하기</h2>
	<form:form action="${pageContext.request.contextPath}/admin/question/questionAdd" name ="questionAdd" commandName="questionRegistCommand" onsubmit="return nullCheck()">
		<form:input path="questionContent" placeholder="추가할 질문을 입력해주세요." id="questionContent"/>
		<form:errors path="questionContent"/>		
		<input type="submit" value="추가하기 " />
	</form:form>
	<h2>질문 승인 대기 </h2>
	<table border="1">
		<tr>
			<th>NO</th>
			<th>질문 내용</th>
			<th>작성자</th>
			<th colspan="2">질문 승인/거절</th>
		</tr>
		<c:forEach var="q" items="${questions }">
			<tr>
				<td>${q.no}</td>
				<td>${q.questionContent }</td>
					<!-- memberNickname이 null일 수 있다. -->
				<c:choose>
					<c:when test="${q.memberSeq == -1 }">
						<td>관리자</td>
					</c:when>
					<c:when test="${!empty q.memberNickname}">
						<td>${q.memberNickname }</td>
					</c:when>
					<c:otherwise>
						<td>탈퇴한 회원</td>
					</c:otherwise>
				</c:choose>
				
				<td><a href="javascript:void(0);" class="btn btn-danger" onClick="deleteConfirm();">거부</a></td>
				<td><a href="javascript:void(0);" class="btn btn-danger" onClick="approveConfirm();">승인</a></td>
				
			</tr>
		<script>
		function deleteConfirm(){
			if(!confirm("정말 거부하시겠습니까?")){
				return false;
			}else{
				location.href="<c:url value='/admin/question/delete?questionSeq='/>"+${q.questionSeq};
			}
		}
		
		function approveConfirm(){
			if(!confirm("정말 승인하시겠습니까?")){
				return false;
			}else{
				location.href="<c:url value='/admin/question/approve?questionSeq='/>"+${q.questionSeq};
			}
		}
		</script>
		</c:forEach>
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
	
	
	<a href='<c:url value="/admin/questionAll"/>'>질문목록보기</a>
	
	<br> <a href="<c:url value='/admin/logout'/> ">로그아웃 하기</a><br>   
	<br> <a href="<c:url value='/admin/question/list'/> ">질문 추가/승인</a><br>
	<br> <a href="<c:url value='/admin/board/list'/> ">게시물 보기</a><br>   	
</body>
</html>