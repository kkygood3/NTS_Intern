2019년 1st NTS 인턴
======================

## 1. gitlab.com에 가입합니다. 
  * 회사 메일(aaa@nts-corp.com)으로 가입 요청 메일이 도착되면, 안내에 따라 가입해 주시면 되겠습니다.
  * Profile Settings에서 Name항목을 **한글 이름**으로 변경하여 저장합니다. (필수)
  * 최초 인증용으로만 사용되므로, 메일계정과 비밀번호를 알면, 이후에도 사용가능합니다. 
  * 사용할 저장소는 다음입니다.  <https://gitlab.com/n_tech_service/2019_1st_intern>
  

## 2. 최신버전 이클립스를 설치합니다. 
##  windows 용 git client를 설치합니다. -- 별도 설명하지 않겠습니다. 


## 3. 이클립스의 git 숏컷들을 활성화 합니다. 
   * 이클립스 > Windows > Perspective > Customize Perspective... > Action Set Availability 에서 Git을 활성화 한다. 
![intern7](uploads/e8fe3b3a62c302e741f898474b3df705/intern7.png)

## 3. gitlab의 master를 로컬에 다운받습니다. 
![intern1](uploads/e1b050cb8753409b11c65329db0e2169/intern1.png)

![intern3](uploads/e2571aa46c71c7a71fae4329b01d6b38/intern3.png)

![intern4](uploads/4c56236d8ffaeaf74876637f0e8e950e/intern4.png)

![intern2](uploads/a89bffba8518c5a7b62c1cec8a9aa7f4/intern2.png)



__이후는 교육진행과 관련된 내용입니다.__
## 4. 제출하는 과제는 branch로 작성합니다. 
  * 시작할 때 제출할 branch와 개발할 branch. 각 하나씩을 생성합니다. 
  * 제출할 branch명은 __과정(PJT.숫자).이름(한글)__로 작성하고, (ex. **PJT.1.허태식** )
  * 개발하는 branch는 _dev를 붙혀서 생성합니다. 

![intern5](uploads/72e51c6a38eb22fe93ed02da5d94188f/intern5.png) 
![intern9](uploads/b4c2a367c38fff72c3be48bda0fcace0/intern9.png)

## 5. branch 생성후 master에서  본인의 branch를 checkout 합니다.
  * 아래와 같이 master 에서 1,2,3 을 순서대로 누르면 원하는 branch를 받을 수 있습니다. (주로 _dev가 되겠죠.)
![intern6](uploads/c8562eee2f671f4898a0c1cb299844db/intern6.png)
  

## 6. _dev branch에 개발이 마무리 되면, 제출할 branch로 merge request를 보냅니다.
  * master로 머지하지 마시고, 본인이 제출할 branch를 반드시 골라야 합니다. 
  * **approvers에 reviewer를 추가합니다.** 
  * ![intern10](uploads/9e1bb29a436b24dee41e509f9498cacd/intern10.png)

## 7. 작성하는 동안 계속 commit & push 하시고, 최종 마무리 되었으면 closed tag를 달아주세요.
  * tag명은 __과정(PJT.숫자).이름(한글)_closed__로 합니다. (ex. **PJT.1.허태식_closed** )

