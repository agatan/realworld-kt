FROM openjdk:8-jdk-alpine as builder

COPY build.gradle gradle.properties settings.gradle gradlew /workdir/
COPY gradle/ /workdir/gradle
COPY src /workdir/src
COPY resources /workdir/resources

WORKDIR /workdir
RUN /workdir/gradlew shadowJar



FROM openjdk:8-jdk-alpine as app
COPY --from=builder /workdir/build/libs/realworld-0.0.1-all.jar /app/realworld-0.0.1-all.jar
WORKDIR /app
EXPOSE 8000
CMD ["java", "-jar", "/app/realworld-0.0.1-all.jar"]
