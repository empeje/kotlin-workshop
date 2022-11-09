package refactored

import java.time.Instant

class PercentageDiscount(
    override val id: Long,
    override val amount: Money,
    override val userId: UserId,
    override val applicationTime: Instant
) : AppliedDiscount() {
    abstract val percent: Percent

    constructor(
        id: Long,
        override val amount: Money,
        override val userId: UserId,
        override val percent: Percent,
        override val applicationTime: Instant
    ) : AppliedDiscount() {
        super(id, amount, userId, applicationTime)
        this.percent = percent
    }
}