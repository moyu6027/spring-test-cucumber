package cn.qa.sean.context;


import cn.qa.sean.configuration.BaseTestConfiguration;
import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;


@CucumberContextConfiguration
@SpringBootTest
//@ContextConfiguration(classes = BaseTestConfiguration.class)
public class CucumberTestContextConfiguration {


}
