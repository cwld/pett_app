package org.cwld.pett;

import com.jcabi.manifests.Manifests;

/**
 * Class to hold static metadata information for the application
 */
public final class MetadataInfo {

    /**
     * Metadata attribute names
     */
    private final static String VERSION_ATTRIBUTE = "Application-Version";
    private final static String COMMIT_HASH_ATTRIBUTE = "Commit-Hash";

    /**
     * Description of the application
     */
    private final static String APPLICATION_DESCRIPTION = "Pre-interview Technical Test";

    /**
     * Retrieves the version information built into the manifest
     * @return The version string for this build
     */
    public static final String getVersion() {
        try {
            return Manifests.read(VERSION_ATTRIBUTE);
        } catch (IllegalArgumentException e) {
            return "INVALID_VERSION";
        }
    }

    /**
     * Gets a simple description of the application
     * @return A short string description of the application
     */
    public static final String getDescription() {
        return APPLICATION_DESCRIPTION;
    }

    /**
     * Gets the commit hash that this application was built from
     * @return A string containing the git commit hash that this application was built from
     */
    public static final String getCommitHash() {
        try {
            return Manifests.read(COMMIT_HASH_ATTRIBUTE);
        } catch (IllegalArgumentException e) {
            return "INVALID_COMMIT_HASH";
        }
    }
}
