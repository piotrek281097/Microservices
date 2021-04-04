FROM openjdk:14-alpine
COPY target/reservations-*.jar reservations.jar
EXPOSE 8080
CMD ["java", "-Dcom.sun.management.jmxremote", "-Xmx128m", "-jar", "reservations.jar"]