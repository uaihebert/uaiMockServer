package test.com.uaihebert.uaimockserver.builder;

import com.uaihebert.uaimockserver.build.UaiRouteBuilder;
import com.uaihebert.uaimockserver.model.BodyValidationType;
import com.uaihebert.uaimockserver.repository.UaiRouteRepository;
import com.uaihebert.uaimockserver.runner.UaiMockServerRunner;
import com.uaihebert.uaimockserver.runner.UaiRunnerMockServerConfiguration;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Arrays;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(UaiMockServerRunner.class)
@UaiRunnerMockServerConfiguration(configurationFile = "routeBuilderTest.json")
public class UaiRouteBuilderTest {

    @Before
    public void before() {
        UaiRouteRepository.clearData();
    }

    @Test
    public void isCreatingPersistenceRouteUsingBuilder() {
        UaiRouteBuilder
                .aBuilder()
                .withPath("/aPath")
                .withMethod("GET")
                .withRequiredContentType("application/json")
                .withHoldTheRequestInMilli(1L)
                .withBodyRequired("aBody", BodyValidationType.RAW_TEXT)
                .withRequiredHeader("aKey1", Arrays.asList("AAA", "BBB"))
                .withRequiredHeader("aKey2", Arrays.asList("AAA", "BBB"))
                .withOptionalHeader("aKey1", Arrays.asList("AAA", "BBB"))
                .withOptionalHeader("aKey2", Arrays.asList("AAA", "BBB"))
                .withRequiredQueryParam("aKey1", Arrays.asList("AAA", "BBB"))
                .withRequiredQueryParam("aKey2", Arrays.asList("AAA", "BBB"))
                .withOptionalQueryParam("aKey1", Arrays.asList("AAA", "BBB"))
                .withOptionalQueryParam("aKey2", Arrays.asList("AAA", "BBB"))
                .withResponseCode(200)
                .withResponseBody("aBody")
                .withResponseBodyInFile("/aPath")
                .withResponseContentType("application/json")
                .addResponseHeader("aKey1", Arrays.asList("AAA", "BBB"))
                .addResponseHeader("aKey2", Arrays.asList("AAA", "BBB"))
                .build();

        assertTrue("it should have only one route, as not temporary", UaiRouteRepository.count() == 1);

        assertFalse(UaiRouteRepository.listAllRoutes().get(0).isTemporary());
    }

    @Test
    public void isCreatingTemporaryRouteUsingBuilder() {
        UaiRouteBuilder
                .aBuilder()
                .withPath("/aPath")
                .withMethod("GET")
                .withRequiredContentType("application/json")
                .withHoldTheRequestInMilli(1L)
                .withBodyRequired("aBody", BodyValidationType.RAW_TEXT)
                .withRequiredHeader("aKey1", Arrays.asList("AAA", "BBB"))
                .withRequiredHeader("aKey2", Arrays.asList("AAA", "BBB"))
                .withOptionalHeader("aKey1", Arrays.asList("AAA", "BBB"))
                .withOptionalHeader("aKey2", Arrays.asList("AAA", "BBB"))
                .withRequiredQueryParam("aKey1", Arrays.asList("AAA", "BBB"))
                .withRequiredQueryParam("aKey2", Arrays.asList("AAA", "BBB"))
                .withOptionalQueryParam("aKey1", Arrays.asList("AAA", "BBB"))
                .withOptionalQueryParam("aKey2", Arrays.asList("AAA", "BBB"))
                .withResponseCode(200)
                .withResponseBody("aBody")
                .withResponseBodyInFile("/aPath")
                .withResponseContentType("application/json")
                .addResponseHeader("aKey1", Arrays.asList("AAA", "BBB"))
                .addResponseHeader("aKey2", Arrays.asList("AAA", "BBB"))
                .buildAsTemporary(1);

        assertTrue(UaiRouteRepository.count() == 1);

        assertTrue(UaiRouteRepository.listAllRoutes().get(0).isTemporary());
    }
}