<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>닉네임 변경</title>
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src="https://code.jquery.com/jquery-3.6.0.js"></script>

</head>
	<script type="text/javascript">
		var submitFlag = false;
		
	  function nickCheck() {
	        $.ajax({
	            url : "/exam/member/nicknameDup",
	            type : "POST",
	            dataType :"JSON",
	            data : {"memberNickname" : $('#memberNickname').val()},
	            success : function (data) {
	                if(data == 1) {
	                	
	                	document.getElementById('nickSame').innerHTML='사용할 수 없는 닉네임 입니다.';
				        document.getElementById('nickSame').style.color='red';
	                } else if (data == 0) {
	                	
	                	document.getElementById('nickSame').innerHTML='사용 가능한 닉네임 입니다.';
			            document.getElementById('nickSame').style.color='blue';
			            submitFlag = true;
	                }
	                doSignUp();
	            }

	        })
	    }

	  function doSignUp() {

		  if (submitFlag == true) {
		  		$("#insertData").prop("disabled", false);
		  }
  }

	</script>
<body>


<h3>닉네임 변경</h3> 

현재 닉네임 : ${memberLogin.memberNickname} <br>
<form>

<table border="1">

		<tr>
			<th>닉네임</th><td>
			<input type="text" id="memberNickname" />
			
			<button type="button" onclick="nickCheck()">중복확인</button>
			<span id="nickSame"></span>
			</td>
		</tr>

	</table>
	
	<br>
	
		<button type="button" onClick="submit_close()" id="insertData" disabled >닉네임 변경하기</button>
	
	
	<script>
	function submit_close() {
		
		let memberNickname = $('#memberNickname').val();

		$.ajax(
				{
					url : '${pageContext.request.contextPath}/member/mypage/changeNickname',
					type : 'POST',
					//data : JSON.stringify(memberNickname),
					data : memberNickname,
					contentType : 'application/json',			
					success : function()
						{
						alert("닉네임이 변경되었습니다.");
						console.log("변경된 닉네임"  + memberNickname);
					
							window.close();
						}
				});
	}

	</script>
</form>




</body>
</html>