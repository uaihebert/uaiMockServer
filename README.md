# uaiMockServer

### CodeStatus ---> [![Build Status](https://travis-ci.org/uaihebert/uaiMockServer.svg?branch=master)](https://travis-ci.org/uaihebert/uaiMockServer) [![Maven Central](https://maven-badges.herokuapp.com/maven-central/uaihebert.com/uaiMockServer/badge.svg)](https://maven-badges.herokuapp.com/maven-central/uaihebert.com/uaiMockServer)

uaiMockServer will create a Rest Server for you with only one command line.

This code can create a Standalone jar or you could add the project in your pom.xml and run it with your TDD tests.

> The documentation/roadMap/etc can be found in here: http://uaimockserver.com

> The most recent Standalone jar and the Config samples can be found in here: [![Download uaiMockServer](https://sourceforge.net/sflogo.php?type=16&group_id=2405235)](https://sourceforge.net/p/uaimockserver/)

####About the build
* If you want just to create the project JAR, run the command: `mvn clean install`
* If you want to create the Standalone jar, run the command: `mvn clean install -Pstandalone`
 

####About eh configuration
You will find a configuration sample in the sourceforge link above. In the project site you will find all the values allowed in a configuration file.
The configuration utilization vary depending on the approache used:
* If you will use the Standalone file, by default the file must be in the same folder of the Jar
* If you use the project in your TDD tests, your file must be in the "src/test/resources" directory
* You can set a full config file path for each approach, check the documentation for more detail.

####Runing with your TDD tests
You need to add the project to your pom:

```xml
<dependency>
    <groupId>uaihebert.com</groupId>
    <artifactId>uaiMockServer</artifactId>
    <version>MOST_RECENT_VERSION</version>
    <scope>test</scope>
</dependency>
```

All you need now is a class to start/shutdown the server:
```java
public class YourTest {
    private static UaiMockServer uaiMockServer;

    @BeforeClass
    public static void beforeClass() {
        uaiMockServer = UaiMockServer.start();
    }

    @AfterClass
    public static void afterClass() {
        uaiMockServer.shutdown();
    }

    @Test
    public void aTest() {
        // execute a real HTTP request, no mock needed. 
    }
}
```
