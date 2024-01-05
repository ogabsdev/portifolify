package br.com.portifolify.setup.wiremock;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WireMockServerConfig {
    private static final Logger LOG = LoggerFactory.getLogger(WireMockServerConfig.class);

    @PostConstruct
    void start() {
        WireMockServer wireMockServer = WireMockTestingServer.getInstance();
        wireMockServer.start();
        WireMock.configureFor(wireMockServer.port());
        LOG.info("Single WireMock instance started and ready to use...");
    }

    @PreDestroy
    void stop() {
        WireMockTestingServer.getInstance().stop();
        LOG.info("Single WireMock instance stopped...");
    }
}
