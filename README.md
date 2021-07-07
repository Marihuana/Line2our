# Line2our (가제)
지하철을 중심으로 하는 서울 여행 정보 제공 
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