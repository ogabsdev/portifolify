package br.com.via;

import br.com.portifolify.application.dataprovider.impl.event.transactionaloutbox.OutboxStatus;
import br.com.portifolify.application.dataprovider.impl.persistence.entity.CustomerOutboxEntity;
import br.com.portifolify.application.entrypoint.restcontroller.dto.request.CustomerPostRequest;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.awaitility.Awaitility;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.test.annotation.DirtiesContext;

import jakarta.persistence.EntityManager;
import java.util.concurrent.TimeUnit;

@DirtiesContext
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@EmbeddedKafka(partitions = 1, brokerProperties = {"listeners=PLAINTEXT://localhost:9092", "port=9092"})
class CustomerTest {

    @Autowired
    private EntityManager entityManager;

    @Test
    @DisplayName("Should persist the customer and publish domain event")
    void shouldPersistCustomerAndPublishDomainEvent() {
        final var customerPostRequest = getJohnDoe();

        Response response = RestAssured.given()
                .log().all()
                .header("Content-Type", "application/json")
                .body(customerPostRequest)
                .when()
                .post("/customers")
                .then()
                .assertThat()
                .statusCode(201)
                .extract().response();

        String customerUUID = response.getHeader("Location").replace("/customers/", "");

        RestAssured.given()
                .log().all()
                .when()
                .get("/customers/" + customerUUID)
                .then()
                .assertThat()
                .body("scorePercentage", Matchers.equalTo(93))
                .body("scoreProcessingDate", Matchers.equalTo("2022-12-03T10:15:30"))
                .body("firstName", Matchers.equalTo(customerPostRequest.getFirstName()))
                .body("lastName", Matchers.equalTo(customerPostRequest.getLastName()))
                .body("document", Matchers.equalTo(customerPostRequest.getDocument()));

        Awaitility.await()
                .atMost(2, TimeUnit.SECONDS)
                .pollInterval(500, TimeUnit.MILLISECONDS)
                .until(() -> {
                    CustomerOutboxEntity customerOutbox = entityManager.find(CustomerOutboxEntity.class, 1L);
                    return customerOutbox.getStatus().equals(OutboxStatus.PROCESSED);
                });
    }

    @Test
    @DisplayName("Validate credit score business rule")
    void validateCreditScoreBusinessRule() {
        RestAssured.given()
                .log().all()
                .header("Content-Type", "application/json")
                .body(getJohnDoe())
                .when()
                .post("/customers")
                .then()
                .assertThat()
                .statusCode(422);
    }

    private CustomerPostRequest getJohnDoe() {
        CustomerPostRequest customerPostRequest = new CustomerPostRequest();
        customerPostRequest.setDocument("37124632873");
        customerPostRequest.setFirstName("John");
        customerPostRequest.setLastName("Doe");

        return customerPostRequest;
    }

}
