package assignment.stringcalculator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class StringCalculatorTest {

    private StringCalculator calculator;

    @BeforeEach
    void setUp() {
        calculator = new StringCalculator();
    }

    @Test
    void empty_string_return_zero() {
        int result = calculator.add("");
        assertThat(result, is(0));
    }

    @Test
    void single_number_return_that_number() {
        int result = calculator.add("5");
        assertThat(result, is(5));
    }
}
