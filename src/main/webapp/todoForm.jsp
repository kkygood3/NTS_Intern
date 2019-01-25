<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>할일 등록</title>
<link rel="stylesheet" href="/css/base.css">
</head>
<body>
    <section>
        <form action="/todoAdd" method="post">
            <article id="title">
                    <h1>할일 등록</h1>
            </article>
            <article>
                <div>
                    <p>어떤일인가요?</p>
                    <input type="text" name="title" maxlength="24" value="swift 공부하기(24자까지)">
                </div>
                <div>
                    <p>누가 할일인가요?</p>
                    <input type="text" name="name" maxlength="50" value="홍길동">
                </div>
                <div>
                    <p>우선순위를 선택하세요</p>
                    <input type="radio" name="sequence" value="1" required>1순위
                    <input type="radio" name="sequence" value="2">2순위
                    <input type="radio" name="sequence" value="3">3순위
                </div>
            </article>
            <article>
                <div><a href="/main">이전</a></div>
                <ul>
                    <input type="submit" value="제출">
                    <input type="reset" value="내용지우기">
                </ul>
            </article>
        </form>
    </section>
</body>
<script type="text/javascript" src="js/typeUpdate.js" ></script>
</html>