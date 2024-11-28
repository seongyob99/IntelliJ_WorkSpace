<%--
  Created by IntelliJ IDEA.
  User: seong
  Date: 24. 11. 27.
  Time: 오후 3:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--JSTL 사용하기 준비 단계--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>멤버 등록하기</h1>
<button><a href="/member/register">멤버등록</a></button>
<h2>멤버 한명 조회</h2>
<button><a href="/member/read?mno=5">하나 조회</a></button>

<h2>멤버 목록</h2>
<ul>
    <c:forEach var="dto" items="${list}">
        <li>
            <span>${dto.mno}</span>
            <span><a href="/member/read?mno=${dto.mno}">${dto.name}</a></span>
            <span>${dto.dueDate}</span>
            <span>${dto.finished? "완료": "미완료"}</span>
        </li>
    </c:forEach>
</ul>
</body>
</html>