package hexlet.code;

import java.util.List;
import java.util.Map;

public class Differ {
    public static String generate(String filepath1, String filepath2, String format) {
        String content1 = readFile(filepath1);
        String content2 = readFile(filepath2);

        String fileFormat1 = getFileType(filepath1);
        String fileFormat2 = getFileType(filepath2);

        Map<String, Object> file1 = Parser.parse(content1, fileFormat1);
        Map<String, Object> file2 = Parser.parse(content2, fileFormat2);

        List<Map<String, Object>> compareResult = Comparator.compare(file1, file2);
        return StylishFormatter.format((compareResult));
    }

    private static String readFile(String filepath) {
        //читаем содержимое файла
        return "";
    }

    private static String getFileType(String filepath) {
        //возвращает расширение файла (json, yml, yaml)
        return "";
    }
}
