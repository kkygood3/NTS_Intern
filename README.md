# PJT 3 전연빈

- 프로젝트 개요
  - Spring을 이용하여 예약 시스템 제작
- 개발 환경	
  - OS : Windows 10
  - language : java 8, css3, html5
  - WAS : Tomcat 8.5



# 구현

1. Web

   - URI

   | METHOD | URI   |
   | ------ | ----- |
   | GET    | /main |

   - 구현 내용
     - 페이지 렌더링으로 controller를 이용하여 mainpage.jsp와 매핑

2. API

   - URI

   | METHOD | URI             | RETURN                   |
   | ------ | --------------- | ------------------------ |
   | GET    | /api/categories | items  : {Category List} |

   - 구현 내용

     - Category 전체 불러오기

       

   - URI

   | METHOD | URI           | PARAMS                       | RETURN                                                       |
   | ------ | ------------- | ---------------------------- | ------------------------------------------------------------ |
   | GET    | /api/products | categoryId, start(시작 지점) | items : {Product List} , totalCount {카테고리 별 총 아이템 갯수} |

   - 구현 내용

     - Category 별 상품들 불러오기

     - categoryId 가 0일때는 전체 상품 불러옵니다.

     - start는 MySQL의 LIMIT을 이용하여 어느지점 부터 몇개의 데이터를 불러올지에 대한 파라미터

     - start가 음수일때는 사용자의 에러로 400 에러

       

   - URI

   | METHOD | URI             | RETURN                  |
   | ------ | --------------- | ----------------------- |
   | GET    | /api/promotions | items :{Promotion List} |

   - 구현 내용

     - 프로모션 전체 데이터 불러오기

       

   