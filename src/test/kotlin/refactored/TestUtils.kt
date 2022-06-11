package refactored

import java.math.BigDecimal

fun pln(amount: Double) = Money(BigDecimal.valueOf(amount).stripTrailingZeros(), Currency("PLN"))
fun pln(amount: Int) = pln(amount.toDouble())
fun percent(amount: Int) = Percent(BigDecimal.valueOf(amount.toLong()).stripTrailingZeros())