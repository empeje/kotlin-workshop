package refactored

import refactored.AppliedDiscountRepository

class InMemoryAppliedDiscountRepository: AppliedDiscountRepository {
    var values = mutableListOf<AppliedDiscount>();

    override fun add(discount: AppliedDiscount) : Unit {
        values.add(discount)
    }

    override fun findAll(): List<AppliedDiscount> {
        return values
    }

    override fun findForUser(userId: UserId): List<AppliedDiscount> {
        return values.filter { it.userId == userId }
    }
    override fun findForUserGroupedByType(userId: UserId): Map<String, List<AppliedDiscount>> {
        return values.groupBy { }
    }
    override fun findLastAppliedDiscountForUser(userId: UserId): AppliedDiscount? {
        return values.sortBy {  }
    }
}