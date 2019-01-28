package org.cwld.pett;

import org.junit.*;
import spark.utils.IOUtils;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import static org.junit.Assert.*;

/**
 * Tests the PETT HTTP server
 */
public class PettHttpServerTest {
    /**
     * Set a port for the test server to run on
     * Ensure that this port is available before running tests
     */
    private static final int TEST_PORT = 10023;
    private static final String ROOT_URL = "http://127.0.0.1:" + TEST_PORT + "/";

    private static PettHttpServer httpServer;

    /**
     * Set up the server before the tests
     */
    @BeforeClass
    public static void setUp() {
        TestConstants.populateManifest();
        httpServer = new PettHttpServer(TEST_PORT);
        // Run the server as a thread
        (new Thread(httpServer)).start();
        httpServer.waitForInit();
    }

    /**
     * Kill the server after the tests
     */
    @AfterClass
    public static void tearDown() {
        httpServer.kill();
    }

    /**
     * Tests a GET request to the root
     */
    @Test
    public void testRoot() {
        try {
            URL getUrl = new URL(ROOT_URL);
            HttpURLConnection conn = (HttpURLConnection)getUrl.openConnection();
            conn.setRequestMethod("GET");

            // Verify we get an OK response first
            assertEquals(HttpURLConnection.HTTP_OK, conn.getResponseCode());

            // Verify there is the expected hello message
            assertEquals(TestConstants.ROOT_HELLO_TEXT, IOUtils.toString(conn.getInputStream()));
        } catch (IOException e) {
            fail ("Unexpected IO exception");
        }
    }

    /**
     * Tests a GET request to the metadata
     */
    @Test
    public void testMetadata() {
        try {
            URL getUrl = new URL(ROOT_URL + TestConstants.METADATA_PATH);
            HttpURLConnection conn = (HttpURLConnection)getUrl.openConnection();
            conn.setRequestMethod("GET");

            // Verify we get an OK response first
            assertEquals(HttpURLConnection.HTTP_OK, conn.getResponseCode());

            // Verify there is the expected metadata response
            assertEquals(TestConstants.getExpectedMetadataResponse(), IOUtils.toString(conn.getInputStream()));
        } catch (IOException e) {
            fail ("Unexpected IO exception");
        }
    }

    /**
     * Tests a GET request to the health
     */
    @Test
    public void testHealth() {
        try {
            URL getUrl = new URL(ROOT_URL + TestConstants.HEALTH_PATH);
            HttpURLConnection conn = (HttpURLConnection)getUrl.openConnection();
            conn.setRequestMethod("GET");

            // Verify we get an OK response first
            assertEquals(HttpURLConnection.HTTP_OK, conn.getResponseCode());

            // Verify there is the expected health response
            assertEquals(TestConstants.getExpectedHealthResponse(), IOUtils.toString(conn.getInputStream()));
        } catch (IOException e) {
            fail ("Unexpected IO exception");
        }
    }

}