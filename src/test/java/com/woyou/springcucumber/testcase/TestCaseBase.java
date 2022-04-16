package com.woyou.springcucumber.testcase;

import com.woyou.springcucumber.config.SpringCucumberConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

@ContextConfiguration(classes = {SpringCucumberConfig.class})
public class TestCaseBase extends AbstractTestNGSpringContextTests {

    @Value("${woyou.baseurl}")
    public String baseUrl;

}
