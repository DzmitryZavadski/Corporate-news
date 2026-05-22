# Этап 1: Сборка jar-файла с помощью JDK

FROM eclipse-temurin:21-jdk-alpine AS build
WORKDIR /app
# Копируем файлы сборщика gradle
COPY gradle gradle
COPY gradlew gradlew.bat build.gradle settings.gradle /app/
COPY src src
# Собираем проект, пропуская тесты ради скорости
RUN ./gradlew bootJar --no-daemon -x test

# Этап 2: Запуск собранного jar на легковесной JRE (поддерживает x86 и arm)
FROM eclipse-temurin:21-jre-alpine
WORKDIR /app
COPY --from=build /app/build/libs/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]