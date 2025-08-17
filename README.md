# 📱 BookPlay - Firebase Auth Android App

<div align="center">
  <img src="https://img.shields.io/badge/Android-3DDC84?style=for-the-badge&logo=android&logoColor=white" />
  <img src="https://img.shields.io/badge/Kotlin-0095D5?style=for-the-badge&logo=kotlin&logoColor=white" />
  <img src="https://img.shields.io/badge/Firebase-FFCA28?style=for-the-badge&logo=firebase&logoColor=black" />
  <img src="https://img.shields.io/badge/Material%20Design-757575?style=for-the-badge&logo=material-design&logoColor=white" />
</div>

## 📖 프로젝트 소개

**BookPlay**는 Firebase Authentication을 활용한 독서 관리 Android 앱입니다. Google 로그인을 통해 안전하고 편리한 사용자 인증을 제공합니다.

### ✨ 주요 기능

- 🔐 **Google OAuth 로그인/로그아웃**
- 👤 **사용자 프로필 정보 표시**
- 🎨 **모던한 Material Design 3 UI**
- 📱 **반응형 레이아웃 지원**
- 🔄 **로그인 상태 자동 유지**

## 🛠️ 기술 스택

- **언어**: Kotlin
- **UI**: Material Design 3, ViewBinding
- **인증**: Firebase Authentication
- **이미지 로딩**: Glide
- **최소 SDK**: API 24 (Android 7.0)
- **타겟 SDK**: API 36 (Android 14)

## 📋 프로젝트 구조

```
BookPlayTest/
├── app/
│   ├── src/main/
│   │   ├── java/com/blacktiger/bookplaytest/
│   │   │   └── MainActivity.kt                 # 메인 액티비티 (Firebase Auth 로직)
│   │   ├── res/
│   │   │   ├── layout/activity_main.xml        # UI 레이아웃
│   │   │   ├── values/strings.xml              # 문자열 리소스
│   │   │   ├── values/colors.xml               # 색상 리소스
│   │   │   ├── values/themes.xml               # Material Design 테마
│   │   │   └── drawable/                       # 아이콘 및 그라데이션 배경
│   │   └── AndroidManifest.xml                 # 앱 매니페스트
│   ├── build.gradle.kts                        # 앱 수준 빌드 설정
│   └── google-services.json                    # Firebase 설정 파일
├── gradle/libs.versions.toml                   # 의존성 버전 관리
└── build.gradle.kts                            # 프로젝트 수준 빌드 설정
```

## 🚀 빠른 시작

### 1️⃣ 프로젝트 클론

```bash
git clone https://github.com/your-username/BookPlayTest.git
cd BookPlayTest
```

### 2️⃣ Firebase 프로젝트 설정

