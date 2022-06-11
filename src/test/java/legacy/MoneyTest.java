package legacy;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class MoneyTest {

    @Test
    void shouldThrowExceptionForNullValue() {
        // when
        Executable whenBlock = () -> Money.of(null, Currency.PLN);

        // then
        assertThrows(IllegalStateException.class, whenBlock);
    }

    @Test
    void shouldThrowExceptionForNullCurrency() {
        // when
        Executable whenBlock = () -> Money.of(BigDecimal.TEN, null);

        // then
        assertThrows(IllegalStateException.class, whenBlock);
    }

    @Test
    void shouldAddTwoValues() {
        // given
        var oneEur = Money.of(BigDecimal.valueOf(1), Currency.EUR);
        var tenEur = Money.of(BigDecimal.valueOf(10), Currency.EUR);

        // when
        var result = oneEur.plus(tenEur);

        // then
        assertThat(result.getValue()).isEqualTo(BigDecimal.valueOf(11));
        assertThat(result.getCurrency()).isEqualTo(Currency.EUR);
    }

    @Test
    void shouldSubtractTwoValues() {
        // given
        var oneEur = Money.of(BigDecimal.valueOf(1), Currency.EUR);
        var tenEur = Money.of(BigDecimal.valueOf(10), Currency.EUR);

        // when
        var result = tenEur.minus(oneEur);

        // then
        assertThat(result.getValue()).isEqualTo(BigDecimal.valueOf(9));
        assertThat(result.getCurrency()).isEqualTo(Currency.EUR);
    }

    @Test
    void plusOperationIsCommutative() {
        // given
        var oneEur = Money.of(BigDecimal.valueOf(1), Currency.EUR);
        var tenEur = Money.of(BigDecimal.valueOf(10), Currency.EUR);

        // when
        var firstResult = oneEur.plus(tenEur);
        var secondResult = tenEur.plus(oneEur);

        // then
        assertThat(firstResult).isEqualTo(secondResult);
    }

    @Test
    void shouldThrowForPlusOperationWhenCurrenciesDiffer() {
        // given
        var oneEur = Money.of(BigDecimal.valueOf(1), Currency.EUR);
        var onePln = Money.of(BigDecimal.valueOf(1), Currency.PLN);

        // when
        Executable whenBlock = () -> oneEur.plus(onePln);

        // then
        assertThrows(IllegalStateException.class, whenBlock);
    }

    @Test
    void shouldMultiplyByPercent() {
        // given
        var tenEur = Money.of(BigDecimal.valueOf(10), Currency.PLN);
        var threePercent = Percent.of(BigDecimal.valueOf(3.5));

        // when
        var result = tenEur.times(threePercent);

        // then
        assertThat(result.getValue()).isEqualTo(BigDecimal.valueOf(0.35));
        assertThat(result.getCurrency()).isEqualTo(Currency.PLN);
    }
}
