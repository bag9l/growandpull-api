FROM openjdk:17

WORKDIR /api
COPY .mvn/ .mvn
COPY mvnw pom.xml ./
RUN chmod +x mvnw
RUN ./mvnw dependency:resolve
COPY src ./src
RUN ./mvnw package

CMD ["./mvnw", "spring-boot:run"]