1. **[Firebase Console](https://console.firebase.google.com/) 접속**
2. **새 프로젝트 생성** 또는 기존 프로젝트 선택
3. **프로젝트 개요** → **앱 추가** → **Android** 선택

### 3️⃣ Android 앱 등록

```
Android 패키지 이름: com.blacktiger.bookplaytest
앱 닉네임: BookPlay Android App
SHA-1 인증서 지문: [아래 명령어로 확인]
```

#### SHA-1 지문 확인 방법

**방법 1: Android Studio (권장)**
1. Android Studio에서 프로젝트 열기
2. 우측 **Gradle** 탭 → **app** → **Tasks** → **android** → **signingReport** 더블클릭
3. 결과에서 **Variant: debug**의 **SHA1** 값 복사

**방법 2: 명령줄**
```bash
# Windows
keytool -list -v -keystore "%USERPROFILE%\.android\debug.keystore" -alias androiddebugkey -storepass android -keypass android

# macOS/Linux  
keytool -list -v -keystore ~/.android/debug.keystore -alias androiddebugkey -storepass android -keypass android
```

### 4️⃣ google-services.json 파일 추가

1. Firebase Console에서 **google-services.json** 파일 다운로드
2. 파일을 **`app/`** 폴더에 저장
3. Android Studio에서 **Sync Project with Gradle Files**

> ⚠️ **보안 주의**: `google-services.json` 파일은 `.gitignore`에 포함되어 GitHub에 업로드되지 않습니다. 
> 프로젝트를 클론한 후 직접 Firebase Console에서 다운로드하여 추가해야 합니다.

### 5️⃣ Firebase Authentication 활성화

1. Firebase Console → **Authentication** → **시작하기**
2. **Sign-in method** → **Google** → **사용 설정** 활성화
3. **프로젝트 지원 이메일** 선택 → **저장**

### 6️⃣ 앱 빌드 및 실행

```bash
# 프로젝트 클린
./gradlew clean

# Debug 빌드
./gradlew assembleDebug

# 앱 설치 및 실행 (디바이스 연결 시)
./gradlew installDebug
```

**또는 Android Studio에서:**
- **Run** → **Run 'app'** (Shift + F10)

## 🎯 앱 사용법

### 로그인 화면
- 앱 시작 시 아름다운 그라데이션 배경의 로그인 화면
- **Google로 로그인** 버튼 클릭

### 사용자 정보 화면
- 로그인 성공 시 사용자 프로필 카드 표시
- 프로필 사진, 이름, 이메일, UID 정보 확인
- **로그아웃** 버튼으로 로그인 화면 복귀

## 🐛 문제 해결

### 일반적인 오류 및 해결방법

| 오류 메시지 | 원인 | 해결 방법 |
|-------------|------|-----------|
| `Default FirebaseApp is not initialized` | google-services.json 누락 | 파일을 app/ 폴더에 추가 |
| `DEVELOPER_ERROR` | SHA-1 지문 미등록 | Firebase Console에 SHA-1 등록 |
| `SIGN_IN_REQUIRED` | Google Play Services 문제 | Google APIs 에뮬레이터 사용 |
| `Network error` | 인터넷 연결 문제 | Wi-Fi 연결 확인 |

### 빌드 오류 시

```bash
# Gradle 캐시 클리어
./gradlew clean
./gradlew --refresh-dependencies

# Android Studio에서
File → Invalidate Caches and Restart
```

## 📱 테스트 환경

### 권장 환경
- **에뮬레이터**: Pixel 6 API 30+ (Google APIs 포함)
- **실제 기기**: Android 7.0 (API 24) 이상
- **네트워크**: 안정적인 인터넷 연결

### 테스트 시나리오
1. ✅ **앱 시작**: 로그인 화면 표시
2. ✅ **Google 로그인**: 계정 선택 팝업 표시
3. ✅ **로그인 완료**: 사용자 정보 화면 전환
4. ✅ **정보 표시**: 프로필 사진, 이름, 이메일, UID 표시
5. ✅ **로그아웃**: 로그인 화면 복귀
6. ✅ **상태 유지**: 앱 재시작 시 로그인 상태 유지

## 🔧 개발 환경

### 필수 요구사항
- **Android Studio**: Arctic Fox 이상
- **JDK**: 11 이상
- **Android SDK**: API 24 이상
- **Firebase 프로젝트**: Google Cloud Console 계정 필요

### 주요 의존성
```kotlin
// Firebase
implementation(platform("com.google.firebase:firebase-bom:33.7.0"))
implementation("com.google.firebase:firebase-auth-ktx")
implementation("com.google.firebase:firebase-analytics-ktx")

// Google Play Services
implementation("com.google.android.gms:play-services-auth:21.2.0")

// UI
implementation("de.hdodenhof:circleimageview:3.1.0")
implementation("com.github.bumptech.glide:glide:4.16.0")
```

## 🚀 다음 개발 계획

### Phase 1: 데이터 저장
- [ ] Firestore 연동
- [ ] 사용자 독서 기록 저장
- [ ] 오프라인 지원

### Phase 2: 도서 관리
- [ ] Google Books API 연동
- [ ] 도서 검색 기능
- [ ] 독서 목록 관리 (읽은 책, 읽고 싶은 책)

### Phase 3: 소셜 기능
- [ ] 독서 리뷰 및 평점 시스템
- [ ] 친구와 독서 기록 공유
- [ ] 독서 챌린지 기능

### Phase 4: 고급 기능
- [ ] 푸시 알림 (Firebase Cloud Messaging)
- [ ] 독서 통계 및 분석
- [ ] 다크 모드 지원

## 📞 지원 및 문서

- [Firebase Auth 공식 문서](https://firebase.google.com/docs/auth/android/google-signin)
- [Google Sign-In 가이드](https://developers.google.com/identity/sign-in/android)
- [Android 개발자 가이드](https://developer.android.com/guide)
- [Material Design 3](https://m3.material.io/)

## 📄 라이선스

이 프로젝트는 MIT 라이선스 하에 배포됩니다. 자세한 내용은 [LICENSE](LICENSE) 파일을 참조하세요.

## 🔗 **GitHub 업로드 방법**

### 1️⃣ GitHub 저장소 생성
1. [GitHub](https://github.com/) 접속 → **New repository**
2. **Repository name**: `BookPlayTest`
3. **Description**: `Firebase Auth를 활용한 Android 독서 관리 앱`
4. **Public** 또는 **Private** 선택
5. ⚠️ **"Add a README file"** 체크 해제 (이미 있음)

### 2️⃣ 로컬과 GitHub 연결
```bash
# GitHub 저장소와 연결 (your-username을 실제 사용자명으로 변경)
git remote add origin https://github.com/your-username/BookPlayTest.git

# 기본 브랜치를 main으로 설정
git branch -M main

# GitHub에 푸시
git push -u origin main
```

### 3️⃣ 협업자를 위한 안내
프로젝트를 클론한 다른 개발자는:
1. 저장소 클론 후
2. **Firebase Console에서 `google-services.json` 직접 다운로드**
3. **`app/` 폴더에 파일 추가**
4. 앱 빌드 및 실행

## 🤝 기여하기

1. Fork the Project
2. Create your Feature Branch (`git checkout -b feature/AmazingFeature`)
3. Commit your Changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the Branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

## 📧 연락처

프로젝트에 대한 질문이나 제안사항이 있으시면 이슈를 생성해 주세요.

---

<div align="center">
  <strong>Firebase Auth를 활용한 안전하고 현대적인 Android 앱 🔥</strong>
</div>
