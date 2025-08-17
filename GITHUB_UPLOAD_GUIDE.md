# 🚀 GitHub 업로드 가이드

## ✅ **완료된 작업**

- [x] Git 초기화 완료
- [x] .gitignore 파일 생성 (Android 프로젝트용)
- [x] 통합 README.md 생성
- [x] 불필요한 MD 파일들 삭제
- [x] 첫 커밋 완료

## 📂 **현재 프로젝트 상태**

```
BookPlayTest/
├── .git/                    ✅ Git 저장소 초기화 완료
├── .gitignore              ✅ Android 전용 gitignore
├── README.md               ✅ 통합 문서 (모든 가이드 포함)
├── app/
│   ├── google-services.json ⚠️ Firebase 설정 파일 (이미 추가됨)
│   └── ...                 ✅ 모든 앱 코드 완료
└── ...                     ✅ Gradle 설정 완료
```

## 🔗 **GitHub에 업로드하는 방법**

### 1️⃣ GitHub에서 새 저장소 생성

1. **[GitHub](https://github.com/) 접속 및 로그인**
2. **"New repository"** 클릭 (또는 `+` → `New repository`)
3. **저장소 정보 입력:**
   ```
   Repository name: BookPlayTest
   Description: Firebase Auth를 활용한 Android 독서 관리 앱
   Visibility: Public (또는 Private)
   ⚠️ "Add a README file" 체크 해제 (이미 있음)
   ⚠️ "Add .gitignore" 체크 해제 (이미 있음)
   ```
4. **"Create repository"** 클릭

### 2️⃣ 로컬 저장소와 GitHub 연결

```bash
# GitHub 저장소와 연결 (your-username을 실제 GitHub 사용자명으로 변경)
git remote add origin https://github.com/your-username/BookPlayTest.git

# 기본 브랜치를 main으로 설정
git branch -M main

# GitHub에 푸시
git push -u origin main
```

### 3️⃣ 완료 확인

GitHub 저장소 페이지에서 다음 사항 확인:
- [ ] README.md가 정상 표시되는지
- [ ] 모든 소스 코드가 업로드되었는지  
- [ ] .gitignore가 적용되어 build/ 폴더 등이 제외되었는지

## 🔒 **보안 주의사항**

### ⚠️ google-services.json 파일 관리

**현재 상황**: `google-services.json` 파일이 커밋에 포함됨

**선택 사항:**

#### 옵션 1: 공개 저장소인 경우 (권장)
```bash
# google-services.json을 .gitignore에 추가
echo "google-services.json" >> .gitignore

# 파일을 Git에서 제거 (로컬에는 유지)
git rm --cached app/google-services.json

# 변경사항 커밋
git add .gitignore
git commit -m "🔒 Add google-services.json to .gitignore for security"
git push
```

#### 옵션 2: 비공개 저장소인 경우
- 현재 상태 유지 (google-services.json 포함)
- 팀원들이 바로 빌드할 수 있음

## 📋 **다음 작업 (선택사항)**

### 저장소 설정
- [ ] **About** 섹션에 프로젝트 설명 추가
- [ ] **Topics** 추가: `android`, `kotlin`, `firebase`, `authentication`
- [ ] **Issues** 탭 활성화 (버그 리포트용)
- [ ] **Discussions** 탭 활성화 (커뮤니티용)

### 추가 파일
- [ ] `LICENSE` 파일 추가 (MIT License 권장)
- [ ] `CONTRIBUTING.md` 파일 추가 (기여 가이드)
- [ ] GitHub Actions 워크플로우 추가 (CI/CD)

## 🎉 **완료!**

이제 GitHub에서 프로젝트를 공유하고 협업할 수 있습니다!

**저장소 URL**: `https://github.com/your-username/BookPlayTest`
