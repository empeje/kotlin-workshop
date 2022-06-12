package refactored

import java.lang.RuntimeException

class DiscountCalculator(
    val logger: Logger = Logger.Sout
) {

    fun calculateDiscount(money: Money, strategy: DiscountStrategy): Money {
        if (strategy is DiscountStrategy.Fixed) {
            val (amount, currency) = (strategy.value)
            logger.logInfo("Calculating fixed discount: $amount ${currency.value}")
        } else if (strategy is DiscountStrategy.Percentage) {
            logger.logInfo("Calculating percentage discount: ${strategy.percent.value}")
        } else {
            throw RuntimeException("Unknown strategy type")
        }
        return strategy.calculateDiscountedValue(money)
    }
}