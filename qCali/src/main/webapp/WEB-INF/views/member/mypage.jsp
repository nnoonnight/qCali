<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
	
<!-- popup -->
 <script>
        function nickname_popup(){
            var url = '${pageContext.request.contextPath}/member/mypage/changeNickname';
            var name = "nickname_change";
            var option = "width = 500, height = 500, top = 100, left = 200, location = no"
            window.open(url, name, option);
            
        }
        
        function pwd_popup(){
            var url = "${pageContext.request.contextPath}/member/mypage/changePwd";
            var name = "pwd_change";
            var option = "width = 500, height = 500, top = 100, left = 200, location = no"
            window.open(url, name, option);
        }
    </script>
	
	
<title>QCali :: Mypage</title>
</head>


<body>
<jsp:include page="/WEB-INF/views/main/header.jsp"></jsp:include>
<jsp:include page="/WEB-INF/views/main/sidebar_board.jsp"></jsp:include>


<div class="container">
<c:if test="${confirmPW == false}">




	
		
		
<br><br><br>	
<form action="${pageContext.request.contextPath}/member/mypage/confirmPwd" method="POST">
  <div class="mb-3 row">
    <label class="col-sm-2 col-form-label">Email</label>
    <div class="col-sm-10">
      <input type="text" readonly class="form-control-plaintext" id="staticEmail" value="${memberLogin.memberId}">
    </div>
  </div>
  <div class="mb-3 row">
    <label class="col-sm-2 col-form-label">Password</label>
    <div class="col-sm-10">
      <input type="password" class="form-control"name="memberPassword"  id="inputPassword" placeholder="비밀번호를 입력해주세요">
  
      ${msg}
    </div>
    <br><br>
     <input type="submit" value="입력하기" class="btn btn-default"/>
    
  </div>
</form>

</c:if>	

<c:if test="${confirmPW == true }">

	<c:if test="${!empty memberLogin}">
		
		<table class="table table-hover">
			<thead>
			<tr>
				<th>회원 번호</th>
				<th>회원 ID</th>
				<th>회원 닉네임</th>
				<th>회원 생일</th>
				<th>회원 가입 날짜</th>
				<th>회원 인증 여부</th>
				<th>회원 레벨</th>
			</tr>
			</thead>
			<tr>
				<td>${memberLogin.memberSeq}</td>
				<td>${memberLogin.memberId}</td>
				<td>${memberLogin.memberNickname}</td>
				<td>${memberLogin.memberBirthDay}</td>
				<td>${memberLogin.memberRegDay}</td>
				<td>${memberLogin.memberAuth}</td>
				<td>${memberLogin.memberLevel}</td>


			</tr>
		</table>
		<figure class="text-end">
   <figcaption class="blockquote-footer">
		<%-- api 로그인 계정 상태 체크 -> 비밀번호 변경 불가 --%>
		<c:if test="${memberLogin.naver eq 'F' && memberLogin.kakao eq 'F'}">
			<a href="#" onclick="javascript:pwd_popup()" target = "_blank">비밀번호 변경하기</a>
		&nbsp;&nbsp;&nbsp;&nbsp;
		</c:if>
		
		<a href="#" onclick="javascript:nickname_popup()" target = "_blank">닉네임 변경하기</a>
		 </figcaption>
		 <figcaption class="blockquote-footer">
		<a href="<c:url value='/member/mypage/delete?memberSeq=${memberLogin.memberSeq }'/>" >회원탈퇴</a>
		<br>
</figcaption></figure>
	게시글 수 : ${boardTotal }
	<table class="table table-hover">
		<thead>
		<tr>
			<th>NO</th>
			<th>제목</th>
			<th>닉네임</th>
			<th>작성일</th>
			<th>좋아요</th>
			<th>조회수</th>
		</tr>
		</thead>

	
		<c:if test="${ empty boardList}">
			<tr>
				<td colspan="7">게시판에 저장된 글이 없습니다.</td>
			</tr>
		</c:if>

		<c:if test="${ !empty boardList}">
			<c:forEach var="list" items="${boardList}">
				
				<tr>
					<td>${list.rn}</td>

					<td><a href="<c:url value='/board/detail?boardSeq=${list.boardSeq}'/>">${list.boardTitle}</a>

					</td>


					<td>${list.memberNickname}</td>
					<td>${list.boardRegday}</td>
					<td>${list.boardLike}</td>
					<td>${list.boardCount}</td>
				</tr>

			</c:forEach>

		</c:if>
		</table>
	<nav aria-label="Page navigation example">
		<ul class="pagination justify-content-center">
				<c:if test="${pageMaker.prev }">
					<li class="page-item disabled"><a class="page-link"
						href="confirmPwd${pageMaker.makeQuery(pageMaker.startPage - 1) }&memberSeq=${memberLogin.memberSeq}">Previous</a>
					</li>
				</c:if>

				<c:forEach var="currentPage" begin="${pageMaker.startPage }"
					end="${pageMaker.endPage }">
					<li class="page-item" <c:out value="${pageMaker.cri.page == currentPage ? 'class=active' : ''}"/>><a class="page-link"
						href="confirmPwd${pageMaker.makeQuery(currentPage) }&memberSeq=${memberLogin.memberSeq}">${currentPage }</a></li>
				</c:forEach>

				
			<c:if test="${pageMaker.next && pageMaker.endPage > 0 }">
				<li class="page-item"><a class="page-link"
						href="confirmPwd${pageMaker.makeQuery(pageMaker.endPage + 1) }&memberSeq=${memberLogin.memberSeq}'/>">Next</a>
					</li>
				</c:if>
			</ul>
		</nav>

	</c:if>
	</c:if>

	<c:if test="${empty memberLogin}">
		<a href="<c:url value='/member/login'/>"><button>로그인</button></a>

	</c:if>
	
</div>
</body>
</html>