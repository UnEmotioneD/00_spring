<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>servletResponse.jsp</title>
</head>
<body>
<h2>Servlet Response Test Page</h2>

<hr>

<%-- Controller에서 request 객체에 등록한 Key 값을 이용해 출력 --%>
<h4>${resTest1}</h4>
<h4>${resTest2}</h4>
</body>
</html>