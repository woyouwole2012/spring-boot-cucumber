package com.woyou.springcucumber;

import io.cucumber.java.Before;
import io.cucumber.spring.SpringFactory;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(plugin = {"pretty", "html:target/site/cucumber-report.html","json:target/site/cucumber-report.json"}
                          ,features = {"src/test/resources/features"}
                          ,glue = {}
                          ,objectFactory = SpringFactory.class)
public class RunTestSuit extends AbstractTestNGCucumberTests {

    @Before
    public void before(){
        System.out.println("--- before class ---");
    }

    public void after(){
        System.out.println("--- after class ---");
    }

}
