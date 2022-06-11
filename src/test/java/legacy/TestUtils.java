package legacy;

import java.math.BigDecimal;

public class TestUtils {

    public static Money pln(Integer amount) {
        return new Money(BigDecimal.valueOf(amount).stripTrailingZeros(), Currency.PLN);
    }

    public static Money pln(Double amount) {
        return new Money(BigDecimal.valueOf(amount).stripTrailingZeros(), Currency.PLN);
    }

    public static Percent percent(Integer amount) {
        return new Percent(BigDecimal.valueOf(amount).stripTrailingZeros());
    }
}
