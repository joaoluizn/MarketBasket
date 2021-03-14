package utils;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class MathUtils {

    public static BigDecimal round(BigDecimal value, BigDecimal increment, RoundingMode roundingMode) {
        // Prevents division by 0.
        if (increment.signum() == 0) {
            return value;
        } else {
            BigDecimal divided = value.divide(increment, 0, roundingMode);
            return divided.multiply(increment);
        }
    }

    public static BigDecimal decimal(String input) {
        return new BigDecimal(input);
    }
}
