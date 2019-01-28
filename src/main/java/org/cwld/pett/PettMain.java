package org.cwld.pett;

import org.apache.commons.cli.*;

import java.io.File;
import java.net.URISyntaxException;


/**
 * Main class to for the PETT application
 */
public class PettMain {
    /**
     * Argument strings
     */
    private static final String DEFAULT_PORT = "4567";
    private static final String PORT_OPTION = "port";
    private static final String PORT_OPTION_SHORT = "p";
    private static final String PORT_OPTION_DESC = "TCP port to run the HTTP server on, default " + DEFAULT_PORT;
    private static final String HELP_FOOTER = "\r\nFull documentation on github at https://github.com/cwld/pett_app";

    /**
     * Helper function to get the name of the JAR file being run
     * @note Adapted from https://stackoverflow.com/questions/320542/how-to-get-the-path-of-a-running-jar-file
     * by user 'Zarkonnen' (https://stackoverflow.com/users/15255/zarkonnen) on Nov 26 2008
     * @return The string of the jar file
     */
    private static final String getJarName() {
        try {
            return (new File(PettMain.class.getProtectionDomain().getCodeSource().getLocation().toURI()).getName());
        } catch (URISyntaxException e) {
            return "";
        }
    }

    /**
     * Main entrypoint for the PETT application
     * @param args  Arguments passed to the java application at runtime
     */
    public static void main(String args[]) {
        Options argOptions = new Options();

        Option portOption = new Option(PORT_OPTION_SHORT, PORT_OPTION, true, PORT_OPTION_DESC);
        portOption.setType(Integer.class);
        argOptions.addOption(portOption);

        System.out.println("PETT application");
        System.out.println("Version: " + MetadataInfo.getVersion());

        CommandLineParser cliParser = new DefaultParser();

        try {
            CommandLine cl = cliParser.parse(argOptions, args);
            int port = Integer.parseInt(cl.getOptionValue(PORT_OPTION, DEFAULT_PORT));
            PettHttpServer httpServer = new PettHttpServer(port);
            httpServer.run();
        } catch (ParseException | NumberFormatException e) {

            HelpFormatter helpFrm = new HelpFormatter();
            helpFrm.printHelp("java -jar " + getJarName(), MetadataInfo.getDescription(), argOptions, HELP_FOOTER, true);
        }
    }
}
