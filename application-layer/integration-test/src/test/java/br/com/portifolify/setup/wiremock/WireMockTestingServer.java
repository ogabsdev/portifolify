package br.com.portifolify.setup.wiremock;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;

public class WireMockTestingServer {
    private static class Loader {
        static final WireMockServer INSTANCE = new WireMockServer(new WireMockConfiguration()
                .port(8888)
        );
    }

    private WireMockTestingServer() {
    }

    public static WireMockServer getInstance() {
        return Loader.INSTANCE;
    }
}
