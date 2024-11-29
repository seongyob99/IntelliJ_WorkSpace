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
<%--  로그인 한 유저 표시--%>
<%--<a>이름: ${loginInfo.name}</a>--%>
<%--<br>--%>
<%--<a>임시 로그인한 유저 정보: ${member.uuid}</a>--%>
<%--<br>--%>
<%--<a>임시 조회한 게시글 번호 정보 : ${cookie.viewTodos.value}</a>--%>
<h4>Test</h4>
<c:forEach var="member" items="${list}">
    <tr>
        <td>${member.name}</td>
        <td>${member.uuid}</td> <!-- UUID 출력 -->
    </tr>
</c:forEach>


<form action="/member/logout" method="post">
    <button type="submit">로그아웃테스트</button>
</form>

<h1>멤버 등록하기</h1>
<button><a href="/member/register">멤버등록</a></button>

<h2>멤버 한명 조회</h2>
<button><a href="/member/read?mid=asy">하나 조회</a></button>

<h2>멤버 목록</h2>
<ul>
    <c:forEach var="dto" items="${list}">
        <li>
            <span>${dto.mid}</span>
            <span><a href="/member/read?mid=${dto.mid}">${dto.name}</a></span>
            <span>${dto.dueDate}</span>
            <span>${dto.finished? "완료": "미완료"}</span>
        </li>
    </c:forEach>
</ul>
</body>
</html>