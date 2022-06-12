package legacy;

import java.time.Instant;
import java.util.Objects;

abstract class AppliedDiscount {

    private final Long id;
    private final Money amount;
    private final UserId userId;
    private final Instant applicationTime;

    protected AppliedDiscount(Long id, Money amount, UserId userId, Instant applicationTime) {
        this.id = id;
        this.amount = amount;
        this.userId = userId;
        this.applicationTime = applicationTime;
    }

    public Money getAmount() {
        return amount;
    }

    public UserId getUserId() {
        return userId;
    }

    public Long getId() {
        return id;
    }

    public Instant getApplicationTime() {
        return applicationTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AppliedDiscount that = (AppliedDiscount) o;
        return Objects.equals(id, that.id) && Objects.equals(amount, that.amount) && Objects.equals(userId, that.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, amount, userId);
    }
}
