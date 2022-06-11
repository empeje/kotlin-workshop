package legacy;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static legacy.TestUtils.pln;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class DiscountCalculatorTest {

    private DiscountCalculator discountCalculator = new DiscountCalculator();

    @BeforeEach
    void setup() {
        discountCalculator = new DiscountCalculator();
    }

    @Test
    void shouldCalculateDiscount() {
        // given
        DiscountStrategy discountStrategy = new DiscountStrategy.Fixed(pln(100));

        // when
        Money result = discountCalculator.calculateDiscount(pln(150), discountStrategy);

        // then
        assertThat(result).isEqualTo(pln(50));
    }

    @Test
    void shouldLogInfo() {
        Logger loggerMock = Mockito.mock(Logger.class);
        discountCalculator = new DiscountCalculator(loggerMock);

        // given
        DiscountStrategy discountStrategy = new DiscountStrategy.Fixed(pln(100));

        // when
        discountCalculator.calculateDiscount(pln(10), discountStrategy);

        // then
        verify(loggerMock, times(1)).logInfo(anyString());
    }
}
