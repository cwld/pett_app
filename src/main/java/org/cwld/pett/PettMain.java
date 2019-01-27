package org.cwld.pett;

// Main entrypoint for the PETT application
public class PettMain {
    public static void main(String args[]) {
        // Disable log outputs for manifests

        System.out.println("PETT application");
        VersionInfo versionInfo = new VersionInfo();
        System.out.println("Version: " + versionInfo.getVersion());
    }
}
