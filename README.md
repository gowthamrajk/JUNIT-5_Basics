# JUNIT-5_Basics ❤️ 

![](https://img.shields.io/github/languages/count/CodeJamm/JUNIT-5_Basics)   ![](https://img.shields.io/github/languages/top/gowthamrajk/JUNIT-5_Basics)

- This module consists of Some Important and Basic Concepts of JUNIT-5 (Unit Testing) which is verymuch supportive for testing our Code with multiple test scripts in Eclipse/Spring framework.

# Introduction 👋

- This JUnit 5 tutorial talks about how it adapted java 8 style of coding and several other features as well.
- JUnit 5 is most widely used testing framework for java applications. 
- For very long time, JUnit has been doing its job perfectly. 
- In between, JDK 8 brought very exciting features in java and most notably lambda expressions. 
- JUnit 5 aims to adapt java 8 style of coding and several other features as well, that’s why java 8 is required to create and execute tests in JUnit 5 (though it is possible to execute tests written with JUnit 3 or JUnit 4 for backward compatibility).

# JUnit 5 Architecture 📺
  
     JUnit 5 = JUnit Platform + JUnit Jupiter + JUnit Vintage

## JUnit Platform

- To be able to launch junit tests, IDEs, build tools or plugins need to include and extend platform APIs. It defines the TestEngine API for developing new testing frameworks that runs on the platform.
- It also provides a Console Launcher to launch the platform from the command line and build plugins for Gradle and Maven.

## JUnit Jupiter

- It includes new programming and extension models for writing tests. 
- It has all new junit annotations and TestEngine implementation to run tests written with these annotations.

## JUnit Vintage

- It primary purpose is to support running JUnit 3 and JUnit 4 written tests on the JUnit 5 platform. 
- It’s there are backward compatibility.

# Installation 📫

You can use JUnit 5 in your maven or gradle project by including minimum two dependencies i.e. Jupiter Engine Dependency and Platform Runner Dependency.

    //pom.xml
    
    <properties>
        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
        <maven.compiler.source>1.8</maven.compiler.source>
        <maven.compiler.target>${maven.compiler.source}</maven.compiler.target>
        <junit.jupiter.version>5.5.2</junit.jupiter.version>
        <junit.platform.version>1.5.2</junit.platform.version>
    </properties>
    <dependencies>
        <dependency>
            <groupId>org.junit.jupiter</groupId>
            <artifactId>junit-jupiter-engine</artifactId>
            <version>${junit.jupiter.version}</version>
            <scope>test</scope>
        </dependency>
        <dependency>
            <groupId>org.junit.platform</groupId>
            <artifactId>junit-platform-runner</artifactId>
            <version>${junit.platform.version}</version>
            <scope>test</scope>
        </dependency>
    </dependencies>

    //build.gradle
    testRuntime("org.junit.jupiter:junit-jupiter-engine:5.5.2")
    testRuntime("org.junit.platform:junit-platform-runner:1.5.2")
    
# JUnit 5 Annotations 📌

### @BeforeEach	- The annotated method will be run before each test method in the test class.
### @AfterEach - The annotated method will be run after each test method in the test class.
### @BeforeAll - The annotated method will be run before all test methods in the test class. This method must be static.
### @AfterAll - The annotated method will be run after all test methods in the test class. This method must be static.
### @Test - It is used to mark a method as junit test
### @DisplayName - Used to provide any custom display name for a test class or test method
### @Disable	- It is used to disable or ignore a test class or method from test suite.
### @Nested -	Used to create nested test classes
### @Tag	- Mark test methods or test classes with tags for test discovering and filtering
### @TestFactory -	Mark a method is a test factory for dynamic tests

# Writing Tests in JUnit 5 😄

    import org.junit.jupiter.api.AfterAll;
    import org.junit.jupiter.api.AfterEach;
    import org.junit.jupiter.api.Assertions;
    import org.junit.jupiter.api.BeforeAll;
    import org.junit.jupiter.api.BeforeEach;
    import org.junit.jupiter.api.Disabled;
    import org.junit.jupiter.api.Tag;
    import org.junit.jupiter.api.Test;
    import com.gowthamrajk.junit5.examples.Calculator;
 
    public class AppTest {
     
    @BeforeAll
    static void setup(){
        System.out.println("@BeforeAll executed");
    }
     
    @BeforeEach
    void setupThis(){
        System.out.println("@BeforeEach executed");
    }
     
    @Tag("DEV")
    @Test
    void testCalcOne() 
    {
        System.out.println("======TEST ONE EXECUTED=======");
        Assertions.assertEquals( 4 , Calculator.add(2, 2));
    }
     
    @Tag("PROD")
    @Disabled
    @Test
    void testCalcTwo() 
    {
        System.out.println("======TEST TWO EXECUTED=======");
        Assertions.assertEquals( 6 , Calculator.add(2, 4));
    }
     
    @AfterEach
    void tearThis(){
        System.out.println("@AfterEach executed");
    }
     
    @AfterAll
    static void tear(){
        System.out.println("@AfterAll executed");
    }
    }

# Test Suites ✔️

- Using JUnit 5 test suites, you can run tests spread into multiple test classes and different packages. 
- JUnit 5 provides two annotations: @SelectPackages and @SelectClasses to create test suites.
- To execute the suite, you will use @RunWith(JUnitPlatform.class).

      @RunWith(JUnitPlatform.class)
      @SelectPackages("com.gowthamrajk.junit5.examples")
      public class JUnit5TestSuiteExample 
      {
    
      }

Additionally, you can use following annotations for filtering test packages, classes or even test methods.

   1) @IncludePackages and @ExcludePackages to filter packages
   2) @IncludeClassNamePatterns and @ExcludeClassNamePatterns to filter test classes
   3) @IncludeTags and @ExcludeTags to filter test methods

    @RunWith(JUnitPlatform.class)
    @SelectPackages("com.gowthamrajkinjava.junit5.examples")
    @IncludePackages("com.gowthamrajk.junit5.examples.packageC")
    @ExcludeTags("PROD")
    public class JUnit5TestSuiteExample 
    {
    
    }

