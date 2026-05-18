package assignment.stringcalculator;

public class StringCalculator {
    public int add(String input) {
        if (input.isEmpty()) {
            return 0;
        }
        final String[] chunks = input.split(",");
        int sum = 0;
        for (String chunk : chunks) {
            sum += Integer.parseInt(chunk);
        }
        return sum;
    }
}
