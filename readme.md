# RabbitMQ Docker kurulum
docker run -d --hostname my-rabbit --name some-rabbit -p 5672:5672 -p 15672:15672 -e RABBITMQ_DEFAULT_USER=user -e RABBITMQ_DEFAULT_PASS=user -m=128m  rabbitmq:3-management  

# Redis
docker run --name redisjava12 -d -p 6379:6379 -m=256m  redis


## Enviroment'a eklenecek
SPRING_AMQP_DESERIALIZATION_TRUST_ALL=true