# Assertions ⌚

- Assertions help in validating the expected output with actual output of a testcase. 
- To keep things simple, all JUnit Jupiter assertions are static methods in the org.junit.jupiter.Assertions class e.g. assertEquals(), assertNotEquals().

      void testCase() 
      {
          //Test will pass
          Assertions.assertNotEquals(3, Calculator.add(2, 2));
      
          //Test will fail 
          Assertions.assertNotEquals(4, Calculator.add(2, 2), "Calculator.add(2, 2) test failed");
      
          //Test will fail 
          Supplier<String> messageSupplier  = ()-> "Calculator.add(2, 2) test failed";
          Assertions.assertNotEquals(4, Calculator.add(2, 2), messageSupplier);
        }
        
        
# Assumptions ⌛

- Assumptions class provides static methods to support conditional test execution based on assumptions. 
- A failed assumption results in a test being aborted. Assumptions are typically used whenever it does not make sense to continue execution of a given test method. 
- In test report, these test will be marked as passed.
- JUnit jupiter Assumptions class has two such methods: assumeFalse(), assumeTrue().

      public class AppTest 
      {
          @Test
          void testOnDev() 
          {
              System.setProperty("ENV", "DEV");
              Assumptions.assumeTrue("DEV".equals(System.getProperty("ENV")), AppTest::message);
          }
      
          @Test
          void testOnProd() 
          {
              System.setProperty("ENV", "PROD");
              Assumptions.assumeFalse("DEV".equals(System.getProperty("ENV")));  
          }
      
          private static String message () 
          {
              return "TEST Execution Failed :: ";
          }
      }
      

# Backward Compatibility for JUnit 3 or JUnit 4 ⏩ 

- JUnit 4 has been here for a quite long time and there are numerous tests written in junit 4. 
- JUnit Jupiter need to support those tests as well. For this purpose, JUnit Vintage sub-project is developed.
- JUnit Vintage provides a TestEngine implementation for running JUnit 3 and JUnit 4 based tests on the JUnit 5 platform.



<br><br>
**For more queries, reach me through gowthamraj692@gmail.com or whatsapp @ 9698382306**

<br>

<div align="center">

# Show some ❤️ by starring this repository !!!

</div>

<br>

## Organization Created & Maintained By 

# ![](https://img.shields.io/static/v1?style=for-the-badge&message=Gowthamraj+K&color=007396&label=) 😄

![](https://img.shields.io/static/v1?style=for-the-badge&message=Fullstack+Web+Developer&color=0b3d36&label=)  ![](https://img.shields.io/static/v1?style=for-the-badge&message=UI+Designer&color=d92323&label=) ![](https://img.shields.io/static/v1?style=for-the-badge&message=Learning+new+things&color=0c0c4f&label=)  ![](https://img.shields.io/static/v1?style=for-the-badge&message=Design+Thinker&color=0b3d17&label=) 

<br>

### Connect with me 👋:

[<img align="left" alt="code-Jamm.in" width="22px" src="https://raw.githubusercontent.com/iconic/open-iconic/master/svg/globe.svg" />][website1]
[<img align="left" alt="GowthamRaj | YouTube" width="22px" src="https://cdn.jsdelivr.net/npm/simple-icons@v3/icons/youtube.svg" />][youtube]
[<img align="left" alt="GowthamRaj " width="22px" src="https://www.iconfinder.com/data/icons/logos-and-brands/512/160_Hackerrank_logo_logos-512.png" />][hackerrank]
[<img align="left" alt="GowthamRaj  | Twitter" width="22px" src="https://cdn.jsdelivr.net/npm/simple-icons@v3/icons/twitter.svg" />][twitter]
[<img align="left" alt="GowthamRaj  | LinkedIn" width="22px" src="https://cdn.jsdelivr.net/npm/simple-icons@v3/icons/linkedin.svg" />][linkedin]
[<img align="left" alt="GowthamRaj  | Instagram" width="22px" src="https://cdn.jsdelivr.net/npm/simple-icons@v3/icons/instagram.svg" />][instagram]
[![](https://img.shields.io/badge/9698382306-25D366?style=social&logo=whatsapp&logoColor=green)]()

<br>

## Copyright-and-license 📌

Code and documentation Copyright 2021 : **Gowthamraj K**


[website1]: https://sites.google.com/view/code-jamm
[hackerrank]: https://www.hackerrank.com/gowthamraj692
[website]: https://github.com/gowthamrajk
[twitter]: https://twitter.com/Gowtham29341737
[youtube]: https://www.youtube.com/channel/UC_Q5Zet9Oz-UVAeJ-oE_uGQ?view_as=subscriber
[instagram]: https://instagram.com/gow_t_h_a_m_r_a_j
[linkedin]: https://www.linkedin.com/in/gowtham-kittusamy-54b835174/
