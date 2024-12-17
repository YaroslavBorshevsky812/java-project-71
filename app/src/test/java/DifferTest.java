import hexlet.code.Differ;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
public class DifferTest {

    @Test
    public void getFile() {
        Differ.getSomethingForTest("testFile");
        assertEquals("testFile", "testFile");
    }
}
