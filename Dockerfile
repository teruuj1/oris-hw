FROM tomcat:10.1.31-jdk21-temurin

WORKDIR /usr/local/tomcat

COPY target/oris-1.0-SNAPSHOT.war /user/local/tomcat/webapps/ROOT.war
