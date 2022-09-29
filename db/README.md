* db 접근법 
1. (window경우) winpty docker exec -it jeju-postgres bash
2. (mac,linux 경우) docker exec -it jeju-postgres bash
3. psql --user postgres --db_name jeju_db 로 접속
4. 그 후 sql문 작성 가능