FROM openjdk:13-jdk-alpine
COPY "./target/moderneducation-0.0.1-SNAPSHOT.jar" "app.jar"
EXPOSE 3000
ENTRYPOINT ["java","-jar","app.jar"]