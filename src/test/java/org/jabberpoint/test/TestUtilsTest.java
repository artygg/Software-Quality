package org.jabberpoint.test;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for the TestUtils class.
 */
public class TestUtilsTest {
    
    @Test
    public void testSetupHeadlessEnvironment() {
        // This test just verifies that the method doesn't throw an exception
        assertDoesNotThrow(() -> TestUtils.setupHeadlessEnvironment(),
            "setupHeadlessEnvironment should not throw an exception");
    }
    
    @Test
    public void testResetHeadlessEnvironment() {
        // This test just verifies that the method doesn't throw an exception
        assertDoesNotThrow(() -> TestUtils.resetHeadlessEnvironment(),
            "resetHeadlessEnvironment should not throw an exception");
    }
} 