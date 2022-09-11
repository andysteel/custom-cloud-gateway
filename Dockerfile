FROM maven:3.8.6-jdk-11
WORKDIR /app
RUN ln -sf /usr/share/zoneinfo/America/Sao_Paulo /etc/localtime \
&& mkdir -p /logs
EXPOSE 8181
COPY . .
RUN mvn clean install -DskipTests
ENTRYPOINT ["java","-jar","target/auth-server.jar"]
