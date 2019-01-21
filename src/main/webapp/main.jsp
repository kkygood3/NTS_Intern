<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
  <meta charset="utf-8">
  <title>JS Bin</title>
  <style>
    html{
      min-width:1230px;
      height:100%;
      width:100%;
      text-align:center;
      background:#ebeded;
    }
    
    body{
      background:white;
      width:80%;
      height:70%;
      padding: 20px;
      display:inline-block;
      overflow:auto;
    }
    
    ul{
      display:inline-block;
    }
    
    li{
      list-style:none;  
    }
    section {
      margin-top:50px;
      margin-left:50px;
      text-align:left;
    }
    
    
    .title_tag{
      font-size:1.8em;
      font-weight:bold;
      color:#2f5164;
      float:left;
      transform: rotate(-30deg)
    }
    
    .add_new_do{
      display:inline-block;
      text-decoration:none;
      font-weight:bold;
      color:white;
      background:#20adee;
      text-align:center;
      width:200px;
      height:30px;
      padding-top:5px;
      float:right;
    }
    
    .art_container>article{
      display:inline-block;
      background:skyblue;
      width:250px;
      margin:20px;
    }
    
    table{
      width:100%;
    }
    
    .do_title{
      background:#345461;
      color:white;
      font-size:1.2em;
    }
    .do_item{
      background:#cee5ef;
    }
    
    th{
      height:75px;
      width:200px;
      border-style:solid;
      border-width:5px;
      border-color:white;
      padding-left:20px;
    }
    .do_name{
      
    }
    
    .do_description{
      font-weight:lighter;
      font-size:0.8em;
    }
  </style>
</head>

<body class="container">
  <br>
  <br>
  <span class="title_tag">나의 해야할 일들</span>
  <a href="" class="add_new_do">새로운 TODO 등록</a>
  <br>
  <section class="art_container">
    <table>
      <tr class="do_title">
        <th>TODO</th>
        <th>DOING</th>
        <th>DONE</th>
      </tr>
      
      <tr class="do_item">
        <th>
          <span class="do_name">자바스크립트 공부하기</span><br>
          <span class="do_description">등록날짜:2018.03.10. 홍길동. 우선순위 1</span>
          <button>→</button>
        </th>
        <th>DOING</th>
        <th>DONE</th>
      </tr>
      
      <tr class="do_item">
        <th>TODO</th>
        <th>DOING</th>
        <th>DONE</th>
      </tr>
      
      <tr class="do_item">
        <th>TODO</th>
        <th>DOING</th>
        <th>DONE</th>
      </tr>
    </table>
  </section>
</body>

</html>