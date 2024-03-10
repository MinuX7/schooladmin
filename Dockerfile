#
# Build stage

FROM maven:3.9-eclipse-temurin-17-alpine AS Build
COPY src /home/app/src
COPY pom.xml /home/app
RUN mvn -f /home/app/pom.xml clean package -DskipTests

FROM amazoncorretto:17-al2-full
COPY --from=Build /home/app/target/schooladmin*.jar /usr/local/lib/schooladmin.jar
#ADD target/academy-0.0.1.jar /usr/local/lib/academy.jar
ENTRYPOINT ["java","-jar","/usr/local/lib/schooladmin.jar"]