
package eapli.base.ordersmanagement.customer.domain;

import eapli.framework.domain.model.AggregateRoot;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import javax.persistence.*;

@Entity
public class Customer implements AggregateRoot<CustomerId> {


    @EmbeddedId
    @GeneratedValue(strategy = GenerationType.AUTO)
    private CustomerId customerId;



    @OneToOne()
    private SystemUser systemUser;

    @Embedded
    private Name name;

    @Embedded
    private EmailAddress emailAddress;

    @Embedded
    private BirthDate birthDate;

    @Embedded
    private PhoneNumber phoneNumber;

    @Embedded
    private Gender gender;


    @Embedded
    private VATIdentifier vatIdentifier;

    @Embedded
    private PostalAddress postalAddress;

    public Customer() {
    }

    public Customer(EmailAddress emailAddress, PostalAddress postalAddresses, PhoneNumber phoneNumber, BirthDate birthDate, Name name, VATIdentifier vatIdentifier, Gender gender) {
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


    @Override
    public boolean sameAs(Object other) {
        return false;
    }

    @Override
    public int compareTo(CustomerId other) {
        return AggregateRoot.super.compareTo(other);
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

