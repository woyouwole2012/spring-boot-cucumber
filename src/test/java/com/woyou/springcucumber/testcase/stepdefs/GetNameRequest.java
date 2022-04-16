package com.woyou.springcucumber.testcase.stepdefs;

import com.woyou.springcucumber.testcase.TestCaseBase;
import org.springframework.stereotype.Component;

@Component
public class GetNameRequest extends TestCaseBase {

    public void getUrl(){
        System.out.println(baseUrl);
    }

}
