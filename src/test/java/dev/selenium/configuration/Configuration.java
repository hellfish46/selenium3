package dev.selenium.configuration;

public class Configuration {

      private static final String BASE_URL_LOCALHOST = "http://localhost:8000/petclinic";
//    private static final String BASE_URL = "http://139.59.149.247:8000/petclinic";


    private String baseUrl;


    public String getBaseUrl() {
        this.baseUrl = System.getProperty("baseUrl", BASE_URL_LOCALHOST);
        return baseUrl;
    }


}
