<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>ȸ������</title>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
</head>
<body>

	<a href="index.html">HOME</a>
	<form action="/semi-Library/library-api/register" method="post">
		<div>
			<label for="name">���̵�</label> 
			<input type="text" name="name" id="name"
				placeholder="���̵� �Է��ϼ���">
		</div>
		<div>
			<label for="date">����</label> 
			<input type="text" name="date" id="date"
				placeholder="������ �Է��ϼ���.(2000-01-01)">
		</div>
			<label for="address">�ּ�</label> 
			<input type="text"
				name="address" id="address" placeholder="�ּҸ� �Է��ϼ���">
		</div>
		<div>
			<button type="submit">�α���</button>
		</div>
	</form>

</body>
</html>