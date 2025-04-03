package io.accelerate.challenge.checker.assertion;

import java.util.ArrayList;
import java.util.List;

public class StringUtils {


    static String stripTrailingSpaces(String value) {
        // Split into multiple lines
        String[] lines = value.split("\\r?\\n");
        List<String> resultLines = new ArrayList<>();

        for (String line : lines) {
            // Remove trailing spaces
            resultLines.add(line.replaceAll("\\s+$", ""));
        }
        // Join the lines back into a single string
        return String.join("\n", resultLines);
    }

    static String removeEmptyLines(String s) {
        // Split into multiple lines
        String[] lines = s.split("\\r?\\n");
        List<String> resultLines = new ArrayList<>();

        for (String line : lines) {
            // Add non-empty lines to the list
            if (!line.isEmpty()) {
                resultLines.add(line);
            }
        }
        // Join the lines back into a single string
        return String.join("\n", resultLines);
    }
}
