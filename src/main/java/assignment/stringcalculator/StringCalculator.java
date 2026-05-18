package assignment.stringcalculator;

public class StringCalculator {
    public int add(String input) {
        if (input.isEmpty()) {
            return 0;
        }
        return input.length();
    }
}
