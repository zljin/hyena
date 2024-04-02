# hyena


### Implementing poc of SpringCloud project based on nacos + gateway + feign + lb

step by step:
1. import db/nacos_config.sql in your mysql database
2. setup jdbc parameter directly
3. start hyena-register
4. start hyena-gateway
5. start hyena-producer
6. start hyena-consumer
7. curl http://localhost:20001/api/consumer/eat/fish


### Implementing hyena-auth based on SpringSecurity + jwt

step by step:
1. import db/hyena_auth.sql in your mysql database
2. setup jdbc parameter directly
3. register customer and login will create token
4. use token to request test controller

```
curl --location 'http://127.0.0.1:8953/normal/test' \
--header 'Authorization: Bearer {{your_token}}'
```