
package eapli.base.ordersmanagement.customer.domain;

import eapli.framework.domain.model.AggregateRoot;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import javax.persistence.*;

@Entity
public class Customer implements AggregateRoot<CustomerId> {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private CustomerId customerId;

    /**
     * cascade = CascadeType.NONE as the systemUser is part of another aggregate
     */
    @OneToOne()
    private SystemUser systemUser;

    @Column(updatable = false,insertable = false)
    @Embedded
    private Name name;

    @Column(updatable = false,insertable = false)
    @Embedded
    private EmailAddress emailAddress;

    @Column(updatable = false,insertable = false)
    @Embedded
    private BirthDate birthDate;

    @Column(updatable = false,insertable = false)
    @Embedded
    private PhoneNumber phoneNumber;

    @Column(updatable = false,insertable = false)
    @Embedded
    private Gender gender;


    @Column(updatable = false,insertable = false)
    @Embedded
    private VATIdentifier vatIdentifier;

    @Column(updatable = false,insertable = false)
    @Embedded
    private PostalAddress postalAddress;

    public Customer() {
    }

    public Customer(SystemUser systemUser, CustomerId customerId,EmailAddress emailAddress, PostalAddress postalAddresses, PhoneNumber phoneNumber, BirthDate birthDate, Name name, VATIdentifier vatIdentifier, Gender gender) {
        this.systemUser = systemUser;
        this.customerId = customerId;
        this.emailAddress = emailAddress;
        this.postalAddress = postalAddresses;
        this.phoneNumber = phoneNumber;
        this.birthDate = birthDate;
        this.name = name;
        this.vatIdentifier=vatIdentifier;
        this.gender=gender;
    }

    public SystemUser user() {
        return this.systemUser;
    }

    public CustomerId getCustomerId(){return this.customerId;}

    @Override
    public boolean sameAs(Object other) {
        return false;
    }

    @Override
    public CustomerId identity() {
        return null;
    }

    @Override
    public boolean hasIdentity(CustomerId id) {
        return AggregateRoot.super.hasIdentity(id);
    }
}

