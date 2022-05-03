<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>MemberPopup</title>
</head>
<body>
	<h2>${info.memberNickname } 님의 정보 </h2>
		<table>
			<tr>
				<th>Id</th>
				<td>${info.memberId }</td>
			</tr>
			<tr>
				<th>닉네임</th>
				<td>${info.memberNickname }</td>
			<tr>
				<th>등록일</th>
				<td>${info.memberRegDay }</td>
			</tr>
			<tr>
				<th>생일</th>
				<td>${info.memberBirthDay }</td>
			</tr>
			<tr>
				<th>Level</th>
				<td>${info.memberLevel }</td>
			</tr>
		</table>
</body>
</html>