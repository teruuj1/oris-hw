FROM tomcat

COPY target/oris-1.0-SNAPSHOT /user/local/tomcat/webapps/ROOT.war
