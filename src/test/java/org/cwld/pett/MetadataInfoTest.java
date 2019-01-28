package org.cwld.pett;

import com.jcabi.manifests.Manifests;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Tests the metadata info functions
 */
public class MetadataInfoTest {
    @BeforeClass
    public static void setUp() {
        TestConstants.populateManifest();
    }

    @Test
    public void getVersion() {
        assertEquals(TestConstants.TEST_VERSION, MetadataInfo.getVersion());
    }

    @Test
    public void getDescription() {
        // Description is just a hardcoded string
        assertEquals(TestConstants.APPLICATION_DESCRIPTION, MetadataInfo.getDescription());
        // Check that it is not null though
        assertNotNull(MetadataInfo.getDescription());
    }

    @Test
    public void getCommitHash() {
        assertEquals(TestConstants.TEST_COMMIT_HASH, MetadataInfo.getCommitHash());
    }
}