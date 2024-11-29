<%--
  Created by IntelliJ IDEA.
  User: seong
  Date: 24. 11. 27.
  Time: 오후 3:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>멤버 등록</title>
</head>
<body>
<h1>멤버 등록</h1>
<form action="/member/register" method="post">
    <div>
        <label for="mid">아이디:</label>
        <input type="text" id="mid" name="mid" placeholder="아이디를 입력해주세요" required>
    </div>
    <div>
        <label for="mpw">비밀번호:</label>
        <input type="password" id="mpw" name="mpw" placeholder="비밀번호를 입력해주세요" required>
    </div>
    <div>
        <label for="name">이름:</label>
        <input type="text" id="name" name="name" placeholder="이름을 입력해주세요" required>
    </div>
    <div>
        <label for="dueDate">기한:</label>
        <input type="date" id="dueDate" name="dueDate">
    </div>
    <div>
        <label for="finished">완료 여부:</label>
        <input type="checkbox" id="finished" name="finished">
    </div>
    <button type="reset">초기화</button>
    <button type="submit">등록</button>
</form>
</body>
</html>
