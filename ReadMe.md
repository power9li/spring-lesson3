#start server 

1) with profile power.prod:
mvn tomcat7:run-war -Dspring.profiles.active=power.prod

2) with profile power.test
mvn tomcat7:run-war -Dspring.profiles.active=power.test
or
mvn tomcat7:run-war
because test is default



#client invoke
Run com.power.spring.lesson3.client.UserClient.main
