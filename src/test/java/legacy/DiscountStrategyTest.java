package legacy;

import org.junit.jupiter.api.Test;

import static legacy.TestUtils.percent;
import static legacy.TestUtils.pln;
import static org.assertj.core.api.Assertions.assertThat;

public class DiscountStrategyTest {


    @Test
    void shouldCalculateFixedDiscount() {
        // given
        DiscountStrategy discount = new DiscountStrategy.Fixed(pln(5));

        // when
        Money discountedValue = discount.calculateDiscountedValue(pln(13));

        // then
        assertThat(discountedValue).isEqualTo(pln(8));
    }

    @Test
    void shouldCalculateFixedDiscountToBeAlwaysNonNegative() {
        // given
        DiscountStrategy discount = new DiscountStrategy.Fixed(pln(13));

        // when
        Money discountedValue = discount.calculateDiscountedValue(pln(5));

        // then
        assertThat(discountedValue).isEqualTo(pln(0));
    }

    @Test
    void shouldCalculatePercentageDiscount() {
        // given
        DiscountStrategy discount = new DiscountStrategy.Percentage(percent(10));

        // when
        Money discountedValue = discount.calculateDiscountedValue(pln(15));

        // then
        assertThat(discountedValue).isEqualTo(pln(13.5));
    }
}
