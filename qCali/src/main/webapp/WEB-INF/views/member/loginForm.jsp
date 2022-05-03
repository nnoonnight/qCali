<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>

  <script>
        function findPwd_popup(){
            var url = '${pageContext.request.contextPath}/member/findPwd';
            var name = "비밀번호 찾기";
            var option = "width = 500, height = 500, top = 100, left = 200, location = no"
            window.open(url, name, option);
            
        }
  </script>
<script src="https://code.jquery.com/jquery-3.6.0.js"></script>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">


<!-- css -->
<link
	href="<c:url value='/resources/static/css/member-insert-form.css'/>"
	rel="stylesheet" type="text/css">
<link
	href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
	rel="stylesheet">

</head>

<title>QCali :: 회원가입</title>



</head>
<body >
<jsp:include page="/WEB-INF/views/main/header.jsp"></jsp:include>
<jsp:include page="/WEB-INF/views/main/sidebar_board.jsp"></jsp:include>

<div class="container"  style="text-align: center">


		<div class="card o-hidden border-0 shadow-lg my-5"  >
			<div class="card-body p-0" >
				<!-- Nested Row within Card Body -->
				<div class="row">
					<div class="col-lg-5 d-none d-lg-block bg-register-image"></div>  <!-- 강아지 이미지 부분 -->
					<div class="col-lg-7">
						<div class="p-5" >
							<div class="text-center">
								<h1 class="h4 text-gray-900 mb-4">Login!</h1>
							</div>

							<form:form class="user" commandName="loginMemberData" >
								<div class="form-group row">
									<div class="form-group">

										<form:input path="memberId"
											class="form-control form-control-user" id="exampleInputEmail"
											placeholder="Email Address" />
										<form:errors path="memberId" />
								
									</div>
									
										
									<div class="form-group">
										<form:password path="memberPassword" 
											class="form-control form-control-user"
											id="exampleInputPassword" placeholder="Password" />
										
									</div>
		
								</div>
									<div class="form-group">
									<input type="submit" class="btn btn-primary btn-user btn-block"
										value="Login"  />
									</div>
							</form:form>



							<hr>
							<a href="${naverLoginURL }" >
								   <img src="<c:url value='/resources'/>/static/images/btnG_완성형.png" width="180px"	/>
							</a> 
							&nbsp;&nbsp;&nbsp;&nbsp;
							<a href="${kakao_url}" ><img src="<c:url value='/resources/static/images/kakao_login_medium_narrow.png'/> "/>
								 
							</a>

							<hr>
                            <div class="text-center">
                                <a class="small" href="#" onclick="findPwd_popup()" target = "_blank">Forgot Password?</a>
                            </div>
                              <div class="text-center">
                                <a class="small" href="${pageContext.request.contextPath }/member/insert">Create an Account!</a>
                            </div>
						</div>
		
					</div>
				</div>
			</div>
		</div>


</div>







</body>


</html>