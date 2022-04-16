package com.woyou.springcucumber.config;

import com.woyou.springcucumber.SpringcucumberApplication;
import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;

@CucumberContextConfiguration
@ComponentScan({})
@SpringBootTest(classes = {SpringcucumberApplication.class})
@PropertySource("classpath:/application.yaml")
public class SpringCucumberConfig {
}
