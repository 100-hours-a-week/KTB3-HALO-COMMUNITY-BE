# 🚀 Our Universe

## Back-end 소개

- 우주에 대해 이야기를 나누고 싶은 사용자에게 다양한 서비스를 제공하기 위한 `우주에 대해 소통하는 커뮤니티` 프로젝트입니다.
- 여러 기능이 들어갈 예정이기 때문에 프로젝트 특성상 확장이 용이하고 의존성 관리가 쉬운 그리고 MVP 웹사이트를 빠르게 구축할 수 있는  `Spring Boot` 프레임워크를 사용하여 구현했습니다.


## 프로젝트 컨셉
- <a href="https://github.com/100-hours-a-week/KTB3-HALO-COMMUNITY-FE">Our Universe Concept v1</a>




### 개발 인원 및 기간

- 개발기간 :  2024-10-03 ~ 2024-12-07
- 개발 인원 : 프론트엔드/백엔드 1명 (본인)

### 사용 기술 및 tools
- `Spring Boot`
- `JPA`, `lombok` : 개발 편의성 향상 DB 쿼리 메서드 자동화
- `Github Actions` :  CI/CD를 사용하여 프론트와 협업 그리고 서비스 배포시 개발 및 배포 속도를 향상
- `AWS`
  - `Auto Scaling Group(ASG)` : 트래픽 증가시 서버를 자동으로 추가하게 하여 트래픽별로 적절한 서버 비용을 사용하게 하였습니다.
  - `Application Load Balancer(ALB)` : ASG에 들어가는 트래픽들을 고르게 분산시키고 트래픽을 측정정해주기 위하여 사용하였습니다.
  - `Route 53` : 구매한 도메인을 ALB와 연결해주고 헬스체크를 하는 용도로 사용하였씁니다.
  - 'API Gateway` : 서버를 거치지 않아도 되는 ServerLess 리소스들에 대한 요청을 따로 가로채기 위해 사용하였습니다.
  - `Lamdba` : 사용자가 이미지 여러개를 동시에 저장할 수도 있는, 일반 API 보다 많은 요청,들은 Lamdba를 사용하여 서버에 부담이 되지 않기 하기 위해 사용하였습니다.
  - `S3` : 이미지같은 대용량 파일을 DB에 저장하면 비용적으로 부담이 되어 AWS에서 제공해주는 S3에 저장하였습니다.
  - `VPC` : 추후 백엔드 및 RDS같은 공개되면 위험한 서버들을 프라이빗 서브넷에 저장하기 위해, 가상 사설 네트워크 망을 구축하려고 해당 리소르를 사용하였습니다.
  - `RDS` : DB에 대한 관리 부담을 줄여주고 CloudWatch로 DB 상태를 실시간으로 관찰하기 위하여 사용하였습니다.
- `Docker` : 서로 다른 운영체제 및 환경에서 해당 서비스를 배포하기 위해 그리고 CI/CD에 테스트를 돌릴 때 매번 같은 환경을 유지하기 위해 사용하였습니다.
- `Docker Compse` : docker 실행 시, 예민한 값들이 들어있는 env파일을 참조하고 추후에 하나의 서버에서 여러 컨테이너들을 동시에 띄울 때를 위해 사용하였습니다.
- `Github Actions` : 컨테이너의 환경 안에서 테스트, 빌드 그리고 배포를 자동화하여 개발 편의성과 속도를 향상시키려고 사용하였습니다

### 서비스 시연 연상
- 

### Front-end
- <a href="https://github.com/100-hours-a-week/KTB3-HALO-COMMUNITY-FE">Front-end Github</a>

### AWS Cloud 아키텍쳐
<img width="812" height="1238" alt="image" src="https://github.com/user-attachments/assets/8f74d0f3-9e1b-4719-bb83-cac7a39a86da" />



