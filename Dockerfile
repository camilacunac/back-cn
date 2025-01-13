FROM eclipse-temurin:22-jdk AS buildstage

RUN apt-get update && apt-get install -y maven

WORKDIR /app

COPY pom.xml .
COPY src /app/src
COPY Wallet_HEALZJHB0K6M53N7 /app/wallet

ENV TNS_ADMIN=/app/wallet

RUN mvn clean package

FROM eclipse-temurin:22-jdk

COPY --from=buildstage /app/target/alertas-medicas-0.0.1-SNAPSHOT.jar /app/alertas-medicas.jar

COPY Wallet_HEALZJHB0K6M53N7 /app/wallet

ENV TNS_ADMIN=/app/wallet
EXPOSE 8081

ENTRYPOINT [ "java", "-jar", "/app/alertas-medicas.jar" ]