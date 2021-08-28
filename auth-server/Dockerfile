FROM openjdk:11
WORKDIR /app
RUN mkdir -p /app/logs \
&& echo "America/Sao_Paulo" > /etc/timezone \
&& dpkg-reconfigure -f noninteractive tzdata
EXPOSE 8181
COPY target/auth-server.jar app/auth-server.jar 
ENTRYPOINT ["java","-jar","app/auth-server.jar"]