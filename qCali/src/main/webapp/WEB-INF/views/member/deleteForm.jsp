<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
		function button_event() {

			if (confirm("정말 삭제하시겠습니까??") == true) { //확인

				document.form.submit();

			} else { //취소

				return;

			}

		}
	</script>
</head>
<body>
<form action="${pageContext.request.contextPath}/member/mypage/delete" method="POST">
	<table border="1">
		<tr>
				<td>비밀번호 : </td>
				<td><input name="memberPassword" type="password" placeholder="비밀번호를 입력해주세요"/>
				
				</td>
			</tr>
		
		</table>
		<br>
		${msg}<br><br>
		<input type="submit" value="회원탈퇴하기" onclick="button_event();"/>
		</form>
		

</body>
</html>