package eapli.base.ordersmanagement.answer.domain;

import eapli.base.ordersmanagement.product.domain.ShortDescription;
import eapli.framework.domain.model.ValueObject;
import eapli.framework.validations.Preconditions;

public class AnswerId implements ValueObject, Comparable<AnswerId> {

    private String id;

    public AnswerId(final String answerId) {
        Preconditions.noneNull(answerId, "AnswerId cannot be null or empty");
        this.id = answerId;
    }
    protected AnswerId() {
    }
    public static AnswerId valueOf(final String description) throws IllegalAccessException {
        return new AnswerId(description);
    }
    @Override
    public int compareTo(AnswerId o) {
        return 0;
    }

    @Override
    public String toString() {
        return  id ;
    }
}
