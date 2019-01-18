# PJT 1 전연빈

- 프로젝트 개요
  - java Servlet과 html, css 를 이용하여 자기소개 웹 어플리케이션 제작

- 개발 환경	
  - OS : Windows 10
  - language : java 8, css3, html5
  - WAS : Tomcat 8.5



# 구현

1. 메인 페이지

   - URI

     | METHOD | URI                 |
     | ------ | ------------------- |
     | GET    | /aboutme/index.html |

   - 구현 내용

     - 메인 페이지로 거주지 간단한 자기소개 내용 첨부

       

2. 자기소개 페이지

   - URI

     | METHOD | URI                   |
     | ------ | --------------------- |
     | GET    | /aboutme/aboutme.html |

   - 구현 내용

     - 이런걸 잘해요?

     - 나의 포트폴리오

     - 나의 최근 프로젝트 설명

       

3. 내사진 페이지

   - URI

     | METHOD | URI                  |
     | ------ | -------------------- |
     | GET    | /aboutme/photho.html |

   - 구현 내용

     - 라인 캐릭터 사진들을 이용한 자기소개

4. 현재시간 페이지

   - URI

     | METHOD | URI            |
     | ------ | -------------- |
     | GET    | /aboutme/today |

   - 구현 내용

     - Servlet과 PrintWriter 객체를 이용한 웹 페이지

     - 현재시간 - LocalDateTime을 이용해 현재시각을 알아오고

       DateTimeFormatter로 패턴매칭

       

   