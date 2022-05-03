<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Q&A 수정</title>
<script type="text/javascript"
	src="<c:url value='/resources/static/js/ckeditor/ckeditor.js'/>"></script>

	<script>
		function nullCheck(){
			if(updateForm.qnaTitle.value==""){
				alert("제목을 입력해주세요.");
				updateForm.qnaTitle.focus();
				return false;
			}else if(updateForm.qnaContent.value==""){
				alert("내용을 입력해주세요.");
				updateForm.qnaContent.focus();
				return false;
			}
		}
	</script>

</head>
<body>
<header>
	<h1>공지사항 수정</h1>
</header>
	<form action="${pageContext.request.contextPath }/qna/modify?qnaSeq=${vo.qnaSeq }" method="POST"
	 enctype="multipart/form-data" name="updateForm" onsubmit="return nullCheck()">
		<input type="hidden" name="qnaSeq" value=${vo.qnaSeq } />
		<input type="hidden" name="attach" />
		
		<table>
			<tr>
				<th>제목</th>
				<td> <input type="text" class="need" name="qnaTitle" value=${vo.qnaTitle } /></td>
			</tr>
			<tr>
				<th>내용</th>
				<td><textarea rows="" cols="" class="need" name="qnaContent" >${vo.qnaContent }</textarea>
				<script>CKEDITOR.replace('qnaContent', {filebrowserUploadUrl:'${pageContext.request.contextPath}/qna/fileupload'});</script>
				</td>
			</tr>
			<tr>
				<th>첨부 파일</th>
				<td>
					<label>
						<input type="file" name="uploadfile" />
						<!-- <img src = "img/select.png" class="file-img" /> -->
					</label>
					<span id="file-name">${vo.qnaFileName }</span></td>
			</tr>			
		</table>
			<input type="submit" value="수정" >
			<a class="btn-empty" href="/qna/detail?qnaSeq=${vo.qnaSeq }">취소</a>
	</form>
</body>
</html>