<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>index.jsp</title>
</head>
<body>
<h1>Hello Spring</h1>

<pre>
	Framework: 개발에 필요한 기본적인 기능들을 일정한 구조와 규칙대로 미리 구현해둔 툴
				개발자가 효육적으로 애플리케이션을 만들 수 있도록 재사용 가능한 코드들과 도구들의 집합
				
	Spring framework: java 기반의 enterprise(기업용 또는 대규모)급 애플리케이션을 개발하기 위한 경량 프래임워크
	
	Maven: java 용 프로젝트 빌드 관리 도구
			기존에 라이브러리 파일들을 다운로드 받아서 /WEB-INF/lib 에 추가 하는 방식에서
			pom.xml(Project Object Model) 파일에 자바 버젼이나 라이브러리 버젼 정보를 
			추가해서 총합하여 관리하는 방식
	
	IoC와 DI Spring 의 기술
	- Inversion of Control: 프로그램 구동에 필요한 객체들을 ioc 가 관리해준다
							Spring framework 에서 객채들을 Bean(콩) 이라고 한다
	- Dependency Injection: ioc 컨테이너가 관리하는 Bean 을 자동으로 읽어와 객체에 주입
	
	Spring MVC
		- DispatcherServlet: 유일한 서블릿 클래스로써 클라이언트 요청이 들어오면 컨트롤러에 해당 요청을 위임하는 역할
		- HandlerMapping: 각 컨트롤러 메소들별 URL 정보를 가지고 있다가, DispatcherServlet 이 적절한 컨ㄷ트올러에 요철할 수 있도록 지정
		- Controller: 클라이언트 요청에 대해서 처리하고 적절한 View 나 Module 을 반환하는 역할
		- ViewResolver: 컨트롤러가 반환하는 논리적인 뷰 이름을 실제 뷰 페이지 경로롤 변환하는 역할
	</pre>

<hr>

<h2>Spring GET, POST 처리</h2>

<%-- 이제는 해당 url의 서블릿이 아닌 메소드로 이동한다 --%>
<h3>
    <a href="/getTest.kh">GET 요청 테스트</a>
</h3>

<form action="/postTest.kh" method="post">
    <input type="submit" value="POST 테스트 요청">
</form>

<h2>서블릿 VS 스프링 요청 파라미터 처리</h2>

<h3>기존 Servlet 방식</h3>
<form action="/servletParamTest.kh" method="get">
    ID : <input type="text" name="testId"><br> PW : <input
        type="password" name="testPw"><br> <input type="submit"
                                                  value="서블릿 파라미터 테스트">
</form>

<h3>Spring 방식 - 1</h3>
<form action="/springParamTest1.kh" method="post">
    ID : <input type="text" name="testId"><br> PW : <input
        type="password" name="testPw"><br> <input type="submit"
                                                  value="스프링 파라미터 테스트1">
</form>

<h3>Spring 방식 - 2</h3>
<form action="/springParamTest2.kh" method="post">
    ID : <input type="text" name="memberId"><br> PW : <input
        type="password" name="memberPw"><br> Name : <input
        type="text" name="memberName"><br> Phone : <input
        type="text" name="memberPhone"><br> <input type="submit"
                                                   value="스프링 파라미터 테스트2">
</form>

<h2>서블릿 VS 스프링 응답 데이터 처리</h2>
<h3>
    <a href="/servletResponseTest.kh">서블릿 방식</a>
</h3>
<h3>
    <a href="/springResponseTest1.kh">Spring 방식 - 1</a>
</h3>
<h3>
    <a href="/springResponseTest2.kh">Spring 방식 - 2</a>
</h3>

<h3>스프링 요청 및 응답 처리</h3>
<form action="/springReqAndResTest.kh" method="post">
    ID : <input type="text" name="memberId"><br> PW : <input
        type="password" name="memberPw"><br> <input
        type="submit" value="스프링 요청 및 응답">
</form>

<h2>
    <a href="/ajax/movePage.kh">AJAX 테스트 페이지 이동</a>
</h2>

<hr>

<h3>MVC 로 연습</h3>
<c:if test="${empty loginMember}">
    <form action="/member/login.kh" method="post">
        아이디 : <input type="text" name="memberId"> <br> 비밀번호 : <input
            type="password" name="memberPw"> <br> <input
            type="submit" value="로그인"> <a href="/member/joinFrm.kh">회원가입</a>
    </form>
</c:if>

<c:if test="${not empty loginMember}">
    <h3>
        <a href="/member/logout.kh">로그아웃</a>
    </h3>
    <h3>
        <a href="/member/mypage.kh">마이페이지</a>
    </h3>
    <h3>
        <a href="/member/delete.kh?memberId=${loginMember.memberId}">회원탈퇴</a>
    </h3>
    <h3>
        <a href="/member/allMemberList.kh">전체 회원조회</a>
    </h3>
</c:if>
</body>
</html>
