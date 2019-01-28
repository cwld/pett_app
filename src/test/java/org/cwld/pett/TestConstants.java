package org.cwld.pett;

import com.google.gson.Gson;
import com.jcabi.manifests.Manifests;

import java.util.HashMap;
import java.util.Map;

/**
 * Holds common test constants for use in tests
 */
public class TestConstants {
    /**
     * Metadata
     */
    public final static String VERSION_ATTRIBUTE = "Application-Version";
    public final static String APPLICATION_DESCRIPTION = "Pre-interview Technical Test";
    public final static String COMMIT_HASH_ATTRIBUTE = "Commit-Hash";
    public final static String TEST_VERSION = "1.2.3-4";
    public final static String TEST_COMMIT_HASH = "1234cfg";

    /**
     * Populates the manifest with the test data, required for anything using the metadata
     */
    public final static void populateManifest() {
        Manifests.DEFAULT.put(TestConstants.VERSION_ATTRIBUTE, TestConstants.TEST_VERSION);
        Manifests.DEFAULT.put(TestConstants.COMMIT_HASH_ATTRIBUTE, TestConstants.TEST_COMMIT_HASH);
    }

    /**
     * Http server
     */
    public static final String ROOT_HELLO_TEXT = "Welcome to PETT!";
    public static final String METADATA_PATH = "metadata";
    public static final String METADATA_ROOT_ATTR = "PEET";
    public static final String METADATA_VERSION_ATTR = "version";
    public static final String METADATA_DESC_ATTR = "description";
    public static final String METADATA_COMMIT_ATTR = "commit";
    public static final String HEALTH_PATH = "health";
    public static final String HEALTH_STATUS_ATTR = "status";

    /**
     * Gets a string response that matches the expected return from the metadata HTTP request
     * given the test data
     */
    public final static String getExpectedMetadataResponse() {
        Gson gson = new Gson();
        Map<String, Object> metaDataRoot = new HashMap<>();
        Map<String, Object> metaDataArray = new HashMap<>();

        // Add metadata
        metaDataArray.put(METADATA_VERSION_ATTR, TEST_VERSION);
        metaDataArray.put(METADATA_DESC_ATTR, APPLICATION_DESCRIPTION);
        metaDataArray.put(METADATA_COMMIT_ATTR, TEST_COMMIT_HASH);

        metaDataRoot.put(METADATA_ROOT_ATTR, metaDataArray);

        return gson.toJson(metaDataRoot);
    }

    /**
     * Gets a string response that matches the expected return from the health HTTP request
     * given the test data
     */
    public final static String getExpectedHealthResponse() {
        Gson gson = new Gson();
        Map<String, Object> healthRoot = new HashMap<>();

        /* Always expect it to be healthy during the tests */
        healthRoot.put(HEALTH_STATUS_ATTR, ServerHealth.HealthStatus.PASS);

        return gson.toJson(healthRoot);
    }
}
