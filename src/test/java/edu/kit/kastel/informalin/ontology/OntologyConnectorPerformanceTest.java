package edu.kit.kastel.informalin.ontology;

import java.time.Duration;
import java.time.Instant;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class OntologyConnectorPerformanceTest {
    private static final int CALLS = 10 * 1024;

    private static Logger logger = LogManager.getLogger(OntologyConnectorPerformanceTest.class);

    private static String ontologyPath = "src/test/resources/teammates_w_text.owl";

    private OntologyConnector ontologyConnector;

    private static OntologyConnector createOntologyConnector(String ontologyPath) {
        return new OntologyConnector(ontologyPath);
    }

    @BeforeEach
    void beforeEach() {
        ontologyConnector = createOntologyConnector(ontologyPath);
    }

    @AfterEach
    void afterEach() {
        ontologyConnector = null;
    }

    @Disabled("Disabled for CI. Enable locally if you need this performance test")
    @Test
    @DisplayName("test performance of getClass by label")
    void getClassByLabelPerformanceTest() {
        String[] className = { "Word", "CorefCluster", "Ecore" };
        String[] uri = { "https://informalin.github.io/knowledgebases/informalin_base#OWLClass_33cd62aa_e856_4bd3_93fd_454951b453b0",
                "https://informalin.github.io/knowledgebases/informalin_base_text.owl#OWLClass_b5f49fac_9778_48c1_9462_f6dd11ae711f",
                "https://informalin.github.io/knowledgebases/informalin_base_ecore.owl#OWLClass_07ebfe27_48f8_44ae_a058_d245321fea01" };
        var start = Instant.now();
        for (var i = 0; i < CALLS; i++) {
            var clazz = ontologyConnector.getClass(className[i % 3]);
            Assertions.assertTrue(clazz.isPresent());
            Assertions.assertEquals(uri[i % 3], clazz.get().getURI());
        }
        var end = Instant.now();
        logExecutionTime("getClass(label)", start, end);
    }

    @Disabled("Disabled for CI. Enable locally if you need this performance test")
    @Test
    @DisplayName("test performance of getClass by localname")
    void getClassByLocalNamePerformanceTest() {
        String[] className = { "BasicComponent", "core", "OWLClass_EClass" };
        String[] uri = { "https://informalin.github.io/knowledgebases/informalin_base_pcm.owl#BasicComponent",
                "https://informalin.github.io/knowledgebases/informalin_base_pcm.owl#core",
                "https://informalin.github.io/knowledgebases/informalin_base_ecore.owl#OWLClass_EClass" };
        var start = Instant.now();
        for (var i = 0; i < CALLS; i++) {
            var clazz = ontologyConnector.getClass(className[i % 3]);
            Assertions.assertTrue(clazz.isPresent());
            Assertions.assertEquals(uri[i % 3], clazz.get().getURI());
        }
        var end = Instant.now();
        logExecutionTime("getClass(localname)", start, end);
    }

    @Test
    @DisplayName("test performance of getIndividual by label")
    void getIndividualByLabelPerformanceTest() {
        String[] individualName = { "combined", "Service", "Those" };
        String[] uri = { "https://informalin.github.io/knowledgebases/examples/teammates.owl#u4OKDhbdK7",
                "https://informalin.github.io/knowledgebases/examples/teammates.owl#atGWKeJYJo",
                "https://informalin.github.io/knowledgebases/examples/teammates.owl#aIaU9gbKPb" };
        var start = Instant.now();
        for (var i = 0; i < CALLS; i++) {
            var individual = ontologyConnector.getIndividual(individualName[i % 3]);
            Assertions.assertTrue(individual.isPresent());
            Assertions.assertEquals(uri[i % 3], individual.get().getURI());
        }
        var end = Instant.now();
        logExecutionTime("getIndividual(label)", start, end);
    }

    @Test
    @DisplayName("test performance of getIndividual by uri")
    void getIndividualByUriPerformanceTest() {
        String[] uri = { "https://informalin.github.io/knowledgebases/examples/teammates.owl#u4OKDhbdK7",
                "https://informalin.github.io/knowledgebases/examples/teammates.owl#atGWKeJYJo",
                "https://informalin.github.io/knowledgebases/examples/teammates.owl#aIaU9gbKPb" };
        var start = Instant.now();
        for (var i = 0; i < CALLS; i++) {
            var individual = ontologyConnector.getIndividualByIri(uri[i % 3]);
            Assertions.assertTrue(individual.isPresent());
        }
        var end = Instant.now();
        logExecutionTime("getIndividual(Iri)", start, end);
    }

    @Disabled("Disabled for CI. Enable locally if you need this performance test")
    @Test
    @DisplayName("test performance of list retrieval and iteration")
    void listPerformanceTest() {
        var listUri = "https://informalin.github.io/knowledgebases/examples/teammates.owl#qBkF5rSDSD";

        var start = Instant.now();
        for (var i = 0; i < 100; i++) {
            var listOpt = ontologyConnector.getListByIri(listUri);
            Assertions.assertTrue(listOpt.isPresent());
            var list = listOpt.get();
            for (var entry : list) {
                Assertions.assertNotNull(entry);
            }
        }
        var end = Instant.now();
        logExecutionTime("list", start, end);
    }

    private void logExecutionTime(String name, Instant start, Instant end) {
        var duration = Duration.between(start, end);
        logger.info("{} took {}.{}s", name, duration.getSeconds(), duration.getNano());
    }

}
