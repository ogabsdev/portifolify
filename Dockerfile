FROM mcr.microsoft.com/openjdk/jdk:17-ubuntu

ENV TZ=America/Sao_Paulo

RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone

RUN mkdir /app

ADD ./application-layer/application/build/libs/application.jar /app/application.jar

WORKDIR /app

EXPOSE 8080

ENTRYPOINT java ${JAVA_OPTS} -jar application.jar