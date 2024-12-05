<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>member/allMember.jsp</title>
</head>
<body>
<h1>회원관리 - 전체 회원 조회</h1>

<hr>

<table border="1">
    <tr>
        <th>아이디</th>
        <th>이름</th>
        <th>나이</th>
        <th>전화번호</th>
        <th>성별</th>
        <th>가입일</th>
    </tr>
    <c:forEach var="member" items="${memberList}">
        <tr>
            <td>${member.memberId}</td>
            <td>${member.memberName}</td>
            <td>${member.memberAge}</td>
            <td>${member.memberPhone}</td>
            <td>${member.memberGender}</td>
            <td>${member.enrollDate}</td>
        </tr>
    </c:forEach>
</table>
<h4>
    <a href="/">메인으로</a>
</h4>
</body>
</html>