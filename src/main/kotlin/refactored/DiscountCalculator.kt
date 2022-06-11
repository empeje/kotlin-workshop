package refactored

class DiscountCalculator(
    val logger: Logger = Logger.Sout
) {

    fun calculateDiscount(money: Money, strategy: DiscountStrategy): Money {
        when(strategy) {
            is DiscountStrategy.Fixed -> {
                val (amount, currency) = (strategy.value)
                logger.logInfo("Calculating fixed discount: $amount ${currency.value}")
            }
            is DiscountStrategy.Percentage -> logger.logInfo("Calculating percentage discount: ${strategy.percent.value}")
        }
        return strategy.calculateDiscountedValue(money)
    }
}