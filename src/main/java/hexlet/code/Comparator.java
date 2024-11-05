package hexlet.code;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

public class Comparator {

    public static List<Map<String, Object>> compare(Map<String, Object> file1, Map<String, Object> file2) {
        SortedMap<String, Object> sortedMap = new TreeMap<>();
        List<Map<String, Object>> result = new ArrayList<>();
        var keys1 = file1.keySet();
        var keys2 = file2.keySet();

        for (var key2 : keys2) {
            if (file1.containsKey(key2)) {
                if (file1.get(key2).equals(file2.get(key2))) {
                    sortedMap.put(key2, "unchanged");
                } else {
                    sortedMap.put(key2, "changed");
                }
            } else {
                sortedMap.put(key2, "added");
            }
        }

        for (var key1 : keys1) {
            if (!keys2.contains(key1)) {
                sortedMap.put(key1, "deleted");
            }
        }

        var entries = sortedMap.entrySet();
        for (var entry : entries) {
            SortedMap<String, Object> temp = new TreeMap<>();
            switch ((String) entry.getValue()) {
                case "changed": temp.put("field", entry.getKey());
                    temp.put("status", entry.getValue());
                    temp.put("old value", file1.get(entry.getKey()));
                    temp.put("new value", file2.get(entry.getKey()));
                    break;
                case "deleted", "unchanged": temp.put("field", entry.getKey());
                    temp.put("status", entry.getValue());
                    temp.put("old value", file1.get(entry.getKey()));
                    break;
                case "added": temp.put("field", entry.getKey());
                    temp.put("status", entry.getValue());
                    temp.put("new value", file2.get(entry.getKey()));
                    break;
                default:
                    break;
            }
            result.add(temp);

        } //в лист преобразовать только отсортированную по порядку мапу

        return result;
    }
}
