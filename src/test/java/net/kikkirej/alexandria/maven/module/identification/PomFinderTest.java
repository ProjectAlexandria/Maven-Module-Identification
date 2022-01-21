package net.kikkirej.alexandria.maven.module.identification;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

class PomFinderTest {

    //SUT
    private PomFinder pomFinder;

    @BeforeEach
    void setUp() {
        pomFinder = new PomFinder();
    }

    @Test
    void semicolonSeparatedPomDirectoriesIn() {
        File analysisDir = new File("./example-structure");
        String result = pomFinder.semicolonSeparatedPomDirectoriesIn(analysisDir);
        assertTrue(result.contains("/module1"));
        assertTrue(result.contains("/module2"));
        assertTrue(result.contains("/module2/submodule"));
    }
}