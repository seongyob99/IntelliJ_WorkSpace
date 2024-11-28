<%--
  Created by IntelliJ IDEA.
  User: it
  Date: 2024-11-20
  Time: 오후 4:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login2 From</title>
</head>
<body>
<h1>미니 실습 로그인 화면 2</h1>
<form action="/login2/result" method = "post">
    ID : <input type="email" name="ID">
    PW : <input type="password" name="PW">
    <button type="submit">로그인</button>
</form>
</body>
</html>
