FROM alpine:latest

RUN apk update
RUN apk add openjdk11 maven

RUN mkdir -p /etc/inventory_app/src/main/
COPY src/main /etc/inventory_app/src/main/
COPY pom.xml /etc/inventory_app/pom.xml

WORKDIR /etc/inventory_app/
RUN mvn install -DskipTests

EXPOSE 2004

ENTRYPOINT ["java", "-jar", "/etc/inventory_app/target/inventory_app.jar"]