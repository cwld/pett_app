package org.cwld.pett;

import com.jcabi.manifests.Manifests;
import org.junit.Test;

import static org.junit.Assert.*;

public class MetadataInfoTest {
    private final static String TEST_VERSION = "1.2.3-4";
    private final static String TEST_COMMIT_HASH = "1234cfg";

    @Test
    public void getVersion() {
        // Add a test version into the manifest
        Manifests.DEFAULT.put(MetadataInfo.VERSION_ATTRIBUTE, TEST_VERSION);

        assertEquals(TEST_VERSION, MetadataInfo.getVersion());
    }

    @Test
    public void getDescription() {
        // Description is just a hardcoded string
        assertEquals(MetadataInfo.APPLICATION_DESCRIPTION, MetadataInfo.getDescription());
        // Check that it is not null though
        assertNotNull(MetadataInfo.getDescription());
    }

    @Test
    public void getCommitHash() {
        // Add a test commit hash into the manifest
        Manifests.DEFAULT.put(MetadataInfo.COMMIT_HASH_ATTRIBUTE, TEST_COMMIT_HASH);

        assertEquals(TEST_COMMIT_HASH, MetadataInfo.getCommitHash());
    }
}