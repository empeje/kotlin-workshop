package refactored

interface AppliedDiscountRepository {
    fun add(discount: AppliedDiscount)
    fun findAll(): List<AppliedDiscount>
    fun findForUser(userId: UserId): List<AppliedDiscount>
    fun findForUserGroupedByType(userId: UserId): Map<String, List<AppliedDiscount>>
    fun findLastAppliedDiscountForUser(userId: UserId): AppliedDiscount?
}

class InMemoryAppliedDiscountRepository : AppliedDiscountRepository {

    private val values: MutableList<AppliedDiscount> = mutableListOf()

    override fun add(discount: AppliedDiscount) {
        values.add(discount)
    }

    override fun findAll(): List<AppliedDiscount> =
        values.toList()

    override fun findForUser(userId: UserId): List<AppliedDiscount> =
        values.filter { it.userId == userId }

    override fun findForUserGroupedByType(userId: UserId): Map<String, List<AppliedDiscount>> =
        values.groupBy {
            when (it) {
                is FixedDiscount -> "fixed"
                is PercentageDiscount -> "percentage"
            }
        }

    override fun findLastAppliedDiscountForUser(userId: UserId): AppliedDiscount? =
        values.maxByOrNull { it.applicationTime }

}