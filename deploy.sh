TOMCAT_HOME="/opt/apache-tomcat-10.1.28"

mvn clean package
cp target/bibliotheque-1.0.war "$TOMCAT_HOME"/webapps
