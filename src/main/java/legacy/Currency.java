package legacy;

import java.util.Objects;

public final class Currency {

    public final static Currency PLN = Currency.of("PLN");
    public final static Currency EUR = Currency.of("EUR");

    private final String value;

    public Currency(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static Currency of(String value) {
        return new Currency(value);
    }

    @Override
    public String toString() {
        return "legacy.Currency{" +
                "value='" + value + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Currency currency = (Currency) o;
        return Objects.equals(value, currency.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