### 폴더 구조
<details>
  <summary>폴더 구조 보기/숨기기</summary>
  <div markdown="1">

    KTB3-HALO-COMMUNITY-FE/
    ├── app.js
    ├── package.json
    ├── package-lock.json
    ├── openapi.json
    ├── Dockerfile
    ├── .github/
    │   └── workflows/
    │       └── deploy.yml
    ├── routes/
    │   ├── index.js
    │   ├── authRoutes.js
    │   ├── healthCheck.js
    │   ├── onboardingRoutes.js
    │   ├── postRoutes.js
    │   └── userRoutes.js
    ├── utils/
    │   └── path.js
    └── public/
        ├── config.js
        ├── page_path.js
        ├── component/
        │   ├── auth/
        │   │   ├── footer/
        │   │   │   ├── footer_inner.css
        │   │   │   └── footer_inner.js
        │   │   ├── gladbanner/
        │   │   │   ├── gladbanne.css
        │   │   │   └── gladbanner.js
        │   │   ├── header/
        │   │   │   ├── header_inner.css
        │   │   │   └── header_inner.js
        │   │   ├── login_wrap/
        │   │   │   ├── find_wrap.css
        │   │   │   ├── find_wrap.js
        │   │   │   ├── login_wrap.css
        │   │   │   └── login_wrap.js
        │   │   └── signup_wrap/
        │   │       ├── signup_wrap.css
        │   │       └── signup_wrap.js
        │   ├── common/
        │   │   ├── alert/
        │   │   │   ├── alert.css
        │   │   │   └── alert.js
        │   │   ├── footer/
        │   │   │   ├── footer_inner.css
        │   │   │   └── footer_inner.js
        │   │   ├── header/
        │   │   │   └── navigator/
        │   │   │       ├── bindNavigatorEvents.js
        │   │   │       ├── navigator.css
        │   │   │       └── navigator.js
        │   │   └── toast/
        │   │       ├── toast.css
        │   │       └── toast.js
        │   ├── onboarding/
        │   │   ├── category_card/
        │   │   │   └── category_card.js
        │   │   ├── cosmic_background/
        │   │   │   └── cosmic_background.js
        │   │   ├── manual_section/
        │   │   │   └── manual_section.js
        │   │   ├── onboarding.css
        │   │   └── onboarding.js
        │   ├── post/
        │   │   ├── category_wrap/
        │   │   │   ├── category_wrap.css
        │   │   │   └── category_wrap.js
        │   │   ├── onboarding/
        │   │   ├── post_detail/
        │   │   │   ├── article_wrap/
        │   │   │   │   ├── article_wrap.css
        │   │   │   │   └── article_wrap.js
        │   │   │   ├── comment_wrap/
        │   │   │   │   ├── comment_wrap.css
        │   │   │   │   └── comment_wrap.js
        │   │   │   ├── stats_wrap/
        │   │   │   │   ├── stats_wrap.css
        │   │   │   │   └── stats_wrap.js
        │   │   │   └── user_info_wrap/
        │   │   │       ├── user_info_wrap.css
        │   │   │       └── user_info_wrap.js
        │   │   ├── post_modify/
        │   │   │   ├── article_wrap/
        │   │   │   │   ├── article_wrap.css
        │   │   │   │   └── article_wrap.js
        │   │   │   ├── image_wrap/
        │   │   │   │   ├── image_wrap.css
        │   │   │   │   └── image_wrap.js
        │   │   │   ├── info_wrap/
        │   │   │   │   ├── info_wrap.css
        │   │   │   │   └── info_wrap.js
        │   │   │   ├── modify_button_wrap/
        │   │   │   │   ├── modify_button_wrap.css
        │   │   │   │   └── modify_button_wrap.js
        │   │   │   └── title_wrap/
        │   │   │       ├── title_wrap.css
        │   │   │       └── title_wrap.js
        │   │   └── post_wrap/
        │   │       ├── post_item/
        │   │       │   ├── post_item_skeleton.css
        │   │       │   ├── post_item_skeleton.js
        │   │       │   ├── post_item.css
        │   │       │   └── post_item.js
        │   │       ├── post_wrap_handlers.js
        │   │       ├── post_wrap.css
        │   │       └── post_wrap.js
        │   └── user/
        │       ├── account/
        │       │   └── account_wrap/
        │       │       ├── account_wrap.css
        │       │       └── account_wrap.js
        │       └── password/
        │           └── password_wrap/
        │               ├── password_wrap.css
        │               └── password_wrap.js
        ├── pages/
        │   ├── auth/
        │   │   ├── login/
        │   │   │   ├── login.css
        │   │   │   ├── login.html
        │   │   │   └── login.js
        │   │   └── signup/
        │   │       ├── signup.css
        │   │       ├── signup.html
        │   │       └── signup.js
        │   ├── onboarding/
        │   │   ├── onboarding.css
        │   │   ├── onboarding.html
        │   │   └── onboarding.js
        │   ├── post/
        │   │   ├── post_create/
        │   │   │   ├── post_create.css
        │   │   │   ├── post_create.html
        │   │   │   └── post_create.js
        │   │   ├── post_detail/
        │   │   │   ├── post_detail.css
        │   │   │   ├── post_detail.html
        │   │   │   └── post_detail.js
        │   │   ├── post_modify/
        │   │   │   ├── post_modify.css
        │   │   │   ├── post_modify.html
        │   │   │   └── post_modify.js
        │   │   └── postList/
        │   │       ├── postList.css
        │   │       ├── postList.html
        │   │       └── postList.js
        │   └── user/
        │       ├── account/
        │       │   ├── account.css
        │       │   ├── account.html
        │       │   └── account.js
        │       └── password/
        │           ├── password.css
        │           ├── password.html
        │           └── password.js
        ├── service/
        │   ├── auth/
        │   │   ├── login/
        │   │   │   └── addLoginEvent.js
        │   │   ├── logout/
        │   │   │   └── addLogoutEvent.js
        │   │   ├── refresh/
        │   │   │   └── refresh_service.js
        │   │   └── signup/
        │   │       └── addSignupEvent.js
        │   ├── comment/
        │   │   ├── add_comment_event.js
        │   │   └── comment_service.js
        │   ├── onboarding/
        │   │   ├── add_onboarding_event.js
        │   │   └── onboarding_service.js
        │   ├── post/
        │   │   ├── onboarding/
        │   │   ├── post_create/
        │   │   │   └── add_post_create_event.js
        │   │   ├── post_delete/
        │   │   │   └── add_post_delete_event.js
        │   │   ├── post_detail/
        │   │   │   ├── add_post_detail_event.js
        │   │   │   └── post_like/
        │   │   │       └── add_post_detail_like_event.js
        │   │   ├── post_list/
        │   │   │   └── add_post_list_event.js
        │   │   └── post_update/
        │   │       └── add_post_update_event.js
        │   └── user/
        │       ├── password/
        │       │   └── addPasswordChangeEvent.js
        │       └── profile/
        │           ├── add_navigator_profile_image.js
        │           ├── add_profile_change_event.js
        │           ├── add_profile_delete_event.js
        │           ├── add_profile_image_change_event.js
        │           └── add_profile_load_event.js
        ├── styles/
        │   ├── alert.css
        │   └── global.css
        └── utils/
            ├── apiClient.js
            ├── confirmDialog.js
            ├── eventHandlers.js
            ├── fetchWithAuth.js
            ├── iconSvgs.js
            ├── imageConstants.js
            ├── imagePreview.js
            ├── initApiClient.js
            ├── jwt.js
            ├── scrollObserver.js
            ├── showAlert.js
            ├── skeletonConfig.js
            ├── textUtils.js
            └── toast.js
        
  </div>
  </details>
  <br/>

