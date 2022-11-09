package refactored

import java.math.BigDecimal
import java.math.RoundingMode
import java.util.*

class Percent(val value: BigDecimal) {

    val fractionalValue: BigDecimal
        get() = value.divide(FRACTION_DIVISOR, value.scale() + 2, RoundingMode.HALF_UP)

    override fun toString(): String {
        return "legacy.Percent{" +
                "value=" + value +
                '}'
    }

    override fun equals(o: Any?): Boolean {
        if (this === o) return true
        if (o == null || javaClass != o.javaClass) return false
        val percent = o as Percent
        return value == percent.value
    }

    override fun hashCode(): Int {
        return Objects.hash(value)
    }

    companion object {
        private val FRACTION_DIVISOR = BigDecimal.valueOf(100)
        fun of(value: BigDecimal): Percent {
            return Percent(value)
        }
    }
}