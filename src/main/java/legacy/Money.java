package legacy;

import java.math.BigDecimal;
import java.util.Objects;

public final class Money {

    private final BigDecimal value;
    private final Currency currency;

    public Money(BigDecimal value, Currency currency) {
        if(value == null) {
            throw new IllegalStateException("Value cannot be null");
        }
        if(currency == null) {
            throw new IllegalStateException("legacy.Currency cannot be null");
        }
        this.value = value;
        this.currency = currency;
    }

    public BigDecimal getValue() {
        return value;
    }

    public Currency getCurrency() {
        return currency;
    }

    public Money plus(Money other) {
        if (this.currency.equals(other.currency)) {
            return Money.of(this.value.add(other.value), this.currency);
        } else {
            throw new IllegalStateException("Can't add two legacy.Money values. Currencies are different.");
        }
    }

    public Money times(Percent percent) {
        return Money.of(
                this.value.multiply(percent.getFractionalValue()).stripTrailingZeros(),
                this.currency
        );
    }

    public static Money of(BigDecimal value, Currency currency) {
        return new Money(value, currency);
    }

    @Override
    public String toString() {
        return "legacy.Money{" +
                "value=" + value +
                ", currency=" + currency +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Money money = (Money) o;
        return Objects.equals(value, money.value) && Objects.equals(currency, money.currency);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value, currency);
    }
}
