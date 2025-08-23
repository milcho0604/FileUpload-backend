# --- Build stage ---
FROM gradle:8.7-jdk17-alpine AS build
WORKDIR /workspace

# 캐시 최적화: wrapper/의존성 먼저 복사
COPY gradlew ./
COPY gradle ./gradle
RUN chmod +x gradlew

COPY build.gradle settings.gradle ./
# (있다면) gradle.properties도 함께
# COPY gradle.properties ./
RUN ./gradlew --no-daemon dependencies || true

# 소스 마지막 복사
COPY src ./src

# 테스트는 임시 배포에서 스킵 가능
RUN ./gradlew --no-daemon bootJar -x test

# --- Runtime stage ---
FROM eclipse-temurin:17-jre-alpine
WORKDIR /app
COPY --from=build /workspace/build/libs/*.jar /app/app.jar
ENV JAVA_OPTS=""
EXPOSE 8080
ENTRYPOINT ["sh","-c","java $JAVA_OPTS -jar /app/app.jar"]
