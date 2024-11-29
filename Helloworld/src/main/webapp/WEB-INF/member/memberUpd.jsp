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
    <title>Title</title>
</head>
<body>
<form action="/member/update" method="post">
    <div>
        <input type="text" name="mid" value="${dto.mid}" readonly>
    </div>
    <div>
        <input type="text" name="name" value="${dto.name}" placeholder="이름 입력">
    </div>
    <div>
        <input type="password" name="mpw" value="${dto.mpw}" placeholder="비밀번호 입력">
    </div>
    <div>
        <input type="date" name="dueDate" value="${dto.dueDate}">
    </div>
    <div>
        <input type="checkbox" name="finished" ${dto.finished ? "checked" : ""}>
    </div>
    <div>
        <button type="submit">수정하기</button>
    </div>
</form>

<form action="/member/delete?mid=${dto.mid}" method="post">
    <button type="submit">삭제하기</button>
</form>
<a href="/member/list">목록가기</a>
</body>
</html>
