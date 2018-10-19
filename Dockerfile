#springboot development environment
FROM ubuntu:15.10
MAINTAINER damionew 
ADD jdk-8u151-linux-x64.tar.gz /usr/local/jdk8

ENV JAVA_HOME /usr/local/jdk8/jdk1.8.0_151

ENV JRE_HOME $JAVA_HOME/jre

ENV CLASSPATH :$JAVA_HOME/lib:$JRE_HOME/lib

ENV PATH $PATH:$JAVA_HOME/bin

ARG appADD $app app.jar

ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]
