<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html>
<head>
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
<title>회원가입</title>
<script
	src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src="https://code.jquery.com/jquery-3.6.0.js"></script>
</head>
<script type="text/javascript">
var submitFlag = false;

	function pwCheck() {

	    if(document.getElementById('memberPassword').value!='' && document.getElementById('memberPasswordCheck').value!='') {
	        if(document.getElementById('memberPassword').value==document.getElementById('memberPasswordCheck').value) {
	            document.getElementById('pwSame').innerHTML='비밀번호가 일치합니다.';
	            document.getElementById('pwSame').style.color='blue';
	            submitFlag = true;
	        }
	        else {
	            document.getElementById('pwSame').innerHTML='비밀번호가 일치하지 않습니다.';
	            document.getElementById('pwSame').style.color='red';
	        }
            doSignUp();
	    }
	}
	
	  function doSignUp() {

		  if (submitFlag == true) {
		  		$("#insertData").prop("disabled", false);
		  }
	  }

	</script>
<body>

<h3>QCali 비밀번호 변경</h3>
<div class="mb-3 row">
    <label class="col-sm-2 col-form-label">Password</label>
    <div class="col-sm-10">
      <input type="password"  class="form-control" id="memberPassword" name="memberPassword" placeholder="변경할 비밀번호를 입력해주세요">
    </div>
  </div>
  <div class="mb-3 row">
    <label class="col-sm-2 col-form-label">Password&nbsp;Check</label>
    <div class="col-sm-10">
      <input type="password" class="form-control" name="memberPasswordCheck"  id="memberPasswordCheck" placeholder="변경할 비밀번호를 입력해주세요">
  
		
		<span id="pwSame"></span>
    </div>
    <button type="button" class="btn btn-default" onclick="pwCheck()">비밀번호 확인</button> 
    <br><br>
    <input type="button" onClick="submit_close()" value="비밀번호 변경하기" id="insertData" disabled  class="btn btn-outline-primary"/>
<script>
	function submit_close() {

		$.ajax(
				{
					url : '${pageContext.request.contextPath}/member/mypage/changePwd',
					type : 'POST',
					data :  JSON.stringify({
						memberPassword : $('#memberPassword').val(),
						memberPasswordCheck : $('#memberPasswordCheck').val()
					}),
					contentType : 'application/json',			
					success : function(result){
						console.log(result);
						console.log(result == false);
						console.log(result == true);
						
						if(result == false){
							
							alert("사용할 수 없는 번호입니다.");
							} 
						else {

								alert("비밀번호 변경이 완료되었습니다.");
								window.close();
	
							}
						},
				});
	}

	</script>
    
  </div>


		

</body>
</html>