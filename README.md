# hyena
> poc of SpringCloud project

run step:
db -- import your local mysql first
hyena-register -- nacos register start in source code
hyena-gateway
hyena-producer
hyena-consumer -- feign to hyena-producer

test script:
curl http://localhost:20001/api/consumer/eat/fish

