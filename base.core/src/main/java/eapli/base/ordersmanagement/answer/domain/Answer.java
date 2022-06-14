package eapli.base.ordersmanagement.answer.domain;

import eapli.base.ordersmanagement.customer.domain.Customer;
import eapli.framework.domain.model.AggregateRoot;

import javax.persistence.*;
import java.util.Map;

@Entity
public class Answer implements AggregateRoot<AnswerId> {

    @EmbeddedId
    @Column(unique = true)
    private AnswerId answerId;

    @ManyToOne
    @JoinColumn
    private Customer customer;

    @ElementCollection
    private Map<String,String> every_Answer;

    @Override
    public boolean sameAs(Object other) {
        return false;
    }

    @Override
    public AnswerId identity() {
        return null;
    }
}
