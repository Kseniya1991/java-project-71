package hexlet.code;

import java.util.List;
import java.util.Map;
import java.util.TreeSet;

public class Comparator {

    public static List<Map<String, Object>> compare(Map<String, Object> file1, Map<String, Object> file2) {
        //как сравнивать
        var keys = new TreeSet<>();
        keys.addAll(file1.keySet());
        keys.addAll(file2.keySet());
        return List.of(Map.of());

    }
}
