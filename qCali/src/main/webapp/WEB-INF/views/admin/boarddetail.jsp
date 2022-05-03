<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시물 디테일</title>
</head>
<script>
	function deleteConfirm(){
		if(!confirm("정말 삭제하시겠습니까?")){
			return false;
		}
		else{
			location.href="<c:url value='/admin/board/delete?boardSeq='/>"+${boards.boardSeq};
		}
	}
</script>
<body>
	<jsp:include page="/WEB-INF/views/admin/main/adminHeader.jsp"></jsp:include>

	<h3>질문 : ${boards. questionContent}</h3>
	<table border="1">
		<tr>
			<th>제목</th>
			<th>작성자</th>
			<th>글 내용</th>
		</tr>
		<tr>
			<td>${boards.boardTitle }</td>
			<td>${boards.memberNickname }</td>
			<td>${boards.boardContent }</td>
		</tr>
	</table>
		<button id="boardDelete" onclick="window:location='<c:url value='/admin/board/delete?boardSeq=${boards.boardSeq }'/>'">글 삭제</button>
		
		<a href="javascript:void(0);" class="btu btn-danger" onClick="deleteConfirm();">글 삭제</a>
		<label>좋아요  +${boards.boardLike }</label>
		<label>조회수 ${boards.boardCount }</label>
	
		<br><a href='<c:url value="/admin/board/list"/>'>글 목록 가기</a><br>
		
		
</body>
</html>