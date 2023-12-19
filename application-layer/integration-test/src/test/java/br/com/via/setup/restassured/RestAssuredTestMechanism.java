package br.com.via.setup.restassured;

import io.restassured.RestAssured;
import org.springframework.stereotype.Component;
import org.springframework.test.context.TestContext;
import org.springframework.util.NumberUtils;
import org.springframework.util.ObjectUtils;

@Component
public class RestAssuredTestMechanism {

    public void configurePort(final TestContext testContext) {
        if (RestAssured.port != RestAssured.UNDEFINED_PORT) {
            return;
        }

        final String port = testContext.getApplicationContext()
                .getEnvironment()
                .getProperty("local.server.port");
        RestAssured.port = ObjectUtils.isEmpty(port) ? 8085 : NumberUtils.parseNumber(port, Integer.class);
    }

}
