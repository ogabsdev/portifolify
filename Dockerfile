FROM tomcat:jre17-temurin-jammy

ENV TZ=America/Sao_Paulo

RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone

ADD ./application-layer/application/target/portifolify.war /usr/local/tomcat/webapps/

EXPOSE 8080