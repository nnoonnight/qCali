<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">

<title>QCali :: 회원가입</title>
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<link rel="stylesheet" href="https://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>

<!-- css -->
<link
	href="<c:url value='/resources/static/css/member-insert-form.css'/>"
	rel="stylesheet" type="text/css">
<link
	href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
	rel="stylesheet">

</head>
<script type="text/javascript">
	let idck = false;
	let pwck = false;
	let nickck = false;

	function pwCheck() {

		if (document.getElementById('exampleInputPassword').value != ''
				&& document.getElementById('exampleRepeatPassword').value != '') {
			if (document.getElementById('exampleInputPassword').value == document
					.getElementById('exampleRepeatPassword').value) {
				document.getElementById('pwSame').innerHTML = '비밀번호가 일치합니다.';
				document.getElementById('pwSame').style.color = 'blue';
				pwck = true;
			} else {
				document.getElementById('pwSame').innerHTML = '비밀번호가 일치하지 않습니다.';
				document.getElementById('pwSame').style.color = 'red';

			}
		}
		doSignUp();
	}

	function idCheck() {
		var str = document.getElementById('exampleInputEmail').value;
		var pattern = /^[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/;
		if (!pattern.test(str)) {
			document.getElementById('idSame').innerHTML = '형식에 맞게 입력하세요.';
			document.getElementById('idSame').style.color = 'red';
		} else {
			document.getElementById('idSame').innerHTML = '';
			$
					.ajax({
						url : "/exam/member/idDup",
						type : "POST",
						dataType : "JSON",
						data : {
							"memberId" : $("#exampleInputEmail").val()
						},
						success : function(data) {
							if (data == 1) {
								document.getElementById('idSame').innerHTML = '사용할 수 없는 이메일 입니다.';
								document.getElementById('idSame').style.color = 'red';
							} else if (data == 0) {
								document.getElementById('idSame').innerHTML = '사용 가능한 이메일 입니다.';
								document.getElementById('idSame').style.color = 'blue';
								idck = true;
							}
							doSignUp();
						}

					})
		}

	}

	function nickCheck() {
		$
				.ajax({
					url : "/exam/member/nicknameDup",
					type : "POST",
					dataType : "JSON",
					data : {
						"memberNickname" : $("#exampleFirstName").val()
					},
					success : function(data) {
						if (data == 1) {
							document.getElementById('nickSame').innerHTML = '사용할 수 없는 닉네임 입니다.';
							document.getElementById('nickSame').style.color = 'red';
						} else if (data == 0) {
							document.getElementById('nickSame').innerHTML = '사용 가능한 닉네임 입니다.';
							document.getElementById('nickSame').style.color = 'blue';
							nickck = true;
						}
						doSignUp();
					}

				});

	}

	function doSignUp() {

		if (idck == true && pwck == true && nickck == true) {
			$("#memberInsert").prop("disabled", false);
		}
	}


      function findPwd_popup(){
          var url = '${pageContext.request.contextPath}/member/findPwd';
          var name = "비밀번호 찾기";
          var option = "width = 500, height = 500, top = 100, left = 200, location = no"
          window.open(url, name, option);
          
      }

</script>
<body class="bg-gradient-primary">
	<jsp:include page="/WEB-INF/views/main/header.jsp"></jsp:include>

	<div class="container">


		<div class="card o-hidden border-0 shadow-lg my-5">
			<div class="card-body p-0">
				<!-- Nested Row within Card Body -->
				<div class="row">
					<div class="col-lg-5 d-none d-lg-block bg-register-image"></div> <!-- 강아지 이미지 부분 -->
					<div class="col-lg-7">
						<div class="p-5">
							<div class="text-center">
								<h1 class="h4 text-gray-900 mb-4">Create an Account!</h1>
							</div>

							<form:form class="user" commandName="InsertCommand"
								id="insertForm">
								<div class="form-group row">
									<div class="form-group">

										<form:input path="memberId"
											class="form-control form-control-user" id="exampleInputEmail"
											placeholder="Email Address" />
										<form:errors path="memberId" />
								
									</div>
									
									<div class="form-group">
										<button type="button" class="btn btn-google btn-user btn-block" onclick="idCheck()">아이디 중복확인</button>
										<span id="idSame"></span>
									</div>
									
									<div class="form-group">
										<form:input path="memberNickname" type="text"
											class="form-control form-control-user" id="exampleFirstName"
											placeholder="Nickname" />
										<form:errors path="memberNickname" />
									</div>
									
									<div class="form-group">
									<button type="button" class="btn btn-google btn-user btn-block" onclick="nickCheck()">닉네임 중복확인
										</button>
										<span id="nickSame"></span>
									
									</div>
									
									<div class="col-sm-6 mb-3 mb-sm-0">
										<form:password path="memberPassword" 
											class="form-control form-control-user"
											id="exampleInputPassword" placeholder="Password" />
										
									</div>
									<div class="col-sm-6">
										<form:password path="memberPasswordCheck"
											class="form-control form-control-user"
											id="exampleRepeatPassword" placeholder="Repeat Password" />
										

									</div>
									<div class="col-sm-0 mb-3 mb-sm-0">
									<button type="button" class="btn btn-google btn-user btn-block" onclick="pwCheck()">비밀번호 확인</button>
										<span id="pwSame"></span>
									</div>
									
									<div class="form-group">
							
										<form:input path="memberBirthDay"
											class="form-control form-control-user" placeholder="BirthDay (MM-DD)" />
										
										<script>
										
											$(function () {
												$("#memberBirthDay").datepicker({
																	//changeMonth: true,
																	//changeDay: true,
																	//changeYear: true,
																	showMonthAfterYear : false,
																	dateFormat : "mm-dd"
																});

											});
										</script>
										<form:errors path="memberBirthDay" />

									</div>

									
									<input type="submit" class="btn btn-primary btn-user btn-block"
										value="Register Account" id="memberInsert" disabled />
								</div>
							</form:form>



							<hr>
							<a href="${naverLoginURL }" class="btn btn-google btn-user btn-block">
								 Register with Naver
							</a> <a href="${kakao_url}" class="btn btn-facebook btn-user btn-block">
								 Register with Kakao
							</a>

							<hr>
                            <div class="text-center">
                                <a class="small" href="#" onclick="findPwd_popup()" target = "_blank">Forgot Password?</a>
                            </div>
                          
							<div class="text-center">
								<a class="small" href="<c:url value='/member/login'/>">Already have an account? Login!</a>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>



	</div>
</body>
</html>