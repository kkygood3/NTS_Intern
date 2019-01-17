2019년 1st NTS 인턴
======================

# PJT.1 Project

### 개요
소개하는 웹페이지를 3개의 HTML(index.html, aboutme.html, photo.html)과 한개의 서블릿(TimeServlet)으로 Tomcat 서버 위에 구동하도록 구현.

### 요구사항
##### 1.  index.html
- 정렬과 배치간격을 일정하게 유지
- 상단 네비게이션 버튼 간격 일정하게 유지
- 가운데 컨텐츠 가운데 정렬
- 네모난 UI는 그림자 효과
- 지도는 이미지 형태로 추가
- 링크 걸려있는 부분은 마우스 올릴 시 커서 모양으로 전환

##### 2.  aboutme.html
- index.html과 같이 정렬과 배치 간격 유지
- 같은 수준의 태그에선 같은 크기의 글자크기 유지
- 가운데 영역은 세가지 영역으로 나누어지고 각 영역의 간격은 일정
- header와 footer는 index.html과 같이 유지

##### 3.  photo.html
- index.html과 같이 정렬과 배치 간격 유지
- 줄 간격 글자크기 일정
- 사진에 테두리
- header와 footer는 index.html과 같이 유지

##### 4.  TimeServlet.java
- 현재 시간은 java.time.LocalDateTime 클래스 사용
- 메인 화면 버튼 클릭 시 index.html로 이동
- 화면 가운데 현재시간 표시