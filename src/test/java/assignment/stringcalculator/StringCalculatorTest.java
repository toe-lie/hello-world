package assignment.stringcalculator;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class StringCalculatorTest {

    @Test
    void empty_string_return_zero() {
        StringCalculator calculator = new StringCalculator();
        int result = calculator.add("");
        assertThat(result, is(0));
    }

    @Test
    void single_number_return_that_number() {
        StringCalculator calculator = new StringCalculator();
        int result = calculator.add("1");
        assertThat(result, is(1));
    }
}
