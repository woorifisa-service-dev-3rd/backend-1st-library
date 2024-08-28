<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html>
<head>
    <title>도서관 입장</title>
</head>
<body>
    <h1>도서관 입장</h1>
    <form action="/semi-Library/index.html" method="post">
        <select name="library">
            <%
                List<String> libraries = (List<String>) request.getAttribute("libraryList");
                if (libraries != null) {
                    for (String library : libraries) {
            %>
                <option value="<%= library %>"><%= library %></option>
            <%
                    }
                }
            %>
        </select>
        <input type="submit" value="등록">
    </form>
</body>
</html>