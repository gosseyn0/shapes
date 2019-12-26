FROM java:8
EXPOSE 8080
COPY wait-for-it.sh /wait-for-it.sh
RUN chmod +x /wait-for-it.sh
ADD build/libs/shapes-service-0.0.1-SNAPSHOT.jar app.jar
#ENTRYPOINT ["java","-jar","app.jar"]
