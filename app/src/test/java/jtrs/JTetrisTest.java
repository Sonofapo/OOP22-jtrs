/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package jtrs;

import org.junit.jupiter.api.Test;

import jtrs.JTetris;

import static org.junit.jupiter.api.Assertions.*;

class JTetrisTest {
    @Test void appHasAGreeting() {
        JTetris classUnderTest = new JTetris();
        assertNotNull(classUnderTest.getGreeting(), "app should have a greeting");
    }
}
