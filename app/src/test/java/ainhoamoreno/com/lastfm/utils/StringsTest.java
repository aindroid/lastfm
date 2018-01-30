package ainhoamoreno.com.lastfm.utils;

import org.junit.Test;

import static org.junit.Assert.*;

public class StringsTest {

    @Test
    public void isEmpty() throws Exception {
        assertTrue(Strings.isEmpty(""));
    }

    @Test
    public void isEmpty_checkExtraSpaces() throws Exception {
        assertTrue(Strings.isEmpty(" "));
    }

    @Test
    public void isEmpty_false() throws Exception {
        assertTrue(!Strings.isEmpty(" no"));
    }
}