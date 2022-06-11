package refactored


import assertk.assertThat
import assertk.assertions.isEqualTo
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class DiscountCalculatorTest {

    private var discountCalculator = DiscountCalculator()

    @BeforeEach
    fun setup() {
        discountCalculator = DiscountCalculator()
    }

    @Test
    fun `should calculate discount`() {
        // given
        val discountStrategy = DiscountStrategy.Fixed(pln(100))

        // when
        val result = discountCalculator.calculateDiscount(pln(150), discountStrategy)

        // then
        assertThat(result).isEqualTo(pln(50))
    }

    @Test
    fun `should log info`() {
        val loggerMock: Logger = mockk(relaxed = true)
        discountCalculator = DiscountCalculator(loggerMock)

        // given
        val discountStrategy = DiscountStrategy.Fixed(pln(100))

        // when
        discountCalculator.calculateDiscount(pln(10), discountStrategy)

        // then
        verify(exactly = 1) { loggerMock.logInfo(any()) }
    }
}