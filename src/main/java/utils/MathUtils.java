package utils;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class MathUtils {

    /**
     * Rounds a {@link BigDecimal} value based on a rounding mode and increment
     *
     * @param value the {@link BigDecimal} to be rounded
     * @param increment the {@link BigDecimal} increment factor
     * @param roundingMode the desired round mode
     * @return the rounded {@link BigDecimal}
     */
    public static BigDecimal round(BigDecimal value, BigDecimal increment, RoundingMode roundingMode) {
        // Prevents division by 0.
        if (increment.signum() == 0) {
            return value;
        } else {
            BigDecimal divided = value.divide(increment, 0, roundingMode);
            return divided.multiply(increment);
        }
    }

    /**
     * Creates a {@link BigDecimal} instance
     *
     * @param input the {@link String} value to be initialized as big decimal
     * @return the new {@link BigDecimal} instance
     */
    public static BigDecimal bigDecimal(String input) throws IllegalArgumentException {
        return new BigDecimal(input);
    }
}
