<%--
  Created by IntelliJ IDEA.
  User: it
  Date: 2024-11-20
  Time: 오후 2:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>LoingForm</title>
</head>
<body>
<h1>미니 실습 로그인 화면 접근 서블릿을 통해서</h1>
<form action="/login/result" method = "post">
    ID : <input type="text" name="ID">
    PW : <input type="password" name="PW">
<button type="submit">로그인</button>
</form>
</body>
</html>
