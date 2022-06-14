package eapli.base.ordersmanagement.answer.domain;

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

    @Override
    public int compareTo(AnswerId o) {
        return 0;
    }

}
