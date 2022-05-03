<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src="https://code.jquery.com/jquery-3.6.0.js"></script>
<style>
body {
	text-align: center;
}

table {
	margin: auto;
	width: 50%;
	height: 150px;
}
</style>
<meta charset="UTF-8">
<title>비밀번호 찾기</title>
</head>
<body>
	<h2>가입시 사용한 이메일을 입력해주세요.</h2>


	<input type="text" id="memberId" />

	<br>

	<button type="button" onClick="submit_close()">비밀번호 찾기</button>


	<script>
		function submit_close() {

			let memberId = $('#memberId').val();

			$.ajax({
				url : '${pageContext.request.contextPath}/member/findPwd',
				type : 'POST',
				data : memberId,
				contentType :'application/json',
				success : function(result) {
					console.log(result);
					console.log(result == false);
					console.log(result == true);
					
					
					if(result == true){
					alert("이메일로 임시 비밀번호가 발송되었습니다.");
					window.close();
					
					} else {
						
						alert("회원 정보가 없습니다.");
						
					}
				},
			});
		}
	</script>


</body>
</html>