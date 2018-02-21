######################## Project Info #############################

1. Project Name: catalystdashboard.

2. Technology: Restful Web services

3. Build process for project:

Build tool: maven

Database: Oracle

compile command: mvn clean install -P(profile name) 
 profile Name like: dim/qa5/qa4/prod/dev_india
mvn clean install -Pqa5

Running  the project with profile: mvn jetty: run -Pdim 


Skipping test case : mvn jetty: run -Pdim -Dmaven.test.skip=true





 


