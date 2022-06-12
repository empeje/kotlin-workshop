package legacy;

import java.time.Instant;

public class FixedDiscount extends AppliedDiscount {

    public FixedDiscount(Long id, Money amount, UserId userId, Instant applicationTime) {
        super(id, amount, userId, applicationTime);
    }
}
