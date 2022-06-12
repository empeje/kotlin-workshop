package legacy;

import java.math.BigDecimal;
import java.time.Instant;

public class TestUtils {

    public static Instant TEST_TIME = Instant.parse("2018-11-30T18:35:24.00Z");

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
