package br.com.via.setup;

import br.com.via.setup.database.DatabaseCleaner;
import br.com.via.setup.restassured.RestAssuredTestMechanism;
import br.com.via.setup.wiremock.WireMockTestMechanism;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.event.AfterTestMethodEvent;
import org.springframework.test.context.event.BeforeTestMethodEvent;
import org.springframework.test.context.event.annotation.AfterTestMethod;
import org.springframework.test.context.event.annotation.BeforeTestMethod;

import java.sql.SQLException;

@Configuration
public class IntegrationTestConfig {

    @Autowired
    protected WireMockTestMechanism wireMockTestMechanism;

    @Autowired
    protected RestAssuredTestMechanism restAssuredTestMechanism;

    @Autowired
    protected DatabaseCleaner databaseCleaner;

    @BeforeTestMethod
    public void beforeTestMethod(final BeforeTestMethodEvent beforeTestMethodEvent) throws SQLException {
        restAssuredTestMechanism.configurePort(beforeTestMethodEvent.getTestContext());
        wireMockTestMechanism.loadStubs(beforeTestMethodEvent.getTestContext());
        databaseCleaner.clean();
    }

    @AfterTestMethod
    public void afterTestMethod(final AfterTestMethodEvent afterTestMethodEvent) {
        wireMockTestMechanism.unloadStubs();
    }

}
