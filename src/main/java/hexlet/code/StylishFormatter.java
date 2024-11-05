package hexlet.code;

import java.util.List;
import java.util.Map;

public class StylishFormatter {

    public static String format(List<Map<String, Object>> compareResult) throws Exception {
        String result = "";

        for (var map : compareResult) {
            switch ((String) map.get("status")) {
                case "deleted": result += "- " + map.get("field") + ": " + map.get("old value") + "\n";
                    break;
                case "unchanged": result += "  " + map.get("field") + ": " + map.get("old value") + "\n";
                    break;
                case "changed": result += "- " + map.get("field") + ": " + map.get("old value") + "\n";
                    result += "+ " + map.get("field") + ": " + map.get("new value") + "\n";
                    break;
                case "added": result += "+ " + map.get("field") + ": " + map.get("new value") + "\n";
                    break;
                default:
                    break;
            }
        }
        return "{\n" + result + "}";
    }
}
