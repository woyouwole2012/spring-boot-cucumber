package com.woyou.springcucumber.runner;

import com.woyou.springcucumber.SuperRunner;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.spring.SpringFactory;
import io.cucumber.testng.CucumberOptions;


//@RunWith(CucumberWithSerenity.class)
@CucumberOptions(plugin = {"pretty", "html:target/site/cucumber-report.html","json:target/site/cucumber-report.json"}
        ,features = {"classpath:com/woyou/springcucumber/features"}
//        ,glue = {"testcase"}
        ,tags = "not @disable and @woyou"
        ,objectFactory = SpringFactory.class)
public class RunTestSuit2 extends SuperRunner {

    @Before
    public void before(){
        System.out.println("--- before class ---");
    }

    @After
    public void after(){
        System.out.println("--- after class ---");
    }

}
