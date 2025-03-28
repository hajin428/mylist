
-- To Do 테이블 기초 데이터
INSERT INTO ToDo (title, description, dueDate, completed) VALUES ('코딩테스트 연습', '프로그래머스로 공부하기', '2024-12-23',  );
INSERT INTO ToDo (title, description, dueDate, completed) VALUES ('팀 프로젝트 미팅', '6시 Zoom 미팅', '2024-12-23',  );
INSERT INTO ToDo (title, description, dueDate, completed) VALUES ('크리스마스', '친구랑 케이크 먹기', '2024-12-25',  );
INSERT INTO ToDo (title, description, dueDate, completed) VALUES ('강아지 산책', '강변으로 산책가기', '2024-12-28',  );
INSERT INTO ToDo (title, description, dueDate, completed) VALUES ('식료품 쇼핑', '이마트', '2024-12-28',  );


-- Answer 테이블 기초 데이터
INSERT INTO Answer (toDoId, content, taskKey) VALUES (13, '프로그래머스 계정 로그인', 1);
INSERT INTO Answer (toDoId, content, taskKey) VALUES (13, '필요한 프로그래밍 언어 환경 설정', 2);
INSERT INTO Answer (toDoId, content, taskKey) VALUES (13, '코딩 인터뷰 대비 자료 검색 및 준비', 3);
INSERT INTO Answer (toDoId, content, taskKey) VALUES (14, 'Zoom 소프트웨어 업데이트 확인 및 설치', 1);
INSERT INTO Answer (toDoId, content, taskKey) VALUES (14, '미팅 일정에 맞춰 알람 설정', 2);
INSERT INTO Answer (toDoId, content, taskKey) VALUES (14, '미팅 주제와 관련된 자료 준비', 3);
INSERT INTO Answer (toDoId, content, taskKey) VALUES (15, '친구와 만나는 시간과 장소 확정하기', 1);
INSERT INTO Answer (toDoId, content, taskKey) VALUES (15, '케이크 주문하기', 2);
INSERT INTO Answer (toDoId, content, taskKey) VALUES (15, '선물 교환할 것 준비하기', 3);
INSERT INTO Answer (toDoId, content, taskKey) VALUES (16, '리드줄 및 목줄 확인하기', 1);
INSERT INTO Answer (toDoId, content, taskKey) VALUES (16, '물병 및 간식 챙기기', 2);
INSERT INTO Answer (toDoId, content, taskKey) VALUES (16, '날씨 확인하고 우산 준비하기', 3);
INSERT INTO Answer (toDoId, content, taskKey) VALUES (17, '쇼핑 목록 작성하기', 1);
INSERT INTO Answer (toDoId, content, taskKey) VALUES (17, '재사용 가능한 쇼핑백 가져가기', 2);
INSERT INTO Answer (toDoId, content, taskKey) VALUES (17, '영업시간 및 특별 할인 정보 확인하기', 3);
