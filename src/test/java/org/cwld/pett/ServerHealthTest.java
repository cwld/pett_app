package org.cwld.pett;

import org.junit.Test;

import static org.junit.Assert.*;

public class ServerHealthTest {

    /**
     * Tests getting the health status
     */
    @Test
    public void getCurrentHealth() {
        /* We should expect the health within this test to be 'PASS'*/
        assertEquals(ServerHealth.HealthStatus.PASS, ServerHealth.getCurrentHealth());
    }
}