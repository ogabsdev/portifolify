package br.com.via.setup.wiremock;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.common.Json;
import com.github.tomakehurst.wiremock.stubbing.StubMapping;
import com.github.tomakehurst.wiremock.stubbing.StubMappingCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.stereotype.Component;
import org.springframework.test.context.TestContext;
import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@Component
public class WireMockTestMechanism {
    private static final Logger LOG = LoggerFactory.getLogger(WireMockTestMechanism.class);
    private final WireMockServer wireMockServer = WireMockTestingServer.getInstance();

    public void loadStubs(final TestContext testContext) {
        final String defaultFolderName = "classpath:/stubs/commons/**/*.json";
        final String currentTestFolderName = String.format("classpath:/stubs/%s/%s/**/*.json",
                testContext.getTestClass().getSimpleName(),
                testContext.getTestMethod().getName());

        List<String> stubFilenameLoaded = new ArrayList<>();

        final PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();

        try {
            for (final Resource resource : resolver.getResources(currentTestFolderName)) {
                String stub = StreamUtils.copyToString(resource.getInputStream(), StandardCharsets.UTF_8);

                if (stub.contains("mappings")) {
                    StubMappingCollection stubMappingCollection = Json.read(stub, StubMappingCollection.class);
                    for (StubMapping stubMapping : stubMappingCollection.getMappings()) {
                        wireMockServer.addStubMapping(stubMapping);
                    }
                } else {
                    wireMockServer.addStubMapping(StubMapping.buildFrom(stub));
                }

                stubFilenameLoaded.add(resource.getFilename());
            }
        } catch (IOException e) {
            LOG.warn("Error loading custom stub file", e);
        }

        try {
            for (final Resource resource : resolver.getResources(defaultFolderName)) {
                if (!stubFilenameLoaded.contains(resource.getFilename())) {
                    wireMockServer.addStubMapping(StubMapping.buildFrom(StreamUtils
                            .copyToString(resource.getInputStream(), StandardCharsets.UTF_8)));
                }
            }
        } catch (IOException e) {
            LOG.warn("Error loading common stub file", e);
        }
    }

    public void unloadStubs() {
        wireMockServer.resetAll();
        wireMockServer.listAllStubMappings()
                .getMappings()
                .forEach(WireMock::removeStub);
    }
}
