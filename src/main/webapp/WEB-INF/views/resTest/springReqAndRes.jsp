<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>springReqAndResTest.jsp</title>
</head>
<body>
<h2>Spring Request and Response Test Page</h2>

<hr>
<%-- [등록된 키값].[객체에서 선언한 변수] 명 --%>
<h4>ID : ${member.memberId}</h4>
<h4>PW : ${member.memberPw}</h4>
</body>
</html>