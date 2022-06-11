package legacy;

public class DiscountCalculator {

    private final Logger logger;


    public DiscountCalculator() {
        logger = new Logger.Sout();
    }

    public DiscountCalculator(Logger logger) {
        this.logger = logger;
    }

    public Money calculateDiscount(Money money, DiscountStrategy strategy) {
        if (DiscountStrategy.Fixed.class.equals(strategy.getClass())) {
            DiscountStrategy.Fixed fixedStrategy = (DiscountStrategy.Fixed) strategy;
            Money amount = fixedStrategy.getValue();
            Currency currency = fixedStrategy.getValue().getCurrency();
            logger.logInfo(String.format("Calculating fixed discount: %s %s", amount.getValue() , currency.getValue()));
        } else if (DiscountStrategy.Percentage.class.equals(strategy.getClass())) {
            logger.logInfo(String.format("Calculating percentage discount: %s percent", ((DiscountStrategy.Percentage) strategy).getValue().getValue()));
        } else {
            throw new RuntimeException("Unknown strategy type");
        }

        return strategy.calculateDiscountedValue(money);
    }
}