## 서비스 화면

> 💡 이미지를 클릭하시면 보시기 편하실겁니다!

`홈`
|로그인|회원가입|
|---|---|
|![image](https://github.com/user-attachments/assets/b36773fa-a777-4994-a808-c78919bac76f)|![image](https://github.com/user-attachments/assets/3853709e-fe2b-4a34-b46d-8bb3edc176a2)|












`전체 게시글 / 게시물 작성 / 상세 / 수정 / 삭제`

|전체 게시글|게시물 작성|게시물 상세|게시글 수정|게시글 삭제|
|---|---|---|---|---|
|![image](https://github.com/user-attachments/assets/4e175003-5e3e-47af-8dbe-a96573093444)|![image](https://github.com/user-attachments/assets/ac7070c0-4ab0-4fea-8b61-cefb0b2a7c10)|![image](https://github.com/user-attachments/assets/83e4f3d9-d8c1-4b2a-9e22-948058d1efce)|![image](https://github.com/user-attachments/assets/1b58c704-fc55-4f9f-a54c-689190665f88)|![image](https://not-me-be.s3.ap-northeast-2.amazonaws.com/our_universe/pages_intro/KakaoTalk_Photo_2025-12-07-11-53-51.png)|






`댓글 목록 / 등록 / 수정 /삭제`

|댓글 화면|댓글 등록|댓글 수정|댓글 삭제|
|---|---|---|---|
|![image](https://github.com/user-attachments/assets/ca9cc958-bf49-425b-be8b-81e1f6aea1b3)|![image](https://not-me-be.s3.ap-northeast-2.amazonaws.com/our_universe/pages_intro/%E1%84%89%E1%85%B3%E1%84%8F%E1%85%B3%E1%84%85%E1%85%B5%E1%86%AB%E1%84%89%E1%85%A3%E1%86%BA+2025-12-07+%E1%84%8B%E1%85%A9%E1%84%92%E1%85%AE+12.03.36.png)|![image](https://not-me-be.s3.ap-northeast-2.amazonaws.com/our_universe/pages_intro/%E1%84%89%E1%85%B3%E1%84%8F%E1%85%B3%E1%84%85%E1%85%B5%E1%86%AB%E1%84%89%E1%85%A3%E1%86%BA+2025-12-07+%E1%84%8B%E1%85%A9%E1%84%8C%E1%85%A5%E1%86%AB+11.59.38.png)|![image](https://not-me-be.s3.ap-northeast-2.amazonaws.com/our_universe/pages_intro/%E1%84%89%E1%85%B3%E1%84%8F%E1%85%B3%E1%84%85%E1%85%B5%E1%86%AB%E1%84%89%E1%85%A3%E1%86%BA+2025-12-07+%E1%84%8B%E1%85%A9%E1%84%8C%E1%85%A5%E1%86%AB+11.59.27.png)|

  
`프로필 수정 / 비밀번호 수정 / 회원 탈퇴 / 로그아웃`





|프로필 수정|비밀번호 수정|회원 탈퇴|로그아웃|
|---|---|---|---|
|![image](https://github.com/user-attachments/assets/2f038f23-5cb7-4c73-a913-8978be381a1f)|![image](https://github.com/user-attachments/assets/d2fb3211-d21c-4731-a5f0-b2cf5291bc3c)|![image](https://not-me-be.s3.ap-northeast-2.amazonaws.com/our_universe/pages_intro/%E1%84%89%E1%85%B3%E1%84%8F%E1%85%B3%E1%84%85%E1%85%B5%E1%86%AB%E1%84%89%E1%85%A3%E1%86%BA+2025-12-07+%E1%84%8B%E1%85%A9%E1%84%92%E1%85%AE+12.07.51.png)|![image](https://not-me-be.s3.ap-northeast-2.amazonaws.com/our_universe/pages_intro/%E1%84%89%E1%85%B3%E1%84%8F%E1%85%B3%E1%84%85%E1%85%B5%E1%86%AB%E1%84%89%E1%85%A3%E1%86%BA+2025-12-07+%E1%84%8B%E1%85%A9%E1%84%92%E1%85%AE+12.07.05.png)|

<br/>

## 트러블 슈팅
- https://github.com/100-hours-a-week/KTB3-HALO-COMMUNITY-FE/issues/18


<br/>

## 프로젝트 후기
현재 데이터센터를 우주에 띄울만큼 글로벌 시장에서 우주에 대한 관심도가 높아짐에 따라 해당 도메인에 대한 커뮤니티 유입도 활발해질 것으로 예상하였다.

글로벌 시장에서는 `Cloudy Nights` 같은 천문학 커뮤니티가 존재하지만 현재 대한민국의 커뮤니티 중, 우주라는 도메인을 전문적으로 하는 커뮤니티가 없다는 점을 고려하였다.

추가적으로 사용자의 접속을 많이 유도할  `랜덤 행성 방문 서비스`를 준비중이다. 해당 서비스는 마치 달에 처음 착륙하여 깃발을 꽂는 것처럼 사용자에게 미지의 행성을 방문하고 방명록을 남기며 다른 이들의 행적을 함께 볼 수 있는 경험을 제공한다.

뿐만 아니라 로그인 페이지에 광고를 넣어 추후 서버를 돌리는데 비용적으로 도움이 될 수단을 추가하였다.

UI 디자인 측면에서 페이지들의 배경은 우주라는 광활함과 아름다움을 표현하기 위해 우주복을 입은 캐릭터가 색깔이 다양한 , 우주를 마치 유영하고 있는, 해파리를 들고 있는 이미지로 선정하였다. (해당 이미지를 사업적으로 사용 가능한지 확인 예정)

### 결론적으로, 해당 커뮤니티 사이트는 우주에 대해 이야기를 다른 사람과 나누고 공유하고 싶은 사람들을 대상으로 만들어진 웹 애플리케이션이다.



<br/>
<br/>
<br/>



