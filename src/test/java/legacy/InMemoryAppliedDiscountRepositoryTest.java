package legacy;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static legacy.InMemoryAppliedDiscountRepositoryTest.AppliedDiscountBuilder.aFixedDiscount;
import static legacy.InMemoryAppliedDiscountRepositoryTest.AppliedDiscountBuilder.aPercentageDiscount;
import static legacy.TestUtils.TEST_TIME;
import static org.assertj.core.api.Assertions.assertThat;

public class InMemoryAppliedDiscountRepositoryTest {

    private InMemoryAppliedDiscountRepository repository;

    @BeforeEach
    void setup() {
        repository = new InMemoryAppliedDiscountRepository();
    }

    @Test
    void shouldReturnAllDiscounts() {
        // given
        thereAreDiscounts(
                aFixedDiscount().withId(1),
                aFixedDiscount().withId(2),
                aFixedDiscount().withId(3)
        );

        // when
        List<AppliedDiscount> result = repository.findAll();

        // then
        assertThat(result)
                .hasSize(3)
                .hasSameElementsAs(
                        List.of(
                                aFixedDiscount().withId(1).build(),
                                aFixedDiscount().withId(2).build(),
                                aFixedDiscount().withId(3).build()
                        )
                );
    }

    @Test
    void shouldFindDiscountsForUser() {
        // given
        thereAreDiscounts(
                aFixedDiscount().withId(1).forUser(1),
                aFixedDiscount().withId(2).forUser(1),
                aFixedDiscount().withId(3).forUser(2)
        );

        // when
        List<AppliedDiscount> result = repository.findForUser(new UserId(1L));

        //then
        assertThat(result)
                .hasSize(2)
                .hasSameElementsAs(
                        List.of(
                                aFixedDiscount().withId(1).forUser(1).build(),
                                aFixedDiscount().withId(2).forUser(1).build()
                        )
                );
    }

    @Test
    void shouldFindForUserGroupedByType() {
        // given
        thereAreDiscounts(
                aFixedDiscount().withId(1).forUser(1),
                aPercentageDiscount().withId(2).forUser(1),
                aFixedDiscount().withId(3).forUser(1)
        );

        // when
        Map<String, List<AppliedDiscount>> result = repository.findForUserGroupedByType(new UserId(1L));

        //then
        assertThat(result)
                .hasSize(2);

        assertThat(result.get("fixed"))
                .hasSameElementsAs(List.of(
                        aFixedDiscount().withId(1).forUser(1).build(),
                        aFixedDiscount().withId(3).forUser(1).build()
                ))
                .hasSize(2);

        assertThat(result.get("percentage"))
                .hasSameElementsAs(
                        List.of(aPercentageDiscount().withId(2).forUser(1).build())
                )
                .hasSize(1);

    }

    @Test
    void shouldFindLastAppliedDiscountForUser() {
        // given
        thereAreDiscounts(
                aFixedDiscount().withId(1).forUser(1).withApplicationTime(TEST_TIME.plusSeconds(10)),
                aPercentageDiscount().withId(2).forUser(1).withApplicationTime(TEST_TIME.plusSeconds(30)),
                aFixedDiscount().withId(3).forUser(1).withApplicationTime(TEST_TIME.plusSeconds(20))
        );

        // when
        Optional<AppliedDiscount> result = repository.findLastAppliedDiscountForUser(new UserId(1L));

        // then
        assertThat(result).isPresent();
        assertThat(result.get())
                .isEqualTo(
                        aPercentageDiscount().withId(2).forUser(1).withApplicationTime(TEST_TIME.plusSeconds(30)).build()
                );
    }

    private void thereAreDiscounts(AppliedDiscountBuilder... discounts) {
        Arrays.stream(discounts)
                .map(AppliedDiscountBuilder::build)
                .forEach(repository::add);
    }

    static class AppliedDiscountBuilder {
        private Long id = 1L;
        private Long amount = 10L;
        private Long userId = 1L;
        private Instant applicationTime = TEST_TIME;
        private Integer percent = 10;
        private String type;

        static AppliedDiscountBuilder aFixedDiscount() {
            AppliedDiscountBuilder builder = new AppliedDiscountBuilder();
            builder.type = "fixed";
            return builder;
        }

        static AppliedDiscountBuilder aPercentageDiscount() {
            AppliedDiscountBuilder builder = new AppliedDiscountBuilder();
            builder.type = "percentage";
            return builder;
        }

        AppliedDiscountBuilder withId(Integer id) {
            this.id = id.longValue();
            return this;
        }

        AppliedDiscountBuilder forUser(Integer id) {
            this.userId = id.longValue();
            return this;
        }

        AppliedDiscountBuilder withApplicationTime(Instant applicationTime) {
            this.applicationTime = applicationTime;
            return this;
        }

        AppliedDiscount build() {
            if(type.equals("fixed")) {
                return new FixedDiscount(
                        id,
                        new Money(BigDecimal.valueOf(amount), new Currency("PLN")),
                        new UserId(userId),
                        applicationTime
                );
            } else {
                return new PercentageDiscount(
                        id,
                        new Money(BigDecimal.valueOf(amount), new Currency("PLN")),
                        new UserId(userId),
                        new Percent(BigDecimal.valueOf(percent)),
                        applicationTime
                );
            }
        }
    }
}
