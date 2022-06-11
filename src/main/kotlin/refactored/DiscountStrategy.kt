package refactored

import java.math.BigDecimal

sealed interface DiscountStrategy {

    fun calculateDiscountedValue(amount: Money): Money

    class Fixed(val value: Money) : DiscountStrategy {

        override fun calculateDiscountedValue(amount: Money): Money {
            val discountedValue = amount.minus(value)
            return if (discountedValue.value > BigDecimal.ZERO) {
                discountedValue
            } else {
                Money(BigDecimal.ZERO, amount.currency)
            }
        }
    }

    class Percentage(val percent: Percent) : DiscountStrategy {

        override fun calculateDiscountedValue(amount: Money): Money =
            amount.minus(amount.times(percent))
    }
}