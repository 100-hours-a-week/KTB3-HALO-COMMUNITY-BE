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
- `AWS` : Auto Scaling Group, Application Load Balancer, Route 53, API Gateway, Lamdba, RDS, VPC, Lamdba
- `Docker` : 서로 다른 운영체제 및 환경에서 해당 서비스를 배포하기 위해 그리고 CI/CD에 테스트를 돌릴 때 매번 같은 환경을 유지하기 위해 사용하였습니다.
- `Docker Compse` : docker 실행 시, 예민한 값들이 들어있는 env파일을 참조하고 추후에 하나의 서버에서 여러 컨테이너들을 동시에 띄울 때를 위해 사용하였습니다.
- `Github Actions` : 컨테이너의 환경 안에서 테스트, 빌드 그리고 배포를 자동화하여 개발 편의성과 속도를 향상시키려고 사용하였습니다

### 서비스 시연 연상


### Front-end
- <a href="https://github.com/100-hours-a-week/KTB3-HALO-COMMUNITY-FE">Front-end Github</a>


---
## 서버 설계
### 1. 서버 구조(3 레이어드 아키텍처 구조)
`Auth`

|           | Controller | Service | Repository |
|-----------|-----------|---------|------------|
| login     | LoginController | LoginService | UserRepository |
| logout    | LogoutController | LogoutService | UserRepository |
| signup    | SignUpController | SignUpService | UserRepository |
| refresh   | TokenRefreshController | TokenRefreshService | UserRepository |

`Comment`

|           | Controller | Service | Repository |
|-----------|-----------|---------|------------|
| CRUD      | CommentController | CommentService | CommentRepository |

`Post`

|           | Controller | Service | Repository |
|-----------|-----------|---------|------------|
| CRUD      | PostController | PostService | PostRepository |
| like      | PostLikeController | PostLikeService | PostLikeRepository |

`User`

|           | Controller | Service | Repository |
|-----------|-----------|---------|------------|
| CRUD      | UserController | UserService | UserRepository |
| password  | PasswordController | PassWordService | UserRepository |
| profile   | ProfileController | UserService | UserRepository |


### 2. 구현 기능
### Auth
```
- Spring Security가 AuthenticationProvider에 Bcrypt 단방향 암호화를 사용하게 구현
  - Bcrypt의 안전하지만 암호화가 느린 Blowfish를 사용하여 빠르게 로그인이 되지 않게 함으로서 로그인 무차별 대입 공격 대비
- 서버의 로그인 정보 저장 메모리 부담을 줄이기 위하여 Jwt 토큰 사용
- 로그인 유지 기간을 도메인별 쿠키(리프레시 토큰)에 저장하여 인가에 사용되는 액세스 토큰 만료 시, 리프레시 토큰을 사용하여 서버에 액세스 토큰 재 발급 요청하는 로직 구현
```

#### Users
```
- 사용자가 서비스 탈퇴 후 복구 할 수 있게 유저 소프트 삭제 구현 
- 소프트 삭제 후, 1달 이후 DB에 필요없는 데이터를 삭제하기 위하여  하드 삭제 구현
- 비밀번호 하드 코딩을 방지 하기 위해 회원가입, 로그인, 비밀번호 변경 시 bcrypt로 비밀번호 암호화하여 처리
- User CRUD 구현
```

#### Posts
```
- 프론트가 협업시 하나의 API로 좋아요 및 좋아요 취소 요청 보내도록 게시글 좋아요 토글 형식 기능 구현
- 로그인한 사용자만 게시글을 작성할 수 있게 유효한 액세스 토큰을 사용하여 게시글 작성할 수 있게 구현
- 게시글 CRUD 기능 구현
```

#### Comments
```
- 다른 사용자의 댓글 삭제 및 수정을 방지하기 위해, 로그인한 사용자와 게시글 작성자의 ID가 같은 경우메나 해당 댓글을 수정 및 삭제할 수 있게 구현
- 댓글 CRUD 기능 구현
```

#### Common
```
- 게시글 삭제, 댓글 등록 그리고 유저 검색 등 비즈니스 로직에서 일어날 수 있는 별도의 예외처리 핸들러 구현
  - 비즈니스 로직 예외 인터페이스를 기반으로 각 도메인별 예외 구현체를 작성하였다.
- API 성공 및 실패 응답 코드들을 도메인 별로 분류하여 관리해야하는 관심사를 분리하였다.
- API를 RestFul하게 구현하여 리소스의 형태로 URL 그리고 행위로 메서드를 구현함으로써 개발 및 협업에 용이하게 구현
```

<br/>

---

## 데이터베이스 설계
### 1. 요구사항 분석

`인증`
- 사용자는 이메일, 닉네임 그리고 비밀번호를 사용하여 회원가입
- 사용자는 이메일과 비밀번호를 사용하여 로그인
- 사용자는 현재 비밀번호를 알고 있는 상태에서만 비밀번호 변경 가능
- 사용자의 계정 탈퇴

`유저 관리`
- 사용자는 이메일, 프로필 이미지, 비밀번호, 닉네임 정보를 포함하는 프로필 정보 및 수정 가능
- 각 유저는 고유한 식별자를 가지고 있으며, 이메일과 닉네임은 유니크하게 설정하여 중복 방지

