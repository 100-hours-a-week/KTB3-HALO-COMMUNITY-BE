# 🚀 Our Space

## Front-end 소개

- `우주에 대해 소통하는 커뮤니티` 프로젝트입니다.
- `express.js` 프레임워크를 사용하여 구현했습니다.
- 개발은 초기 프로젝트 화면부터, 기능, 백엔드 연결까지 `직접 구현`했습니다.

## 프로젝트 컨셉
- https://github.com/100-hours-a-week/KTB3-HALO-COMMUNITY-FE/issues/1

### 개발 인원 및 기간

- 개발기간 :  2024-10-03 ~ 2024-12-07
- 개발 인원 : 프론트엔드/백엔드 1명 (본인)

### 사용 기술 및 tools
- Express.js

### 서비스 시연 연상
- 

### Back-end
- <a href="https://github.com/100-hours-a-week/KTB3-HALO-COMMUNITY-BE">Back-end Github</a>

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



