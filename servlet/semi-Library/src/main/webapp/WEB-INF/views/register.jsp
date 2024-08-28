<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>회원가입</title>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
</head>
<body>

	<a href="index.html">HOME</a>
	<form action="/semi-Library/library-api/register" method="post">
		<div>
			<label for="name">아이디</label> 
			<input type="text" name="name" id="name"
				placeholder="아이디를 입력하세요">
		</div>
		<div>
			<label for="date">생일</label> 
			<input type="text" name="date" id="date"
				placeholder="생일을 입력하세요.(2000-01-01)">
		</div>
			<label for="address">주소</label> 
			<input type="text"
				name="address" id="address" placeholder="주소를 입력하세요">
		</div>
		<div>
			<button type="submit">로그인</button>
		</div>
	</form>

</body>
</html>