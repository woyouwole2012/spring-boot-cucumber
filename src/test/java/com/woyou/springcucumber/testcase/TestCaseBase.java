package com.woyou.springcucumber.testcase;

import com.woyou.springcucumber.config.SpringCucumberConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;

@Component
public class TestCaseBase{

    @Value("${woyou.baseurl}")
    public String baseUrl;

}
