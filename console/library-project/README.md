# 도서 관리 시스템
- 도서를 대출하고, 반납할 수 있는 프로그램

<br>

## 팀원
<table>
  <tr>
    <td align="center">
      <a href="https://github.com/jyp-on">
        <img src="https://avatars.githubusercontent.com/u/52206904?v=4" alt="박주영" width="150" height="150"/>
      </a>
    </td>
    <td align="center">
      <a href="https://github.com/khwoowoo">
        <img src="https://avatars.githubusercontent.com/u/23547185?v=4" alt="강현우" width="150" height="150"/>
      </a>
    </td>
    <td align="center">
      <a href="https://github.com/min20ta">
        <img src="https://github.com/rlfrkdms1.png" alt="김민지" width="150" height="150"/>
      </a>
    </td>
        <td align="center">
      <a href="https://github.com/HwangSunBeom">
        <img src="https://avatars.githubusercontent.com/u/72551358?v=4" alt="황순범" width="150" height="150"/>
      </a>
    </td>
  </tr>
   <tr>
    <td align="center">
      <a href="https://github.com/jyp-on">
        <b>박주영</b>
      </a>
    </td>
    <td align="center">
      <a href="https://github.com/khwoowoo">
        <b>강현우</b>
      </a>
    </td>
    <td align="center">
      <a href="https://github.com/min20ta">
        <b>김민지</b>
      </a>
    </td>
    <td align="center">
      <a href="https://github.com/HwangSunBeom">
        <b>황순범</b>
      </a>
    </td>
  </tr>
</table>
<br>

<br>

## 프로젝트 기간
2024년 8월 21일(수) ~ 2024년 8월 22일(목)

<br>

## 주요 기능
- **학생 등록**: 사용자 정보를 입력하여 등록하거나, 기존 회원인지 조회
- **도서 대출 카드 생성**: 학생이 도서관을 이용할 수 있도록 매핑 테이블에 등록
- **도서 대출**: 도서관을 선택하여 도서 목록을 조회하고 대출 도서 목록을 작성하여 대출
- **도서 반납**: 현재 대출 내역을 조회하고 도서명을 입력하여 연체료를 조회하고 반납
- **도서 신청** : 도서명을 입력하여 선택한 도서관에 있는지 확인하고 도서를 신청

<br>

## 실행 예시
```
Last login: Thu Aug 22 10:45:43 on ttys004
 jyp-mac@JYP-M3-MACBOOK-PRO  ~/Documents/temp  ls
application.yml           패키징
library-1.0-SNAPSHOT.jar  패키징.zip
 jyp-mac@JYP-M3-MACBOOK-PRO  ~/Documents/temp  java -jar library-1.0-SNAPSHOT.jar application.yml
도서증을 등록하시겠습니까?
(1) 예 (2) 아니오
1
등록 하실 이름을 입력해주세요.
이름 : 박주영

생년월일을 입력해주세요 (yyyy-MM-dd).
생년월일 : 2000-12-13
주소를 입력해주세요.
주소 : 도봉구
도서증이 생성되었습니다.
도서관에 등록하시겠습니까??
(1) 예  (2) 아니오
1
어느 도서관에 등록하시겠습니까?
[1] 중앙 도서관 [2] 북부 도서관 [3] 남부 도서관 [4] 수원 도서관 [5] 우리 도서관 [6] 강남 도서관 [7] 도심 도서관 [8] 케이 도서관 [9] 하나 도서관 [10] 중구 도서관
1
LoanCard successfully saved.
중앙 도서관을 선택하였습니다.
책 대여는 중앙 도서관에서만 가능합니다.
도서관 목록 :
[1] 중앙 [2] 북부 [3] 남부 [4] 수원 [5] 우리 [6] 강남 [7] 도심 [8] 케이 [9] 하나 [10] 중구
어느 도서관에 입장하시겠습니까?

1

현재 도서 목록:
|      책 이름       | 재고 |
--------------------------
| 나의 라임 오렌지 나무       | 25 |
| 프라이드와 편견           | 20 |
| 백년의 고독             | 27 |
| 바람과 함께 사라지다        | 19 |

대출할 도서 목록을 작성해주세요 (도서이름 ,로 구분):
백년의 고독
대출되었습니다. 반납 기한은 대출일로 부터 14일입니다.

대출한 도서 목록:
| 책 이름 | 대출 날짜 |
---------------------------------
| 백년의 고독               | 2024-08-22 |
현재 대출 내역 :

|     책 이름       |    대출 날짜     |
---------------------------------------
| 백년의 고독 | 2024-08-22 |

반납하실 책 이름을 입력해주세요 (반납을 끝내시려면 '끝'이라 입력해주세요) :
백년의 고독
반납되었습니다.
현재 대출 내역 :

|     책 이름       |    대출 날짜     |
---------------------------------------

반납하실 책 이름을 입력해주세요 (반납을 끝내시려면 '끝'이라 입력해주세요) :
끝
도서관에 신청하고 싶은 도서 이름을 입력해주세요.
나의 책
신청되었습니다.
 jyp-mac@JYP-M3-MACBOOK-PRO  ~/Documents/temp 
```

<br>
   
## Project Setting
- jdk 17
- Gradle 기반 프로젝트
  - 빌드 및 의존성 관리
- 외부 라이브러리
  - mysql-connector-j-8.0.33.jar
  - lombok-1.18.24.jar
