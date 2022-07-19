FROM openjdk:18-alpine

RUN mkdir /app
COPY ./target/ms-pago-webapi-0.0.1-exec.jar /app/ms-pago-webapi-0.0.1-exec.jar
WORKDIR /app
CMD "java" "-jar" "ms-pago-webapi-0.0.1-exec.jar"


