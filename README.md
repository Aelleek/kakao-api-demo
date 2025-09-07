# Kakao API Demo

Spring Boot 기반으로 Kakao API를 연동하고 테스트하기 위한 프로젝트입니다.  
OAuth2 로그인부터 시작해서 카카오에서 제공하는 다양한 API(사용자 정보, 메시지, 친구 목록 등)를 실험합니다.

## Requirements
- Java 17+
- Gradle 8.x (Wrapper 포함)
- Spring Boot 3.x
- Kakao Developers 애플리케이션 (REST API 키, Redirect URI 설정 필요)

---

## Getting Started

1. **카카오 개발자 콘솔에서 앱 생성**
    - [Kakao Developers](https://developers.kakao.com) → 내 애플리케이션 → 앱 키 확인
    - Redirect URI 등록: `http://localhost:8080/login/oauth2/code/kakao`

2. **환경설정**
   `src/main/resources/application.yml` 예시:
   ```yaml
   spring:
     security:
       oauth2:
         client:
           registration:
             kakao:
               client-id: <REST_API_KEY>
               client-secret: <CLIENT_SECRET>
               redirect-uri: "{baseUrl}/login/oauth2/code/{registrationId}"
               authorization-grant-type: authorization_code
               client-authentication-method: POST
               scope:
                 - profile_nickname
                 - account_email
           provider:
             kakao:
               authorization-uri: https://kauth.kakao.com/oauth/authorize
               token-uri: https://kauth.kakao.com/oauth/token
               user-info-uri: https://kapi.kakao.com/v2/user/me
               user-name-attribute: id
빌드 및 실행

bash
코드 복사
./gradlew clean build
./gradlew bootRun
테스트

브라우저에서: http://localhost:8080/oauth2/authorization/kakao

로그인 및 권한 동의 후 사용자 정보 확인 가능





# 1. Kakao REST API Java Spring RestClient 예제

이 프로젝트는 Kakao REST API를 Java Spring RestClient 구현한 예제입니다.

## 주요 기능

- 카카오 로그인
- 사용자 정보 가져오기
- 친구 목록 가져오기
- 나에게 메시지 발송
- 친구에게 메시지 발송
- 로그아웃
- 연결 끊기

## 프로젝트 구조
```
.
│
├── /src/resources/static/index.html # 메인 HTML 파일
└── /src/resources/static/style.css # 스타일시트
├── src/main/java/com/example/demo/controller/KakaoController.java # 컨트롤러 파일
├── src/main/java/com/example/demo/service/KakaoApiService.java # 서비스 파일
├── src/main/resources/application.yml # 설정 파일
└── README.md # 프로젝트 설명 파일
```

## 설치 방법

1. 프로젝트 클론
```bash
git clone [repository-url]
cd [project-directory]
```

2. 의존성 설치
```bash
gradle wrapper
./gradlew build       # macOS/Linux
gradlew.bat build     # Windows
```

3. 카카오 개발자 설정
- [Kakao Developers](https://developers.kakao.com)에서 애플리케이션 생성
- `application.properties`의 `client-id`와 `client-secret`을 발급받은 값으로 변경
- 카카오 개발자 콘솔의 "카카오 로그인 > 플랫폼 > Web 플랫폼"에서 사이트 도메인을 등록합니다.
- 카카오 개발자 콘솔의 "카카오 로그인 > 카카오 로그인 활성화"를 ON으로 설정합니다.
- Redirect URI 설정: `http://localhost:8080/api/kakao/redirect`

4. 서버 실행
```bash
./gradlew bootRun
```

## 사용 방법

1. 브라우저에서 `http://localhost:8080` 접속
2. 카카오 로그인 버튼 클릭
3. 각 기능 버튼을 통해 API 테스트

## 주의사항

- 카카오 로그인 Redirect URI가 정확히 설정되어 있어야 합니다.
- 친구 목록 조회와 메시지 발송을 위해서는 추가 동의가 필요합니다.

## 의존성

- Spring Boot: 3.2.3
- Spring Web
- Jackson

## 스크린샷
![image](https://github.com/user-attachments/assets/a64d2a83-c036-4cb2-88e5-07bba3890ec3)

