# Backend
Spring

# 실행법
1. 디스코드에 올린 .env file을 download하여 지금 경로에 붙여넣기
2. docker-compose up --build 명령어 입력
3. intellij 상에서 application 실행하여 개발 시작하면됨.

# db 접근법
1. docker exec -it jeju-postgres bash 로 컨테이너 접근
2. psql -U postgres -d jeju_db 로 user, db이름 인자로 부여 후 접속
3. \dt 로 모든 테이블 관찰 가능