package legacy;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

public final class Percent {

    private final static BigDecimal FRACTION_DIVISOR = BigDecimal.valueOf(100);

    private final BigDecimal value;

    public Percent(BigDecimal value) {
        this.value = value;
    }

    public BigDecimal getValue() {
        return value;
    }

    public BigDecimal getFractionalValue() {
        return value.divide(FRACTION_DIVISOR, this.value.scale() + 2, RoundingMode.HALF_UP);
    }

    public static Percent of(BigDecimal value) {
        return new Percent(value);
    }

    @Override
    public String toString() {
        return "legacy.Percent{" +
                "value=" + value +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Percent percent = (Percent) o;
        return Objects.equals(value, percent.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
