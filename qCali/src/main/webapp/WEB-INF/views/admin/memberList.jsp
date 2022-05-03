<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>멤버 리스트</title>
</head>

<body>
	<jsp:include page="/WEB-INF/views/admin/main/adminHeader.jsp"></jsp:include>

	<table border="1">
		<tr>
		<th>아이디</th>
		<th>비밀번호</th>
		<th>가입 날짜</th>
		<th>이메일 인증상태</th>
		<th>레벨</th>
		</tr>
		
		<c:forEach var="m" items="${members }">
		<tr>
			<td>${m.memberId}</td>
			<td>${m.memberNickname }</td>
			<td>${m.memberRegDay }</td>
			<td>${m.memberAuth }</td>
			<td>${m.memberLevel}</td>
			<td><a href="javascript:void(0);" class="btn btn-danger" onclick="deleteConfirm();">삭제</a></td>
	
		</tr>
		<script type="text/javascript">
		function deleteConfirm(){
		if(!confirm("정말 삭제하시겠습니까??")){
			return false;
		}else{
			location.href="<c:url value='/admin/member/delete?memberSeq='/>"+${m.memberSeq};
			
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



	<br> <a href="<c:url value='/admin/logout'/> ">로그아웃 하기</a><br>   
	<br> <a href="<c:url value='/admin/question/list'/> ">질문 추가/승인</a><br>
	<br> <a href="<c:url value='/admin/board/list'/> ">게시물 보기</a><br>   	



</body>
</html>