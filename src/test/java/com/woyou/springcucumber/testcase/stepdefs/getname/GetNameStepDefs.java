package com.woyou.springcucumber.testcase.stepdefs.getname;

import com.woyou.springcucumber.testcase.stepdefs.GetNameRequest;
import io.cucumber.java.AfterStep;
import io.cucumber.java.BeforeStep;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;

public class GetNameStepDefs{

    @BeforeStep
    public void beforeStep(){
        System.out.println("--- before step ---");
    }

    @AfterStep
    public void afterStep(){
        System.out.println("--- after step ---");
    }

    @Autowired
    private GetNameRequest getNameRequest;

    @Given("[woyou get name] has config")
    public void woyouGetNameHasConfig() {
        getNameRequest.getUrl();
    }

    @When("[woyou get name] get {} success")
    public void woyouGetNameGetPathSuccess(String path) {
        System.out.println(path);
    }

    @Then("[woyou get name] response status is {} and code is {} success")
    public void woyouGetNameResponse(String status, int code) {
        System.out.println(status);
        System.out.println(code);
    }

    @And("[woyou get name] response body contains {} success")
    public void woyouGetNameResponseBodyContainsBodySuccess(String body) {
        System.out.println(body);
    }
}
