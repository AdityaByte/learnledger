FROM tomcat:9-jdk17-openjdk

COPY learnledger.war /usr/local/tomcat/webapps/

EXPOSE 8080

CMD ["catalina.sh", "run"]