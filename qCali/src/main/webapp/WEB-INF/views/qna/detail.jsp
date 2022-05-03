<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="<c:url value='/resources/static/css/dropdown.css'/> "
	rel="stylesheet" type="text/css">
<title>QNA Detail</title>
</head>
<body>
	<h2>Q&A</h2>
	<table class="table">
		<thead>
		<tr>
			<th>제목</th>
			<td colspan="6">${vo.qnaTitle }</td>
		</tr>
		<tr>
			<th>작성자</th>
			<c:if test="${! empty vo.qnaWriter  }">
				<td>${vo.qnaWriter }</td>
			</c:if>
			<c:if test="${empty vo.qnaWriter }">
				<c:if test="${!empty vo.memberNickname }">
					<td><div class="dropdown">
							<a class="dropbtn">${ list.memberNickname}</a>
							<div class="dropdown-content">
								<a href="<c:url value='/board/mylist/memberSeq=${list.memberSeq }'/> ">게시물 보기</a> 
								<a href=# onclick="popUpInfo();">회원 정보 보기</a>
							</div>
						</div></td>
				<script type="text/javascript">
					function popUpInfo(){
						let url = "${pageContext.request.contextPath}/member/popup?memberSeq=${b.memberSeq}";
						let name = "Member 정보";
						let specs = "height=300, width= 250, status = no, location= no, top=100, left=100";
						window.open(url, name, specs);}
				</script>
						
				</c:if>
				<c:if test="${empty vo.memberSeq}">
					<td>탈퇴한 회원</td>
				</c:if>
			</c:if>

			<th>작성일자</th>
			<td>${vo.qnaRegDay }</td>

			<th>조회수</th>
			<td>${vo.qnaReadcnt }</td>
		</tr>

		<tr>
			<th>내용</th>
			<td colspan="5">${vo.qnaContent }</td>
		</tr>

		<tr>
			<th>첨부 파일</th>
			<td><c:if test="${!empty vo.qnaFileName }">
					<a href="<c:url value='/qna/download?qnaSeq=${vo.qnaSeq}'/>">
						${vo.qnaFileName } </a>
				</c:if></td>
		</tr>
	</table>


	<c:if test="${!empty admin }">
		<a href="javascript:void(0);" class="btu btn-danger"
			onClick="deleteConfirm();">삭제</a>

		<a href="<c:url value='/qna/modify?qnaSeq=${vo.qnaSeq }' /> ">수정하기</a>
		<c:if test="${ vo.qnaIndent == 0}">
			<a href="<c:url value='/qna/reply?qnaSeq=${vo.qnaSeq }' /> ">답글쓰기</a>
		</c:if>
	</c:if>

	<c:if test="${!empty member }">
		<a href="javascript:void(0);" class="btu btn-danger"
			onClick="deleteConfirm();">삭제</a>
		<a href="<c:url value='/qna/modify?qnaSeq=${vo.qnaSeq }' /> ">수정하기</a>
	</c:if>


<script>
	function deleteConfirm(){
		if(!confirm("정말 삭제하시겠습니까?")){
			return false;
		}
		else{
			location.href="<c:url value='/qna/delete?qnaSeq='/>"+${vo.qnaSeq};
		}
	}
</script>


</body>
</html>