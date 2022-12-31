# SpringBoot Study

## thymeleaf_basic

- Thymeleaf 기본 기능 예제
- Controller에서 HTML 페이지로 객체 송,수신 방법
- HTML 페이지에서 Thymeleaf를 사용해 객체 접근 방법
- Thymeleaf의 조건문, 반복문, 연산, layout 등에 대해 정리

## thymeleaf_form

- Thymeleaf 폼 관련 예제
- 학생 추가 Form을 통해 학생에 대한 정보를 입력받고 리스트로 출력하는 예제
  - HTML, Thymeleaf를 활용한 Form 생성 (th:field 활용)
  - Form 입력 값을 Controller로 수신 방법
  - input 태그 외에도 radio, checkbox, select 등의 태그 입력 방법
  - properites 파일을 활용한 메세지, 국제화 기능 추가 

![](https://blog.kakaocdn.net/dn/b3vfCQ/btrQtRLvUpf/zq3xSmMGiZ3hHKU6YoGbJ0/img.png)

## basic_board

- DB를 사용하지 않은 게시판 구현 예제
  - 게시물 CRUD 기능 구현
  - 파일 첨부 기능 및 다운로드 기능 구현
  - 이미지 업로드 및 미리보기 기능 구현

![](https://blog.kakaocdn.net/dn/3ICS3/btrP7NJztSf/jUO3ejt0zFotaNTSOtGFQ1/img.png)
![](https://blog.kakaocdn.net/dn/bSLJJs/btrRd62noh4/rcTeBCKJJHCe7FkVz4n63K/img.png)

## validation_example

- Thymeleaf로 만든 Form을 활용해 Validation(검증)하는 예제
  - field error, global error 사용
  - error.properties를 활용한 에러 메세지 설정

## entityManager_example

- Entity Manager을 주입 받아 MySQL DB에 Data CRUD 예제

## jparepository_example

- Jpa Repository를 사용해(상속 받아) MySQL DB에 Data CRUD 예제

## jpa_mapping_example

- Jpa Repository를 사용한 1:1, 1:N, N:M 연관관계 매핑 예제
- 1:N 연관관계 매핑 => Team : Player = 1 : N 관계