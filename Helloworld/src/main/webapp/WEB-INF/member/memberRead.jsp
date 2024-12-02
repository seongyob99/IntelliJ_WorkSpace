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
<h1>마이페이지</h1>
임시 서블릿 컨텍스트의 어플리케이션 공간에 저장된 변수 조회 : ${appTestName}
<div>
    <input type="text" name="mno" value="${dto.mid}" readonly>
</div>
<div>
    <input type="text" name="name" value="${dto.name}" placeholder="제목 입력 해주세요." readonly>
</div>
<div>
    <input type="date" name="dueDate" value="${dto.dueDate}">
</div>
<div>
    <input type="checkbox" name="finished" ${dto.finished ? "checked" : ""} readonly>
</div>
<div>
    <a href="/member/update?mid=${dto.mid}">수정/삭제</a>
    <a href="/member/list">목록가기</a>
</div>
</body>
</html>