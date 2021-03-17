FROM openjdk:latest

RUN apt get update
RUN apt install maven
RUN mkdir /etc/inventory_app/main
COPY main/* /etc/inventory_app/main/
WORKDIR /etc/inventory_app/main/

RUN mvn install -DskipTests

EXPOSE 2004
ENTRYPOINT ["java", "-jar", "inventory_app.jar"]