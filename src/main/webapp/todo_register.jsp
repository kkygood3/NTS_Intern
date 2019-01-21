<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width">
  <title>JS Bin</title>
  <style>
html {
  min-width: 803px;
  height: 100%;
  width: 100%;
  text-align: center;
  background: #ebeded;
}
body {
  background: white;
  width: 80%;
  height: 70%;
  padding: 20px;
  display: inline-block;
  text-align: center;
  overflow: auto;
}
section {
  display: inline-block;
  text-align: left;
  width: 50%;
}
button {
  width: 100px;
  height: 40px;
  border: solid thin;
}
.btn_before {
  float: left;
  font-size: 1.05em;
  text-decoration: underline;
  background: white;
}
.btn_submit,
.btn_reset {
  float: right;
  margin-left: 10px;
  color: white;
  background: #22aded;
}
  </style>
</head>
<body>
  <h2>할일 등록</h2>
  <section>
    어떤일인가요?<br>
    <input type="text" placeholder="swift 공부하기(24자까지)" maxlength=24></input>
    <br><br>
    
    누가 할일인가요?<br>
    <input type="text" placeholder="홍길동" maxlength=10></input>
    <br><br>
    
    우선순위를 선택하세요<br>
    
    <input type="radio">1순위</input>
    <input type="radio">2순위</input>
    <input type="radio">3순위</input>
    <br><br><br>
    <button class="btn_before">< 이전</button>
    <button class="btn_reset">내용지우기</button>
    <button class="btn_submit">제출</button>
  </section>  
</body>
</html>