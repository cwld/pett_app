package org.cwld.pett;


/**
 * Main class to for the PETT application
 */
public class PettMain {
    private static final PettHttpServer httpServer = new PettHttpServer();

    /**
     * Main entrypoint for the PETT application
     * @param args  Arguments passed to the java application at runtime
     */
    public static void main(String args[]) {
        System.out.println("PETT application");
        System.out.println("Version: " + MetadataInfo.getVersion());
        httpServer.run();
    }
}
