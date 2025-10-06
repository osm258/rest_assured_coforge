package com.automation.steps;

import com.automation.utils.ConfigReader;
import io.cucumber.java.Before;
import io.restassured.RestAssured;
import io.restassured.config.HttpClientConfig;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.impl.conn.PoolingClientConnectionManager;


public class Hooks {

    @Before
    public void setUp() {
        setUpConfig();
        ConfigReader.initConfig();
        ConfigReader.clearScenarioStore();
        RestAssured.baseURI = ConfigReader.getProperty("api.url");
    }

    public void setUpConfig() {
        PoolingClientConnectionManager cm = new PoolingClientConnectionManager();
        cm.setMaxTotal(100);
        cm.setDefaultMaxPerRoute(20);

        RestAssured.config = RestAssured.config().httpClient(
                HttpClientConfig.httpClientConfig().httpClientFactory(() -> {
                    return new DefaultHttpClient((ClientConnectionManager) cm);
                })
        );

    }

}
