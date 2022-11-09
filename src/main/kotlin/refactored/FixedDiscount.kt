package refactored

import java.time.Instant

class FixedDiscount(
    override val id: Long,
    override val amount: Money,
    override val userId: UserId,
    override val applicationTime: Instant
) : AppliedDiscount()