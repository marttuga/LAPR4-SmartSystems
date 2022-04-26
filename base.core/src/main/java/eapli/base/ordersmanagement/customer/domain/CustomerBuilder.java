package eapli.base.ordersmanagement.customer.domain;

import eapli.framework.application.ApplicationService;
import eapli.framework.domain.model.DomainFactory;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;

import java.util.Calendar;

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

    public CustomerBuilder withSystemUser(final SystemUser systemUser) {
        this.systemUser = systemUser;
        return this;
    }

    public CustomerBuilder withCustomerId(final CustomerId customerId) {
        this.customerId = customerId;
        return this;
    }

    public CustomerBuilder withCustomerId(final String customerId) {
        this.customerId = new CustomerId(customerId);
        return this;
    }

    public CustomerBuilder withEmailAddress(final EmailAddress emailAddress) {
        this.emailAddress = emailAddress;
        return this;
    }

    public CustomerBuilder withEmailAddress(final String emailAddress) {
        this.emailAddress = new EmailAddress(emailAddress);
        return this;
    }

    public CustomerBuilder withPostalAddress(final PostalAddress postalAddress){
        this.postalAddress = postalAddress;
        return this;
    }

    public CustomerBuilder withPostalAddress(final String postalAddress){
        this.postalAddress = new PostalAddress(postalAddress);
        return this;
    }

    public CustomerBuilder withPhoneNumber(final PhoneNumber phoneNumber){
        this.phoneNumber= phoneNumber;
        return this;
    }

    public CustomerBuilder withPhoneNumber(final String phoneNumber){
        this.phoneNumber= new PhoneNumber(phoneNumber);
        return this;
    }

    public CustomerBuilder withBirthDate (final BirthDate birthDate){
        this.birthDate=birthDate;
        return this;
    }

    public CustomerBuilder withBirthDate (final Calendar birthDate){
        this.birthDate=new BirthDate(birthDate);
        return this;
    }

    public CustomerBuilder withVATIdentifier(final VATIdentifier vatIdentifier){
        this.vatIdentifier=vatIdentifier;
        return this;
    }

    public CustomerBuilder withVATIdentifier(final String vatIdentifier){
        this.vatIdentifier = new VATIdentifier(vatIdentifier);
        return this;
    }


    public CustomerBuilder withName(final Name name){
        this.name= name;
        return this;
    }

    public CustomerBuilder withName(final String name){
        this.name= new Name(name);
        return this;
    }

    public CustomerBuilder withGender(final Gender gender){
        this.gender= gender;
        return this;
    }
    public CustomerBuilder withGender(final String gender){
        this.gender= new Gender(gender);
        return this;
    }

    @Override
    public Customer build() {
        return new Customer(systemUser,customerId,emailAddress, postalAddress, phoneNumber, birthDate, name, vatIdentifier, gender);
    }

}
