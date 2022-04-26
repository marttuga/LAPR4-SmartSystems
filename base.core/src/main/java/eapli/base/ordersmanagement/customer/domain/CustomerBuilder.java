package eapli.base.ordersmanagement.customer.domain;

import eapli.framework.application.ApplicationService;
import eapli.framework.domain.model.DomainFactory;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;

import javax.persistence.Embedded;
import javax.persistence.OneToOne;

@ApplicationService
public class CustomerBuilder implements DomainFactory<Customer> {

    private CustomerId customerId;

    private SystemUser systemUser;

    private Name name;

    private EmailAddress emailAddress;

    private BirthDate birthDate;

    private PhoneNumber phoneNumber;

    private Gender gender;

    private VATIdentifier vatIdentifier;

    private PostalAddress postalAddress;


    public CustomerBuilder withEmailAddress(EmailAddress emailAddress) {
        this.emailAddress = emailAddress;
        return this;
    }

    public CustomerBuilder withPostalAddress(PostalAddress postalAddress){
        this.postalAddress = postalAddress;
        return this;
    }

    public CustomerBuilder withPhoneNumber(PhoneNumber phoneNumber){
        this.phoneNumber= phoneNumber;
        return this;
    }

    public CustomerBuilder withBirthDate (BirthDate birthDate){
        this.birthDate=birthDate;
        return this;
    }

    public CustomerBuilder withVATIdentifier(VATIdentifier vatIdentifier){
        this.vatIdentifier=vatIdentifier;
        return this;
    }

    public CustomerBuilder withName(String name){
        this.name= new Name(name);
        return this;
    }

    public CustomerBuilder withGender(Gender gender){
        this.gender= gender;
        return this;
    }

    @Override
    public Customer build() {
        return new Customer(this.emailAddress, this.postalAddress, this.phoneNumber, this.birthDate, this.name, this.vatIdentifier, this.gender);
    }

}
