package refactored

import java.math.BigDecimal
import java.math.RoundingMode

@JvmInline
value class Currency(val value: String) {

    companion object {
        val PLN = Currency("PLN")
        val EUR = Currency("EUR")
    }
}

@JvmInline
value class Percent(val value: BigDecimal) {

    companion object {
        private val FRACTION_DIVISOR = BigDecimal.valueOf(100)
    }

    fun getFractionalValue(): BigDecimal =
        value.divide(FRACTION_DIVISOR, value.scale() + 2, RoundingMode.HALF_UP)
}

data class Money(val value: BigDecimal, val currency: Currency) {
    operator fun plus(other: Money): Money {
        check(currency == other.currency) {
            "Can't add two Money values. Currencies are different."
        }
        return Money(value.add(other.value), currency)
    }


    operator fun minus(other: Money): Money {
        check(currency == other.currency) {
            "Can't add two Money values. Currencies are different."
        }
        return Money(value.subtract(other.value), currency)
    }

    operator fun times(percent: Percent): Money = Money(
        value = value.multiply(percent.getFractionalValue()).stripTrailingZeros(),
        currency = currency
    )
}

@JvmInline
value class UserId(val value: Long)