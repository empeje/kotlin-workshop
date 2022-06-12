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
    fun plus(other: Money): Money =
        if (currency == other.currency) {
            Money(value.add(other.value), currency)
        } else {
            throw IllegalStateException("Can't add two Money values. Currencies are different.")
        }

    fun minus(other: Money): Money =
        if (currency == other.currency) {
            Money(value.subtract(other.value), currency)
        } else {
            throw IllegalStateException("Can't add two Money values. Currencies are different.")
        }

    fun times(percent: Percent): Money = Money(
        value = value.multiply(percent.getFractionalValue()).stripTrailingZeros(),
        currency = currency
    )
}

@JvmInline
value class UserId(val value: Long)