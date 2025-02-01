# wearther_backend
2024-2 SOLUX Project

## 🌤️ Main 기능
- 회원가입/로그인/로그아웃
- 사용자 위치·취향·보유한 옷 관리
- 날씨와 사용자 정보를 기반으로 적절한 의상 추천
- 보유한 옷 조회, 추천상품 조회, 추천상품 구매 사이트 연결 기능
- 마이페이지

## 👩‍💻 역할 분담

| 이름  | 역할분담 |
|-----| ------ |
| <a href="https://github.com/jungwoow">권정우</a> |홈 화면|
| <a href="https://github.com/seoyeonsw">백서연</a> |위치·취향·옷 관리, 마이페이지|
| <a href="https://github.com/qlcskcode">윤선희</a> |회원가입, 로그인, 로그아웃|
| <a href="https://github.com/yeonjae02">최연재</a> |ERD 디자인, 옷장, CI/CD 파이프라인 구축(배포)|

## 🌳 프로젝트 구조
```
├─java
│  └─com
│      └─appsolve
│          └─wearther_backend
│              ├─apiResponse
│              │  ├─dto
│              │  └─exception
│              │      └─handler
│              ├─auth
│              │  ├─Controller
│              │  ├─Dto
│              │  ├─Entity
│              │  ├─jwt
│              │  ├─Repository
│              │  └─Service
│              ├─closet
│              │  ├─api
│              │  ├─dto
│              │  ├─entity
│              │  ├─repository
│              │  └─service
│              ├─config
│              ├─Controller
│              ├─Dto
│              ├─Entity
│              ├─home
│              │  ├─controller
│              │  ├─dto
│              │  └─service
│              ├─init_data
│              │  ├─entity
│              │  ├─repository
│              │  └─service
│              ├─profileEdit
│              ├─Repository
│              └─Service
└─resources
    ├─static 
    ├─templates
    └─application.yml
``` 
