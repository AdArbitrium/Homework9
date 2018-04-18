package edu.gcccd.csis;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.Assert.*;

public class SelfAwareTest {
    private static File tmp;

    // Create tmp file .. runs before each and every test methods
    @Before
    public void setUp() throws Exception {
        tmp = File.createTempFile("tmp", ".tmp");
    }

    // Delete tmp file .. runs after each and every test methods
    @After
    public void tearDown() throws Exception {
        assertTrue(tmp.delete());
    }

    // Test that Strings provided in the StringArray ReservedWords are found and correctly counted in a given String
    @Test
    public void occurrences() throws Exception {
        final SelfAware sa = new SelfAware();
        int k = sa.occurrences(tmp.getAbsolutePath());
        assertEquals(0, k);

        sa.append(tmp.getAbsolutePath(), Language.ReservedWords[0]);
        k = sa.occurrences(tmp.getAbsolutePath());
        assertEquals(1, k);

        // a few more appends followed by assets just like that ...
        // ...

    }

    // Test that append successfully and correctly adds a given string to a file
    @Test
    public void append() throws Exception {
        assertNotNull(tmp);
        final SelfAware sa = new SelfAware();

        final long size0 = Files.size(Paths.get(tmp.toURI()));
        final String s = "// Hello World";
        sa.append(tmp.getAbsolutePath(), s);
        final long size1 = Files.size(Paths.get(tmp.toURI()));
        assertEquals(size0 + s.length(), size1);

        // now verify that the correct string was appended to the file
        // ...
    }
}