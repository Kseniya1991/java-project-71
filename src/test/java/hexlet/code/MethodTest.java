package hexlet.code;

import org.junit.jupiter.api.Test;

public class MethodTest {

    @Test
    public void testCallGenerate() throws Exception {
        String file1 = "src/main/resources/file1.json";
        String file2 = "src/main/resources/file2.json";

        String result = "{\n" +
                "- follow: false\n" +
                "  host: hexlet.io\n" +
                "- proxy: 123.234.53.22\n" +
                "- timeout: 50\n" +
                "+ timeout: 20\n" +
                "+ verbose: true\n" +
                "}";

        if (!Differ.generate(file1, file2, file1).equals(result)) {
            throw new AssertionError("Метод работает неверно!");
        }
    }
}
