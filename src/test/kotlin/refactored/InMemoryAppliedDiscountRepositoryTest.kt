package refactored


import assertk.assertThat
import assertk.assertions.hasSize
import assertk.assertions.isEqualTo
import assertk.assertions.isNotNull
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.time.Instant


class InMemoryAppliedDiscountRepositoryTest {

    private lateinit var repository: InMemoryAppliedDiscountRepository

    @BeforeEach
    fun setup() {
        repository = InMemoryAppliedDiscountRepository()
    }

    @Test
    fun `should return all discounts`() {
        // given
        thereAreDiscounts(
            aFixedDiscount(1),
            aFixedDiscount(2),
            aFixedDiscount(3)
        )

        // when
        val result = repository.findAll()

        // then
        assertThat(result).apply {
            hasSize(3)
            isEqualTo(
                listOf(
                    aFixedDiscount(1),
                    aFixedDiscount(2),
                    aFixedDiscount(3)
                )
            )
        }

    }

    @Test
    fun shouldFindDiscountsForUser() {
        // given
        thereAreDiscounts(
            aFixedDiscount(1, userId = 1),
            aFixedDiscount(2, userId = 1),
            aFixedDiscount(3, userId = 2)
        )

        // when
        val result = repository.findForUser(UserId(1L))

        //then
        assertThat(result).apply {
            hasSize(2)
            isEqualTo(
                listOf(
                    aFixedDiscount(1),
                    aFixedDiscount(2)
                )
            )
        }
    }

    @Test
    fun shouldFindForUserGroupedByType() {
        // given
        thereAreDiscounts(
            aFixedDiscount(1, userId = 1),
            aPercentageDiscount(2, userId = 1),
            aFixedDiscount(3, userId = 2)
        )

        // when
        val result = repository.findForUserGroupedByType(UserId(1L))

        //then
        assertThat(result).hasSize(2)

        assertThat(result["fixed"]!!).apply {
            hasSize(2)
            isEqualTo(listOf(
                aFixedDiscount(1, userId = 1),
                aFixedDiscount(3, userId = 2)
            ))
        }

        assertThat(result["percentage"]!!).apply {
            hasSize(1)
            isEqualTo(listOf(
                aPercentageDiscount(2, userId = 1)
            ))
        }
    }

    @Test
    fun shouldFindLastAppliedDiscountForUser() {
        // given
        thereAreDiscounts(
            aFixedDiscount(1, userId = 1, applicationTime = TEST_TIME.plusSeconds(10)),
            aPercentageDiscount(2, userId = 1, applicationTime = TEST_TIME.plusSeconds(30)),
            aFixedDiscount(3, userId = 1, applicationTime = TEST_TIME.plusSeconds(20))
        )

        // when
        val result = repository.findLastAppliedDiscountForUser(UserId(1L))

        // then
        assertThat(result).apply {
            isNotNull()
            isEqualTo(aPercentageDiscount(2, userId = 1, applicationTime = TEST_TIME.plusSeconds(30)))
        }
    }

    private fun thereAreDiscounts(vararg discounts: AppliedDiscount) {
        discounts.forEach(repository::add)
    }

    private fun aFixedDiscount(
        id: Int,
        amount: Int = 10,
        applicationTime: Instant = TEST_TIME,
        userId: Int = 1
    ) = FixedDiscount(
        id = id.toLong(),
        amount = pln(amount),
        applicationTime = applicationTime,
        userId = UserId(userId.toLong())
    )

    private fun aPercentageDiscount(
        id: Int,
        amount: Int = 10,
        applicationTime: Instant = TEST_TIME,
        userId: Int = 1,
        percent: Int = 10
    ) = PercentageDiscount(
        id = id.toLong(),
        amount = pln(amount),
        applicationTime = applicationTime,
        userId = UserId(userId.toLong()),
        percent = percent(percent)
    )
}