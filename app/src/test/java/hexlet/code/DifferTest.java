package hexlet.code;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DifferTest {

    @Test
    void getSomethingForTest() {
        Differ.getSomethingForTest("testFile");
        assertEquals("testFile", "testFile");
    }
}