`게시글 관리`
- 사용자가 제목, 내용, 이미지, 작성일시, 수정일시 등의 정보를 포함하는 게시글 관리
- 게시글은 작성자를 참조하여 관계를 설정
- 사용자의 게시글 좋아요 정보를 별도의 Table로 분리

`댓글 관리`
- 사용자가 내용, 작성자, 작성일시 등의 정보를 포함하는 댓글 관리
- 댓글은 어떤 게시글에 속해 있는지 나타내는 참조 포함



### 2. 모델링
`E-R Diagram`  
<img src="https://not-me-be.s3.ap-northeast-2.amazonaws.com/our_universe/ERD_IMAGE/%E1%84%89%E1%85%B3%E1%84%8F%E1%85%B3%E1%84%85%E1%85%B5%E1%86%AB%E1%84%89%E1%85%A3%E1%86%BA+2025-12-07+%E1%84%8B%E1%85%A9%E1%84%92%E1%85%AE+3.43.26.png" width="70%" />
> https://www.erdcloud.com/d/H99Loa24Gz6eWB8LM

요구사항을 기반으로 모델링한 E-R Diagram입니다.  

<br/>

<br/>


## 인프라 설계2
### 1. AWS 아키텍처
<img width="400" height="600" alt="image" src="https://not-me-be.s3.ap-northeast-2.amazonaws.com/our_universe/cloud/image+(2).webp" />
<br/>
추후 백엔드 서버와 DB를 프라이빗 서버에 배치하기 위해 내부 사설망인 VPC를 구축하고 안정적인 서버를 위한 트래픽 별로 서버의 개수를 변하게 할 수 있는 Auto Scaling Group기반 Application Load Balancer 사용하였다.

<br>

그리고 Docker Compose와 DockerFile을 활용하여 컨테이너 생성 및 소멸 스크립트를 env 파일과 함께 실행하여 여러 컨테이너를 관리하는 도커파일에 대한 책임을 compose로 분리하였다.
<br/>

- `Auto Scaling Group(ASG)` : 트래픽 증가시 서버를 자동으로 추가하게 하여 트래픽별로 적절한 서버 비용을 사용하게 하였습니다.
- `Application Load Balancer(ALB)` : ASG에 들어가는 트래픽들을 고르게 분산시키고 트래픽을 측정정해주기 위하여 사용하였습니다.
- `Route 53` : 구매한 도메인을 ALB와 연결해주고 헬스체크를 하는 용도로 사용하였씁니다.
- `API Gateway` : 서버를 거치지 않아도 되는 ServerLess 리소스들에 대한 요청을 따로 가로채기 위해 사용하였습니다.
- `Lamdba` : 사용자가 이미지 여러개를 동시에 저장할 수도 있는, 일반 API 보다 많은 요청,들은 Lamdba를 사용하여 서버에 부담이 되지 않기 하기 위해 사용하였습니다.
- `S3` : 이미지같은 대용량 파일을 DB에 저장하면 비용적으로 부담이 되어 AWS에서 제공해주는 S3에 저장하였습니다.
- `VPC` : 추후 백엔드 및 RDS같은 공개되면 위험한 서버들을 프라이빗 서브넷에 저장하기 위해, 가상 사설 네트워크 망을 구축하려고 해당 리소르를 사용하였습니다.
- `RDS` : DB에 대한 관리 부담을 줄여주고 CloudWatch로 DB 상태를 실시간으로 관찰하기 위하여 사용하였습니다.


## 트러블 슈팅

1. 커스텀 시큐리티 구현시 스레드 로컬이 필요했던 문제
각 요청 인증 정보를 저장하기 위해  스레드 로컬을 구현할 필요성이 있었기 때문에`ThreadLocal` 클래스를 사용하여 각 스레드에 대한 인증정보를 저장하고 불러오는 `CustomSecurityContextHolder`를 구현하여 해결하였다. 

<br/>

## 프로젝트 후기

- 인증 및 인가
1. 스프링 프레임워크에서 내부적으로 Proivder를 선택할 때, argon2, bcrypt 그리고 pbkdf2 같은 암호화 알고리즘들이 Default로 들어가는 것을 코드상으로 확인할 수 있는 좋은 경험이였다.
2.  JWT는 블랙리스트를 구현하기 위해 서버에 메모리를 일정부분 할당하여 사용한다는 것을 제외하고는 서버의 직접적인 요청을 보내지 않지만 그 서버가 개입하지 않는, 즉 jwt 액세스 토큰이 살아있는 기간 동안 해킹을 당하면 서버가 바로 조치할 수 있는 방법이 없다는 것을 알게 되었고 완벽한 보안은 존재하지 않는다라는 것이 씁쓸하게 느껴졌었다.
<br>
<br>

 - RestFull API
<br>
 1. URL에 리소스를 그리고 메서드에 행위를 적고 이것을 직관적으로 이해하게 함으로써 해당 컨텍스트를 이해하는데 드는 시간을 줄임으로서 개발을 용이하게 도움을 줄 수 있다는 것을 알게 되었습니다.
 


<br/>
<br/>
<br/>

