package org.cwld.pett;

/**
 * Class that provides a health reading for the server
 */
public class ServerHealth {

    public enum HealthStatus {
        PASS,
        WARN,
        FAIL
    };

    /**
     * Returns a health status based on the current memory usage in the JVM
     * @return The current health status
     */
    public static HealthStatus getCurrentHealth() {
        Runtime currentRt = Runtime.getRuntime();

        long memPercentage = (currentRt.totalMemory() * 100)/currentRt.maxMemory();

        if (memPercentage >= 99) {
            return HealthStatus.FAIL;
        } else if (memPercentage >= 90) {
            return HealthStatus.WARN;
        }
        return HealthStatus.PASS;
    }
}
