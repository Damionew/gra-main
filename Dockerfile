#springboot development environment
FROM ubuntu:15.10
MAINTAINER damionew 
COPY jdk1.7.0_79 jdk1.7.0_79

ENV JAVA_HOME=/jdk1.7.0_79
ENV PATH=$JAVA_HOME/bin:$PATH
ENV CLASSPATH=.:$JAVA_HOME/lib/dt.jar:$JAVA_HOME/lib/tools.jar

EXPOSE 8080

ENTRYPOINT ["java","-jar","/app.jar"]
