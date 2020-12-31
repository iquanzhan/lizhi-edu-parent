FROM jdk8:v1.0
EXPOSE 10001

VOLUME /tmp
ADD target/lizhi-edu-auth.jar  /app.jar
RUN bash -c 'touch /app.jar'
ENTRYPOINT ["java","-jar","/app.jar"]
#docker run -di  -p 10001:10001 --name v1.0.0