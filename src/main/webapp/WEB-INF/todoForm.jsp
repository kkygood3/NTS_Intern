<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="css/base.css">
<meta charset="UTF-8">
<title>할일 등록</title>
</head>
<body>
    <section id="content-todoForm">
        <form action="/todoAdd" method="post">
            <article>
                <p id="title">할일 등록</p>
            </article>
            <article>
                <div>
                    <p>어떤일인가요?</p>
                    <input type="text" name="title" maxlength="24" value="swift 공부하기(24자까지)" id="work" class="input-todoForm">
                </div>
                <div>
                    <p>누가 할일인가요?</p>
                    <input type="text" name="name" maxlength="17" value="홍길동" class="input-todoForm">
                </div>
                <div>
                    <p>우선순위를 선택하세요</p>
                    <input type="radio" name="sequence" value="1" required>1순위
                    <input type="radio" name="sequence" value="2">2순위
                    <input type="radio" name="sequence" value="3">3순위
                </div>
            </article>
            <article id="command">
                <div id="button-left"><a href="/main">< 이전</a></div>
                <div id="button-right">
                    <input type="submit" value="제출" class="buttonBlue">
                    <input type="reset" value="내용지우기" class="buttonBlue">
                </div>
            </article>
        </form>
    </section>
</body>
</html>