<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>ajax/ajaxTest.jsp</title>
    <script src=https://code.jquery.com/jquery-3.7.1.min.js></script>
</head>
<body>
<h1>AJAX 테스트 페이지</h1>

<hr>

<button id="ajaxTest1">Ajax Test 1</button>
<button id="ajaxTest2">Ajax Test 2</button>
<button id="ajaxTest3">Ajax Test 3</button>
<button id="ajaxTest4">Ajax Test 4</button>
<button id="ajaxTest5">Ajax Test 5</button>
<button id="ajaxTest6">Ajax Test 6</button>

<script>
    $("#ajaxTest1").on("click", function () {
        $.ajax({
            url: "/ajax/ajaxTest1.kh",
            data: {
                str: "스프링",
                num: 10,
            },
            type: "get",
            success: function (res) {
                console.log(res);
            },
            error: function () {
                console.log("ajax error");
            }
        })
    });

    $("#ajaxTest2").on("click", function () {
        $.ajax({
            url: "/ajax/ajaxTest2.kh",
            data: {
                str: "스프링",
                num: 10
            },
            type: "get",
            success: function (res) {
                console.log(res);
            },
            error: function () {
                console.log("ajax error");
            }
        })
    });

    $("#ajaxTest3").on("click", function () {
        $.ajax({
            url: "/ajax/ajaxTest3.kh",
            data: {
                str: "스프링",
                num: 10
            },
            type: "get",
            success: function (res) {
                // Controller 에서 JSONArray 에 요소들을 추가하고 응답을 하면
                console.log(typeof res);// 응답된 데이터 자료형이 String 이다
                console.log(res);

                // parse 메소드를 통해 JavaScript Object 로 변환하여 배열이 출력됨
                res = JSON.parse(res); // JSON.parse() : json 문자열을 json 으로 변환해줌
                console.log(typeof res);// 이제 object 타입이다
                console.log(res);
            },
            error: function () {
                console.log("ajax error");
            }
        })
    });

    $("#ajaxTest4").on("click", function () {
        $.ajax({
            url: "/ajax/ajaxTest4.kh",
            data: {
                str: "스프링",
                num: 10
            },
            // dataType 을 지정하면 sting -> object
            // (Controller 에서 json 문자열로 응답 시 자동으로 JSON object 로 변환)
            // dataType : 'json',
            type: "get",
            success: function (res) {
                console.log(typeof res); // string
                console.log(res);

                res = JSON.stringify(res); // 자바스크립트 오브젝트를 문자열로 변환
                console.log(typeof res);
                console.log(res);
            },
            error: function () {
                // 반환 형식이 JSONObject 가 아니면 error 로 빠진다
                console.log("ajax error");
            }
        })
    });

    $("#ajaxTest5").on("click", function () {
        $.ajax({
            url: "/ajax/ajaxTest5.kh",
            // type 지정안하면 자동으로 get 이 된다
            type: "get",
            success: function (res) {
                console.log(typeof res);
                console.log(res);
            },
            error: function () {
                console.log("ajax error");
            }
        })
    });

    $("#ajaxTest6").on("click", function () {
        $.ajax({
            url: "/ajax/ajaxTest6.kh",
            type: "get",
            success: function (res) {
                console.log(typeof res);
                console.log(res);
            },
            error: function () {
                console.log("ajax error");
            }
        })
    });
</script>
</body>
</html>