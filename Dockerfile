FROM openjdk:21-jdk
WORKDIR /app
COPY .env .env
COPY target/xml-to-java.jar app.jar
ARG LOG_FOLDER
RUN mkdir -p ${LOG_FOLDER}
ARG XML_TO_JAVA_SERVER_PORT
EXPOSE ${XML_TO_JAVA_SERVER_PORT}
ENTRYPOINT ["java", "-cp", "app.jar:BOOT-INF/lib/*", "org.springframework.boot.loader.launch.JarLauncher"]