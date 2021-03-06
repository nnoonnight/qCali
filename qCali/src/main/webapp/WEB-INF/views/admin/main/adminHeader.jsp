<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">

</head>
<body>

	<nav class="navbar navbar-expand-lg navbar navbar-dark bg-dark">
		<div class="container-fluid">
			<a class="navbar-brand"
				href="${pageContext.request.contextPath }/main">QCali : Admin</a>
			<button class="navbar-toggler" type="button"
				data-bs-toggle="collapse" data-bs-target="#navbarText"
				aria-controls="navbarText" aria-expanded="false"
				aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse" id="navbarText">
				<ul class="navbar-nav me-auto mb-2 mb-lg-0">
					<li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath }/admin/board/list">게시물</a></li>
					<li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath }/admin/member/list">User</a></li>
					<li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath }/admin/question/list">질문 관리</a></li>
	        		<li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath }/notice/list">공지사항</a></li>
    			    <li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath }/qna/list">Q&A</a></li>
 			     </ul>
 			     <ul class="nav jsutify-content.end">
	   		  		<c:if test="${!empty adminAuthInfoCommand }">
	   		  			<span class="navbar-text">
	   		  				관리자 님
	   		  			</span>
  						<a class="nav-link" href="${pageContext.request.contextPath }/member/logout">로그아웃</a>
					</c:if>     
 			     </ul>
   			 </div>
 		 </div>
	</nav>


<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>


</body>
</html>