package refactored

import java.math.BigDecimal
import java.time.Instant

val TEST_TIME = Instant.parse("2018-11-30T18:35:24.00Z")

fun pln(amount: Double) = Money(BigDecimal.valueOf(amount).stripTrailingZeros(), Currency("PLN"))
fun pln(amount: Int) = pln(amount.toDouble())
fun percent(amount: Int) = Percent(BigDecimal.valueOf(amount.toLong()).stripTrailingZeros())