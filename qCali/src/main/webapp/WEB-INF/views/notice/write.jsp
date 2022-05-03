<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script type="text/javascript"
	src="<c:url value='/resources/static/js/ckeditor/ckeditor.js'/>"></script>
<title>공지 사항 글 쓰기</title>
<script>
	function nullCheck() {
		if (writeForm.noticeTitle.value == "") {
			alert("제목을 입력해주세요.");
			writeForm.noticeTitle.focus();
			return false;
		} else if (writeForm.noticeContent.value == "") {
			alert("내용을 입력해주세요.");
			writeForm.noticeContent.focus();
			return false;
		}
	}
</script>

</head>
<body>
	<form action="<c:url value='/notice/write' /> " method="post"
		name="writeForm" onsubmit="return nullCheck()">
		<table width="700" border="3" bordercolor="lightgray" align="center">
			<tr>
				<td>제 목</td>
				<td><input name="noticeTitle" type="text" size="70"
					maxlength="100" /></td>
			</tr>
			<tr>
				<td>작성자</td>
				<td>${adminAuthInfoCommand.adminNickname}</td>
			</tr>
			<tr>
				<td>내 용</td>
				<td>
				<textarea name="noticeContent" id="noticeContent" cols="72" rows="20"></textarea>
				<script>CKEDITOR.replace('noticeContent', {filebrowserUploadUrl:'${pageContext.request.contextPath}/notice/fileupload'});</script>
				</td>
			</tr>
			<tr align="center" valign="middle">
				<td colspan="5"><input type="reset" value="작성취소"> <input
					type="submit" value="등록하기 " /> <!--                 <input type="submit" value="등록" > -->
					<br> <a href="<c:url value='/notice/list'/> ">공지사항 목록</a><br>

				</td>
			</tr>
		</table>
	</form>
</body>
</html>