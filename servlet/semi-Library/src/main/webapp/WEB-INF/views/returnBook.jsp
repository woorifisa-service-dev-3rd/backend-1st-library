<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<script>
    function removeBook(button) {
        var row = button.parentElement.parentElement;
        row.remove();
    }
</script>
</head>
<body>
	<table>
		<tr>
			<th>책이름</th>
		</tr>
		
		<c:choose>
			<c:when test="${empty returnBookList}">
				<tr>
					<td>등록된 책 정보가 없습니다.</td>
				</tr>
			</c:when>
			<c:otherwise>
				<c:forEach items="${returnBookList}" var="returnBookList">
					<tr>
						<td>${returnBookList.bookTitle}</td>
						<td><input type="button" value="반납" onclick="removeBook(this)" /></td>
					</tr>
				</c:forEach>
			</c:otherwise>
		</c:choose>

	</table>
</body>
</html>