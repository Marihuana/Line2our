# Line2our (가제)
지하철을 중심으로 하는 서울 여행 정보 제공 
---
## Branch 규칙

- 브랜치 종류  
  - master: 메인 유지관리 브랜치 릴리즈 시 태깅추가, 운영환경      
  - develop: 실행 가능한, 에러 없는 최신 소스, 개발자 공유    
  - feature/xxx : 개발 항목별 또는 최소 작업 단위    

- 브랜치 [그래프][1] 예) 
  
  ```
  -  master --------------------(pull request & taging)
         \                            /  
	  develop ------------------(merge)-----
            \                       /     
             --- feartures(feature/login)       
                                               
  ```

---

### Package 구조
- 도메인 중심으로 생성
```
└──   com
       └──   four
           └──   hackerton
                    └──   line2our
                              ├──   feature
                              │   ├──   common
                              │   │   └──   BaseActivity.kt
                              │   └──   main
                              │       ├──   MainActivity.kt
                              │       └──   MainViewModel.kt
                              ├──   gobal
                              │       └── RemoteConfig.kt
                              ├──   model
                              │       ├──   database
                              │       └──   network
                              │               ├──   model
                              │               │       └── SearchResult.kt
                              │               ├──   repository
                              │               │       └── TestRepository.kt
                              │               └──   service
                              │                       └── TestService.kt
                              └──   util
```
---

### API 명세

slack 테스트

                              │               │       └── SearchResult.kt
