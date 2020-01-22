package dev.selenium.petclinic;

import dev.selenium.configuration.Configuration;
import org.testng.annotations.Test;

public class ConfigurationTest {


    @Test
    public void urlTest(){
        Configuration config = new Configuration();
        String getBaseUrl = config.getBaseUrl();
        System.out.println(getBaseUrl);

    }
}
