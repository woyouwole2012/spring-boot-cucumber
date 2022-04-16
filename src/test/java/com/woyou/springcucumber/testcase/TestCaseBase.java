package com.woyou.springcucumber.testcase;

import com.woyou.springcucumber.config.SpringCucumberConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;

@ContextConfiguration(classes = {SpringCucumberConfig.class})
public class TestCaseBase extends AbstractTestNGSpringContextTests {

    @Value("${woyou.baseurl}")
    public String baseUrl;

}
