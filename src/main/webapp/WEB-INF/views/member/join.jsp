<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>join.jsp</title>
    <script src=https://code.jquery.com/jquery-3.7.1.min.js></script>
</head>
<body>
<h1>회원가입 - 회원가입</h1>

<hr>

<form action="/member/join.kh" method="post">
    아이디 : <input type="text" name="memberId">
    <button type="button" id="idDuplicationCheck">중복체크</button>
    <br>
    비밀번호 : <input type="password" name="memberPw"> <br>
    이름 : <input type="text" name="memberName"> <br>
    전화번호 : <input type="text" name="memberPhone"> <br>
    나이 : <input type="text" name="memberAge"> <br>
    성별 : <input type="radio" name="memberGender" value="M">남
    <input type="radio" name="memberGender" value="W"> 여 <br>
    <input type="submit" value="회원가입">
</form>

<script>
    $("#idDuplicationCheck").on("click", function () {
        let memberId = $("input[name=memberId]").val();

        $.ajax({
            url: "/member/idDuplicationCheck.kh",
            data: {
                memberId: memberId
            },
            type: "get",
            success: function (res) {
                if (res === 1) {
                    alert("이미 사용중인 아이디 입니다");
                } else {
                    alert("사용 가능한 아이디 입니다");
                }
            },
            error: function () {
                console.log("ajax error");
            }
        });
    });
</script>
</body>
</html>