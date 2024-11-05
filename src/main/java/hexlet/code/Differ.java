package hexlet.code;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;
import java.nio.file.Files;

public class Differ { //основной класс
    public static String generate(String filepath1, String filepath2, String format) throws Exception {
        String content1 = readFile(filepath1);
        String content2 = readFile(filepath2);

        String fileFormat1 = getFileType(filepath1);
        String fileFormat2 = getFileType(filepath2);

        Map<String, Object> file1 = Parser.parse(content1, fileFormat1);
        Map<String, Object> file2 = Parser.parse(content2, fileFormat2);

        List<Map<String, Object>> compareResult = Comparator.compare(file1, file2);
        return StylishFormatter.format((compareResult));
    }

    private static String readFile(String filepath) throws IOException {
        Path pathOfFile = Path.of(filepath);
        return Files.readString(pathOfFile);
    }

    private static String getFileType(String filepath) {
        String[] resultOfSplit = filepath.split("\\.");
        String format = resultOfSplit[resultOfSplit.length - 1];

        //возвращает расширение файла (сплит по точке и взять последний элемент) (json, yml, yaml)
        return format;
    }

    public static String format(List<Map<String, Object>> compareResult, String format) throws Exception {
        return switch (format) {
            case "stylish" -> StylishFormatter.format(compareResult);
            default -> throw new RuntimeException("not correct format");
        };
    }
}
