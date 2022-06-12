package legacy;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class InMemoryAppliedDiscountRepository implements AppliedDiscountRepository {

    private final List<AppliedDiscount> values = new ArrayList<>();

    @Override
    public void add(AppliedDiscount discount) {
        values.add(discount);
    }

    @Override
    public List<AppliedDiscount> findAll() {
        return new ArrayList<>(values);
    }

    @Override
    public List<AppliedDiscount> findForUser(UserId userId) {
        return values.stream()
                .filter((value) -> value.getUserId().equals(userId))
                .collect(Collectors.toList());
    }

    @Override
    public Map<String, List<AppliedDiscount>> findForUserGroupedByType(UserId userId) {
        return values.stream().collect(Collectors.groupingBy(
                (AppliedDiscount value) -> {
                    if(value instanceof FixedDiscount) {
                        return "fixed";
                    }
                    if(value instanceof PercentageDiscount) {
                        return "percentage";
                    }
                    throw new RuntimeException();
                }
        ));
    }

    @Override
    public Optional<AppliedDiscount> findLastAppliedDiscountForUser(UserId userId) {
        return values.stream()
                .sorted(Comparator.comparing(AppliedDiscount::getApplicationTime))
                .reduce((first, second) -> second);
    }
}
