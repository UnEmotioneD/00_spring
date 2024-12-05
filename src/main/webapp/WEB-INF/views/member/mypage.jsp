<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>member/mypage.jsp</title>
</head>
<body>
<h1>회원관리 - 마이페이지</h1>
<hr>
<table border="1">
    <tr>
        <th>아이디</th>
        <td>${sessionScope.loginMember.memberId}</td>
    </tr>
    <tr>
        <th>이름</th>
        <!-- sessionScope 를 생략하면 제일 좁은 범위부터 찾아서 온다 -->
        <td>${loginMember.memberName}</td>
    </tr>
    <tr>
        <th>전화번호</th>
        <td>${loginMember.memberPhone}</td>
    </tr>
    <tr>
        <th>나이</th>

        <td>${loginMember.memberAge}</td>
    </tr>
    <tr>
        <th>성별</th>

        <td>${loginMember.memberGender}</td>
    </tr>
    <tr>
        <th>가입일</th>
        <td>${loginMember.enrollDate}</td>
    </tr>
</table>
<h4>
    <a href="/">메인으로</a>
</h4>
</body>
</html>