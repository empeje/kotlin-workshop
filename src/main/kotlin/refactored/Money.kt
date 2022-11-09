package refactored

import java.math.BigDecimal
import java.util.*

data class Money(val value: BigDecimal, val currency: Currency) {
    fun plus(other: Money): Money {
        return if (currency == other.currency) {
            of(value?.add(other.value), currency)
        } else {
            throw IllegalStateException("Can't add two legacy.Money values. Currencies are different.")
        }
    }

    fun minus(other: Money?): Money {
        return if (currency == other!!.currency) {
            of(value?.subtract(other.value), currency)
        } else {
            throw IllegalStateException("Can't add two legacy.Money values. Currencies are different.")
        }
    }

    fun times(percent: Percent): Money {
        return of(
            value?.multiply(percent.fractionalValue).stripTrailingZeros(),
            currency
        )
    }

    companion object {
        fun of(value: BigDecimal, currency: Currency): Money {
            return Money(value, currency)
        }
    }
}