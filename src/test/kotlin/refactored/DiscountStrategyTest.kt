package refactored

import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.Test

class DiscountStrategyTest {

    @Test
    fun `should calculate fixed discount`() {
        // given
        val discount = DiscountStrategy.Fixed(pln(5))

        // when
        val discountedValue = discount.calculateDiscountedValue(pln(13))

        // then
        assertThat(discountedValue).isEqualTo(pln(8))
    }

    @Test
    fun `should calculate fixed discount to be always non negative`() {
        // given
        val discount = DiscountStrategy.Fixed(pln(13))

        // when
        val discountedValue = discount.calculateDiscountedValue(pln(5))

        // then
        assertThat(discountedValue).isEqualTo(pln(0))
    }

    @Test
    fun `should calculate percentage discount`() {
        // given
        val discount = DiscountStrategy.Percentage(percent(10))

        // when
        val discountedValue = discount.calculateDiscountedValue(pln(15))

        // then
        assertThat(discountedValue).isEqualTo(pln(13.5))
    }
}