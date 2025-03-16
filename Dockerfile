FROM tomcat:9.0-jdk17
# Set build-time arguments
# ARG DB_URL="DEFAULT"
# ARG DB_USERNAME="DEFAULT"
# ARG DB_PASSWORD="DEFAULT"
# Set environment variables using arguments
# ENV DB_URL=$DB_URL
# ENV DB_USERNAME=$DB_USERNAME
# ENV DB_PASSWORD=$DB_PASSWORD
ENV JAVA_OPTS='-Xmx1g -Duser.timezone=Asia/Kolkata -Djava.locale.providers=COMPAT,CLDR,SPI'
RUN chmod 777 /tmp
#ADD /target/MyRestApp.war /usr/local/tomcat/webapps/
# Switched from ADD to COPY as its more predicatble for local files.
# Removed the leading / in /target/MyRestApp.war because Docker’s build context doesn't include absolute paths.
COPY target/MyRestApp.war /usr/local/tomcat/webapps/
RUN unlink /etc/localtime
RUN ln -s /usr/share/zoneinfo/Asia/Kolkata /etc/localtime
EXPOSE 8080
CMD ["/usr/local/tomcat/bin/catalina.sh", "run"]
