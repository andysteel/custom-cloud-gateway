FROM openjdk:11
WORKDIR /app
RUN ln -sf /usr/share/zoneinfo/America/Sao_Paulo /etc/localtime \
&& mkdir -p /logs
EXPOSE 8181
RUN mvn clean install -DskipTests
COPY target/auth-server.jar auth-server.jar
ENTRYPOINT ["java","-jar","auth-server.jar"]
