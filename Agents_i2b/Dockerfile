FROM maven:3.5-jdk-8-alpine
MAINTAINER Alvaro Tejido Jardon <uo250821@uniovi.es>
WORKDIR /usr/src/Agents_i2b
COPY . /usr/src/Agents_i2b/
RUN mvn package -Dmaven.test.skip=true
EXPOSE 8080
CMD ["java", "-jar", "target/agents_i2b-0.0.1.jar", "--spring.data.mongodb.host=mongo_agents"]
