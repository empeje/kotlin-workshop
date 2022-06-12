package legacy;

import java.time.Instant;

public class PercentageDiscount extends AppliedDiscount {

    private final Percent percent;

    public PercentageDiscount(Long id, Money amount, UserId userId, Percent percent, Instant applicationTime) {
        super(id, amount, userId, applicationTime);
        this.percent = percent;
    }

    public Percent getPercent() {
        return percent;
    }
}
