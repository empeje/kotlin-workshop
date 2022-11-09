package refactored

import java.time.Instant

sealed class AppliedDiscount() {
    abstract val id: Long
    abstract val amount: Money
    abstract val userId: UserId
    abstract val applicationTime: Instant

    class FixedDiscount(
        override val id: Long,
        override val amount: Money,
        override val userId: UserId,
        override val applicationTime: Instant
    ) : AppliedDiscount()

    class PercentageDiscount(
        override val id: Long,
        override val amount: Money,
        override val userId: UserId,
        override val applicationTime: Instant
    ) : AppliedDiscount()
}