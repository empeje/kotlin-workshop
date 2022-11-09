package refactored

interface AppliedDiscountRepository {
    fun add(discount: AppliedDiscount) : Unit
    fun findAll(): List<AppliedDiscount>
    fun findForUser(userId: UserId): List<AppliedDiscount>
    fun findForUserGroupedByType(userId: UserId): Map<String, List<AppliedDiscount>>
    fun findLastAppliedDiscountForUser(userId: UserId): AppliedDiscount?
}