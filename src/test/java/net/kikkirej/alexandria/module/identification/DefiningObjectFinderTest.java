package net.kikkirej.alexandria.module.identification;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

class DefiningObjectFinderTest {

    //SUT
    private DefiningObjectFinder pomFinder;

    @BeforeEach
    void setUp() {
        pomFinder = new DefiningObjectFinder();
    }

    @Test
    void semicolonSeparatedPomDirectoriesIn() {
        File analysisDir = new File("./example-structure");
        String result = pomFinder.moduleDirectoriesIn(analysisDir, "pom.xml");
        assertTrue(result.contains("/module1"));
        assertTrue(result.contains("/module2"));
        assertTrue(result.contains("/module2/submodule"));
    }
}