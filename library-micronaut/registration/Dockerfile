FROM openjdk:14-alpine
COPY target/registration-*.jar registration.jar
EXPOSE 8080
CMD ["java", "-Dcom.sun.management.jmxremote", "-Xmx128m", "-jar", "registration.jar"]