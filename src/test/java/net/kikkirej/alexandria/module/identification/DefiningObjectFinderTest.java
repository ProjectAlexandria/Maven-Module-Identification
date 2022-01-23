package net.kikkirej.alexandria.module.identification;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;

class DefiningObjectFinderTest {

    //SUT
    private DefiningObjectFinder definingObjectFinder;

    @BeforeEach
    void setUp() {
        definingObjectFinder = new DefiningObjectFinder();
    }

    @Test
    void semicolonSeparatedPomDirectoriesIn() {
        File analysisDir = new File("./example-structure");
        Collection<String> result = definingObjectFinder.moduleDirectoriesIn(analysisDir, "pom.xml");
        assertTrue(result.contains("/"));
        assertTrue(result.contains("/module1"));
        assertTrue(result.contains("/module2"));
        assertTrue(result.contains("/module2/submodule"));
    }
}