package refactored

import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.math.BigDecimal

class MoneyTest {

    @Test
    fun `should add two values`() {
        // given
        val oneEur = Money(BigDecimal.valueOf(1), Currency.EUR)
        val tenEur = Money(BigDecimal.valueOf(10), Currency.EUR)

        // when
        val result = oneEur.plus(tenEur)

        // then
        assertThat(result.value).isEqualTo(BigDecimal.valueOf(11))
        assertThat(result.currency).isEqualTo(Currency.EUR)
    }

    @Test
    fun `should subtract two values`() {
        // given
        val oneEur = Money(BigDecimal.valueOf(1), Currency.EUR)
        val tenEur = Money(BigDecimal.valueOf(10), Currency.EUR)

        // when
        val result = tenEur.minus(oneEur)

        // then

        assertThat(result.value).isEqualTo(BigDecimal.valueOf(9))
        assertThat(result.currency).isEqualTo(Currency.EUR)
    }

    @Test
    fun plusOperationIsCommutative() {
        // given
        val oneEur = Money(BigDecimal.valueOf(1), Currency.EUR)
        val tenEur = Money(BigDecimal.valueOf(10), Currency.EUR)

        // when
        val firstResult = oneEur.plus(tenEur)
        val secondResult = tenEur.plus(oneEur)

        // then
        assertThat(firstResult).isEqualTo(secondResult)
    }

    @Test
    fun shouldThrowForPlusOperationWhenCurrenciesDiffer() {
        // given
        val oneEur = Money(BigDecimal.valueOf(1), Currency.EUR)
        val onePln = Money(BigDecimal.valueOf(1), Currency.PLN)

        // when
        val whenBlock = {
            oneEur.plus(
                onePln
            )
        }

        // then
        assertThrows<IllegalStateException>{ whenBlock() }
    }

    @Test
    fun shouldMultiplyByPercent() {
        // given
        val tenEur = Money(BigDecimal.valueOf(10), Currency.PLN)
        val threePercent = Percent(BigDecimal.valueOf(3.5))

        // when
        val result = tenEur.times(threePercent)

        // then
        assertThat(result.value).isEqualTo(BigDecimal.valueOf(0.35))
        assertThat(result.currency).isEqualTo(Currency.PLN)
    }
}