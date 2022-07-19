# MsPagoService

Proyecto de microservicio de pago

# Pasos para levantar los servicios de pago

Ejecutar el siguiente comando para crear la imagen
`
docker build -t jcspz0/ms-pago-service:1.0 -f ./Dockerfile-back .
`
\n
Ejecutar el siguiente comando para iniciar el contenedor
`
 docker container run -p 8081:8081 -d --name ms-pago-service jcspz0/ms-pago-service:1.0
`

# Pasos para levantar la web de pago

Ejecutar el siguiente comando para construir la imagen
`
docker build -t jcspz0/ms-pago-web:1.0 -f ./Dockerfile-front .
`
Luego ejecutar el siguiente comando para iniciar el contenedor
`
docker container run -p 4000:4000 -d --name ms-pago-web jcspz0/ms-pago-web:1.0
`
Para ingresar a la web se debe de ir a la siguiente url
http://localhost:4000

# Pasos para levantar la aplicacion con docker-compose

Para ejecutar como docker-compose solo se debe de ejecutar el siguiente comando
`
docker-compose up
`
Nota: tomar en cuenta que el ms-pago-service no funcionará hasta que la ms-pago-db este lista (si no existe la BD aun tardará más)

