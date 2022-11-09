package refactored


class DiscountCalculator {
    private val logger: Logger

    constructor() {
        logger = Logger.Sout()
    }

    constructor(logger: Logger) {
        this.logger = logger
    }

    fun calculateDiscount(money: Money, strategy: DiscountStrategy): Money? {
        if (strategy is DiscountStrategy.Fixed) {
            val fixedStrategy = strategy
            val amount = fixedStrategy.value
            val currency = fixedStrategy.value.currency
            logger.logInfo("Calculating fixed discount: $amount ${currency.value}")
        } else if (strategy is DiscountStrategy.Percentage) {
            logger.logInfo(
                    "Calculating percentage discount: ${strategy.value} percent"
            )
        } else {
            throw RuntimeException("Unknown strategy type")
        }
        return strategy.calculateDiscountedValue(money)
    }
}