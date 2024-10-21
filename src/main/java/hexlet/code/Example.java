package hexlet.code;

import java.util.*;

public class Example {
    public static void main(String[] args) throws Exception {
        var data1 = new HashMap<String, Object>(
                Map.of("host", "hexlet.io",
                "timeout", 50,
                "proxy", "123.234.53.22",
                "follow", false)
        );
        //System.out.println(data1); //=> {two=two, four=true, one=eon}

        var data2 = new HashMap<String, Object>(
                Map.of("timeout", 20,
                "verbose", true,
                "host", "hexlet.io")
        );
        //System.out.println(data2); //=> {zero=4, two=own, four=true}


//        System.out.println(Example.genDiff(data1, data2));
//        System.out.println(format(Example.genDiff(data1, data2)));

        System.out.println(Comparator.compare(data1, data2));
        System.out.println(StylishFormatter.format(Comparator.compare(data1, data2)));

    }

    public static String format(List<Map<String, Object>> compareResult ) {
//        [{field=follow, old value=false, status=deleted},
//        {field=host, old value=hexlet.io, status=unchanged},
//        {field=proxy, old value=123.234.53.22, status=deleted},
//        {field=timeout, new value=20, old value=50, status=changed},
//        {field=verbose, new value=true, status=added}]

        String result = "";

        for (var map : compareResult) {
                switch ((String) map.get("status")) {
                    case "deleted": result+= "- " + map.get("field") + ": " + map.get("old value") + "\n";
                        break;
                    case "unchanged": result+= "  " + map.get("field") + ": " + map.get("old value") + "\n";
                        break;
                    case "changed": result+= "- " + map.get("field") + ": " + map.get("old value") + "\n";
                        result+= "+ " + map.get("field") + ": " + map.get("new value") + "\n";
                        break;
                    case "added": result+= "+ " + map.get("field") + ": " + map.get("new value") + "\n";
                        break;
                    default:
                        break;
                }
            }
        return "{\n" + result + "}";
    }

    public static List<Map<String, Object>> genDiff(Map<String, Object> file1, Map<String, Object> file2) {
        SortedMap<String, Object> sortedMap = new TreeMap<>();
        List<Map<String, Object>> result = new ArrayList<>();
        var keys1 = file1.keySet();
        var keys2 = file2.keySet();

        for (var key2 : keys2) { //сравниваем вторую мапу с первой
            if (file1.containsKey(key2)) {
                if (file1.get(key2).equals(file2.get(key2))) {
                    sortedMap.put(key2,"unchanged");
                } else {
                    sortedMap.put(key2, "changed");
                }
            } else {
                sortedMap.put(key2, "added");
            }
        }

        for (var key1 : keys1) {
            if(!keys2.contains(key1)) {
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
        //[{field=follow, old value=false, status=deleted},
        // {field=host, old value=hexlet.io, status=unchanged},
        // {field=proxy, old value=123.234.53.22, status=deleted},
        // {field=timeout, new value=20, old value=50, status=changed},
        // {field=verbose, new value=null----------------, status=added}]
    }
}

//[
//        {
//        "FIELD": "host",
//        "STATUS": "SAME",
//        "OLD_VALUE": "hexlet.io"
//        },
//        {
//        "FIELD": "array",
//        "STATUS": "SAME",
//        "OLD_VALUE": [
//        1,
//        2
//        ]
//        },
//        {
//        "FIELD": "follow",
//        "STATUS": "REMOVED",
//        "OLD_VALUE": false
//        }
//        ]
