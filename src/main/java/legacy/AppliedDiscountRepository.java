package legacy;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface AppliedDiscountRepository {

    void add(AppliedDiscount discount);
    List<AppliedDiscount> findAll();
    List<AppliedDiscount> findForUser(UserId userId);
    Map<String, List<AppliedDiscount>> findForUserGroupedByType(UserId userId);
    Optional<AppliedDiscount> findLastAppliedDiscountForUser(UserId userId);
}
