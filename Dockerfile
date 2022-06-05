FROM gradle:7.1.0-jdk11
COPY . /gif-rates
WORKDIR /gif-rates
RUN gradle build
ENTRYPOINT ["java", "-jar", "/gif-rates/build/libs/gif-rates-1.0.0.jar"]