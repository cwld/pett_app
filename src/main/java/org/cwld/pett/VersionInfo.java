package org.cwld.pett;

import com.jcabi.manifests.Manifests;

/**
 * Class to hold static version information for the application
 */
public final class VersionInfo {

    /**
     * Retrieves the version information built into the manifest
     * @return  The version string for this build
     */
    public static final String getVersion() {
        return Manifests.read("Application-Version");
    }
}
