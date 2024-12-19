# TaskWise: To Do & Calender API
---

### 핵심 기능
- 사용자가 **To Do**를 등록하고,
- AI가 그날 일정 수행을 돕기 위해 **필요한 준비사항**을 알려주는 앱입니다.

### 목적
- **하루의 생산성**을 높이고,
- 사용자가 할 일을 **효과적으로 수행**할 수 있도록 AI 기반의 가이드를 제공합니다.


# 2. 소스 빌드 및 실행 메뉴얼

## 3. 라이브러리 
| 라이브러리              | version        | 라이브러리 주요 용도              | 비고 |
|-------------------------|----------------|----------------------------------|------|
| spring-boot-data-jpa    | -              | JPA를 활용한 데이터베이스 처리     |      |
| spring-boot-web         | -              | 웹 애플리케이션 개발              |      |
| lombok                  | -              | 코드 간소화를 위한 애노테이션 제공 |      |
| mysql-connector-j       | -              | MySQL 데이터베이스 연결           |      |
| spring-boot-test        | -              | 테스트 관련 기능 제공             |      |
| junit-platform-launcher | -              | JUnit 플랫폼 테스트 실행 지원      |      |
| springdoc-openapi-ui    | 2.3.0          | Swagger를 사용한 OpenAPI 문서화    |      |
| openai-api              | 0.18.2         | OpenAI GPT API 사용              |      |
| openai-client           | 0.18.2         | OpenAI GPT API 클라이언트 구현     |      |
| json                    | 20240303       | JSON 데이터 처리                 |      |
| spring-boot-redis       | -              | Redis와의 데이터 처리 지원         |      |
| spring-boot-cache       | -              | 캐시 기능 지원                   |      |
| jackson-databind        | -              | JSON 데이터 바인딩               |      |
| jackson-jsr310          | -              | Java 8 날짜와 시간 객체 지원       |      |



## 4. API 명세서

| **기능**                        | **API**                           | **Parameter**                              | **Body**                                                                                      | **Response**                                                                                         |
|---------------------------------|-----------------------------------|-------------------------------------------|----------------------------------------------------------------------------------------------|------------------------------------------------------------------------------------------------------|
| **특정 To Do의 AI 응답 조회**    | GET `/api/answers/{toDoId}`       | `toDoId` (PathVariable, Long)             | 없음                                                                                         | `List<AnswerResponseDto>`                                                                            |
| **프롬프트 생성 및 AI 응답 저장**| POST `/api/answers/generate`      | 없음                                       | `{ "date": "2024-12-20" }`                                                                   | `{ "message": "응답이 성공적으로 저장되었습니다." }`                                                  |
| **To Do 작성**                  | POST `/api/todo`                  | 없음                                       | `{ "title": "Sample Task", "description": "Task description", "dueDate": "2024-12-12" }`    | `{ "messege": "작성이 완료되었습니다." }`                                                            |
| **To Do 수정**                  | PUT `/api/todo/{id}`              | `id` (PathVariable, Long)                 | `{ "title": "Updated Task", "description": "Updated description", "dueDate": "2024-12-15", "completed": true }` | `{ "message": "수정이 완료되었습니다." }`                                                            |
| **To Do 단건 조회**             | GET `/api/todo/{id}`              | `id` (PathVariable, Long)                 | 없음                                                                                         | `{ "id": 1, "title": "Sample Task", "description": "Task description", "dueDate": "2024-12-12", "completed": false }` |
| **To Do 삭제**                  | DELETE `/api/todo/{id}`           | `id` (PathVariable, Long)                 | 없음                                                                                         | `{ "message": "항목이 삭제되었습니다." }`                                                            |
| **To Do 전체 조회**             | GET `/api/todo/alltodo`           | 없음                                       | 없음                                                                                         | `List<GetAllToDoResponseDto>`                                                                        |
| **특정 날짜의 To Do 조회**       | POST `/api/todo/bydate`           | 없음                                       | `{ "date": "2024-12-12" }`                                                                   | `List<GetToDoByDateResponseDto>`                                                                     |
| **To Do 완료 여부 수정**         | PATCH `/api/todo/{id}/completed`  | `id` (PathVariable, Long)                 | `{ "completed": true }`                                                                      | `{ "messege": "수정이 완료되었습니다." }`                                                            |



## 5. 시퀀스 다이어그램 

## 6. 추가적인 기능 설명

## 7. 기술 스택 

## 8. 연락처
