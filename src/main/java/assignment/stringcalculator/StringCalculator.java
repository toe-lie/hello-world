package assignment.stringcalculator;

import java.util.ArrayList;import java.util.List;import java.util.regex.Matcher;import java.util.regex.Pattern;

public class StringCalculator {
    public int add(String input) {
        final List<Integer> numbers = new ArrayList<>();
        final Pattern pattern = Pattern.compile("\\d+");
        final  Matcher matcher = pattern.matcher(input);

        while (matcher.find()) {
            numbers.add(Integer.parseInt(matcher.group()));
        }
        return numbers.stream().mapToInt(Integer::intValue).sum();
    }
}
