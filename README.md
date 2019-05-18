# Toptal project
  - The project consists three different modules (UI, REST API & Load test)
  - Runs tests using TestNG and Jmeter
  - To drive the browsers (e.g. Chrome) it's used Selenium WebDriver
  - Tests can be executed locally or remotely using Jenkins CI server
  - Project is created on MacBook Pro (macOS, Mojave)

## Importing Project (IntelliJ IDEA)
  - Import Project --> Select project root dir --> Import project from external model - Maven (leave everything by default)
  - This is Maven project and you should import dependencies (Use Auto import).

## JMeter - prerequisites on Jenkins Server

  - Download and Install Apache JMeter 5.1.1 [link1](https://jmeter.apache.org/download_jmeter.cgi)
  - Install following plugins
    - Property File Reader  – Config Element
    - Response Times vs Threads
    - Response Times Distribution
  - Set path to JMeter bin directory:
    - e.g. 
      - `export JMETER_HOME="/path/to/apache-jmeter-5.1.1"`
      - `export PATH=$PATH:$JMETER_HOME/bin`
      

##  How to run tests from command line
### REST API
  - cd $PROJECT_DIR
  - `mvn clean test -pl restapi` (or without 'mvn' if it's triggered from Jenkins)
  - Report screenshot: 
       [link1](https://www.screencast.com/t/3j1Ry2Suo3P) , 
       [link2](https://www.screencast.com/t/v3VzW0Gupulj)
![alt text](https://content.screencast.com/users/askeledz/folders/Jing/media/ceb3d2bf-2873-4597-a43f-47d35fc9612a/00000326.png)

### SELENIUM
  - cd $PROJECT_DIR
  - `mvn clean test -pl selenium -Duser.browser=chrome` (or without 'mvn' if it's triggered from Jenkins)
  - Report screenshot: 
       [link1](https://www.screencast.com/t/PGX8Rlpb) ,
       [link2](https://www.screencast.com/t/OM7RTeLbv)
![alt text](https://content.screencast.com/users/askeledz/folders/Jing/media/fd5b7199-f03e-4a16-af20-f199d73bb3c9/00000328.png)

### Jenkins CI
  - https://host:port/view/toptal/
  
### LOAD (JMeter config)
  - e.g. `jmeter -n -t $WORKSPACE/jmeter/toptal.jmx -l test.jtl -Jenvironment=${env} -Jmodule=${module}`
  - Report screenshot: 
       [link1](https://www.screencast.com/t/TAyWzbSY)
![alt text](https://content.screencast.com/users/askeledz/folders/Jing/media/899a0a0e-bb9a-47e7-b4bf-93082582ab4d/00000327.png)
            


