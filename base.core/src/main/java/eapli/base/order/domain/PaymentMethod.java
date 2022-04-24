package eapli.base.order.domain;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class PaymentMethod implements Serializable {
    @Id
    @Column(name = "id", nullable = false)
    private String id;


    @OneToOne (fetch= FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name = "superPaymentMethod")



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
