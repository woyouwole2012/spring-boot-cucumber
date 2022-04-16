package com.woyou.springcucumber;

import com.woyou.springcucumber.config.SpringCucumberConfig;
import com.woyou.springcucumber.listeners.BootListener;
import io.cucumber.testng.FeatureWrapper;
import io.cucumber.testng.PickleWrapper;
import io.cucumber.testng.TestNGCucumberRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.*;

//@SpringBootTest(classes = {SpringcucumberApplication.class})
//@PropertySource("classpath:/application.yaml")
@Listeners({BootListener.class})
@ContextConfiguration(classes = {SpringCucumberConfig.class})
public class SuperRunner extends AbstractTestNGSpringContextTests {
    private TestNGCucumberRunner testNGCucumberRunner;

    public SuperRunner() {
    }

    @BeforeClass(
            alwaysRun = true
    )
    public void setUpClass() {
        this.testNGCucumberRunner = new TestNGCucumberRunner(this.getClass());
    }

    @Test(
            groups = {"cucumber"},
            description = "Runs Cucumber Scenarios",
            dataProvider = "scenarios"
    )
    public void runScenario(PickleWrapper pickleWrapper, FeatureWrapper featureWrapper) {
        this.testNGCucumberRunner.runScenario(pickleWrapper.getPickle());
    }

    @DataProvider
    public Object[][] scenarios() {
        return this.testNGCucumberRunner == null ? new Object[0][0] : this.testNGCucumberRunner.provideScenarios();
    }

    @AfterClass(
            alwaysRun = true
    )
    public void tearDownClass() {
        if (this.testNGCucumberRunner != null) {
            this.testNGCucumberRunner.finish();
        }
    }
}
