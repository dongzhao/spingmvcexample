get grant code
--------------------------------------------
http://localhost:8090/oauth/authorize?client_id=springmvcdemo&response_type=code


curl -i -X POST -H "Content-Type: application/json" -d "{\"userName\":\"kevin\"}" localhost:8090/user/create