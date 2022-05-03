<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공지 사항 세부 글</title>
</head>
<body>
<header>
	<h1>공지사항 디테일</h1>
</header>
<article>
	<table border = "1">
		<tr>
			<th>제목</th>
			<td>${notice.noticeTitle}</td>
		</tr>
		<tr>
			<th>작성일자</th>
			<td>${notice.noticeRegDay}</td>
		</tr>
		<tr>
			<th>내용</th>
			<td>${notice.noticeContent}</td>
		</tr>
	</table>
</article>
	<br><a href="<c:url value='/notice/list'/> ">공지사항 목록</a><br>   	
	<c:if test="${adminAuthInfoCommand != null }">
		<a href="javascript:void(0);" class="btu btn-danger" onClick="deleteConfirm();">삭제</a>
<script>
	function deleteConfirm(){
		if(!confirm("정말 삭제하시겠습니까?")){
			return false;
		}
		else{
			location.href="<c:url value='/notice/delete?noticeSeq='/>"+${notice.noticeSeq};
		}
	}
</script>
		<button id="noticeUpdate" onclick="window:location='<c:url value='/notice/update?noticeSeq=${notice.noticeSeq }'/>'">수정</button>

	</c:if>
	
</body>
</html>