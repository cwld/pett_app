package org.cwld.pett;

import com.google.gson.Gson;
import spark.Request;
import spark.Response;
import spark.Route;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Semaphore;

import static spark.Spark.*;

/**
 * HTTP server using spark to handle requests
 * Implements all http handlers required for PETT
 */
public class PettHttpServer implements Runnable {
    /**
     * We need a semaphore to allow the waitForInit function
     * to work correctly when run() is called in a thread
     * Otherwise Spark has its own thread safety so we don't need
     * to provide any extra synchronisation
     */
    Semaphore waitForStartSemaphore = new Semaphore(0);

    /**
     * Attribute strings for the metadata handler
     */
    private static final String METADATA_ROOT_ATTR = "PEET";
    private static final String METADATA_VERSION_ATTR = "version";
    private static final String METADATA_DESC_ATTR = "description";
    private static final String METADATA_COMMIT_ATTR = "commit";

    /**
     * Paths to handle
     */
    private static final String PATH_ROOT = "/";
    private static final String PATH_METADATA = "/metadata";

    /**
     * Port number
     */
    private int serverPort;

    /**
     * Constructor
     * @param port  The port number to run the HTTP server on
     */
    public PettHttpServer(int port)
    {
        serverPort = port;
    }

    /**
     * Defines the route for the root page at /
     */
    private static Route rootPage = (Request request, Response response) -> "Welcome to PETT!";
    private static Route metaDataPage = (Request request, Response response) -> {
        response.type("application/json");

        Gson gson = new Gson();
        Map<String, Object> metaDataRoot = new HashMap<>();
        Map<String, Object> metaDataArray = new HashMap<>();

        // Add metadata
        metaDataArray.put(METADATA_VERSION_ATTR, MetadataInfo.getVersion());
        metaDataArray.put(METADATA_DESC_ATTR, MetadataInfo.getDescription());
        metaDataArray.put(METADATA_COMMIT_ATTR, MetadataInfo.getCommitHash());

        metaDataRoot.put(METADATA_ROOT_ATTR, metaDataArray);

        return gson.toJson(metaDataRoot);
    };

    /**
     * Runs the spark HTTP server
     * @note There is no need to explicitely call start to spark, this will
     * happen when any routes are defined
     * This should only be called once, and will not complete until stop() is called
     * or an error that causes the server to go down. Consider running in a thread
     */
    public void run() {
        port(serverPort);
        get(PATH_ROOT, rootPage);
        get(PATH_METADATA, metaDataPage);
        waitForStartSemaphore.release();
    }

    /**
     * Waits for the server to be able to handle requests after calling run()
     * @note This must only be called once after 'run' else it will block infinitely
     */
    public void waitForInit() {
        waitForStartSemaphore.acquireUninterruptibly();
        awaitInitialization();
    }

    /**
     * Stops the server
     */
    public void kill() {
        stop();
    }
}
