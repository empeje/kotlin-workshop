package refactored

import java.time.Instant

sealed class AppliedDiscount {
    abstract val id: Long
    abstract val amount: Money
    abstract val applicationTime: Instant
    abstract val userId: UserId
}

data class FixedDiscount(
    override val id: Long,
    override val amount: Money,
    override val applicationTime: Instant,
    override val userId: UserId
) : AppliedDiscount()

data class PercentageDiscount(
    override val id: Long,
    override val amount: Money,
    override val applicationTime: Instant,
    override val userId: UserId,
    val percent: Percent
) : AppliedDiscount()
