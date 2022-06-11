package legacy;

import java.math.BigDecimal;

public interface DiscountStrategy {

    Money calculateDiscountedValue(Money amount);

    class Fixed implements DiscountStrategy {

        private final Money value;

        public Fixed(Money value) {
            this.value = value;
        }

        @Override
        public Money calculateDiscountedValue(Money amount) {
            Money discountedValue = amount.minus(value);
            if(discountedValue.getValue().compareTo(BigDecimal.ZERO) > 0) {
                return discountedValue;
            } else {
                return Money.of(BigDecimal.ZERO, amount.getCurrency());
            }
        }

        public Money getValue() {
            return value;
        }
    }

    class Percentage implements DiscountStrategy {

        private final Percent value;

        public Percentage(Percent percentage) {
            this.value = percentage;
        }

        @Override
        public Money calculateDiscountedValue(Money amount) {
            return amount.minus(amount.times(value));
        }

        public Percent getValue() {
            return value;
        }
    }
}
