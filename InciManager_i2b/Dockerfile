FROM maven:3.5-jdk-8-alpine
MAINTAINER Alvaro Tejido Jardon <uo250821@uniovi.es>
WORKDIR /usr/src/InciManager_i2b
COPY . /usr/src/InciManager_i2b/
EXPOSE 8081
CMD ["java", "-jar", "target/InciManager_i2b-0.0.1-SNAPSHOT.jar", "--spring.kafka.bootstrap-servers=kafka:9092", "--agents_url=http://agents:8080/agent", "--spring.data.mongodb.host=mongo_incidents"]
WORKDIR /usr/src/InciDashboard_i2b
COPY . /usr/src/InciDashboard_i2b/
EXPOSE 8082
CMD ["java", "-jar", "target/InciDashboard_i2b-0.0.1.jar", "--spring.data.mongodb.host=mongo_incidents", "--spring.kafka.bootstrap-servers=kafka:9092"]
