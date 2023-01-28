package tets;

import main.extensions.Extensions;
import org.junit.Before;
import org.junit.Test;

import java.util.UUID;

import static org.junit.Assert.*;

public class ExtensionTest {
    UUID id;
    String stringId;
    String stringInt;
    String stringFloat;
    String string;


    @Before
    public void setUp() {
        id = UUID.randomUUID();
        stringId = id.toString();
        string = "Brazil";
        stringInt = "5";
        stringFloat = "10.99";
    }

    @Test
    public void testParseId() {
        assertEquals(id, Extensions.parseId(stringId));
        assertNull(Extensions.parseId(string));
    }

    @Test
    public void testParseInt() {
        assertEquals(5, Extensions.parseInt(stringInt));
        assertEquals(0, Extensions.parseInt(string));
    }

    @Test
    public void testParseFloat() {
        assertNotEquals(0, Extensions.parseFloat(stringFloat));
    }
}
