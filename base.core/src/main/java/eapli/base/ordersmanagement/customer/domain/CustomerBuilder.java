package eapli.base.ordersmanagement.customer.domain;

import eapli.framework.domain.model.DomainFactory;
import eapli.framework.general.domain.model.EmailAddress;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;


public class CustomerBuilder implements DomainFactory<Customer> {

    private Customer theCustomer;

    private CustomerId customerId;

    private CustomerFirstName customerFirstName;

    private CustomerLastName customerLastName;

    private CustomerEmailAdress customerEmailAddress;

    private CustomerPhoneNumber customerPhoneNumber;

    private CustomerVATIdentifier customerVATIdentifier;

    private CustomerBirthDay customerBirthDay;

    private CustomerGender customerGender;

    private Set<CustomerPostalAddress> customerPostalAddresses;

    public CustomerBuilder withCustomerId(final CustomerId id){
        this.customerId=id;
        return this;
    }

    public CustomerBuilder withCustomerId(final String id){
        this.customerId = new CustomerId(id);
        return this;
    }

    public CustomerBuilder withFisrtName(final CustomerFirstName firstName){
        this.customerFirstName=firstName;
        return this;
    }

    public CustomerBuilder withFisrtName(final String firstName){
        this.customerFirstName = new CustomerFirstName(firstName);
        return this;
    }

    public CustomerBuilder withLastName(final CustomerLastName lastName){
        this.customerLastName=lastName;
        return this;
    }

    public CustomerBuilder withLastName(final String lastName){
        this.customerLastName = new CustomerLastName(lastName);
        return this;
    }

    public CustomerBuilder withEmailAdress(final CustomerEmailAdress emailAddress){
        this.customerEmailAddress=emailAddress;
        return this;
    }

    public CustomerBuilder withEmailAdress(final String emailAddress){
        this.customerEmailAddress = new CustomerEmailAdress(emailAddress);
        return this;
    }

    public CustomerBuilder withPhoneNumber(final CustomerPhoneNumber phoneNumber){
        this.customerPhoneNumber=phoneNumber;
        return this;
    }

    public CustomerBuilder withPhoneNumber(final String phoneNumber){
        this.customerPhoneNumber = new CustomerPhoneNumber(phoneNumber);
        return this;
    }

    public CustomerBuilder withVATIdentifier(final CustomerVATIdentifier vatIdentifier){
        this.customerVATIdentifier=vatIdentifier;
        return this;
    }

    public CustomerBuilder withVATIdentifier(final String vatIdentifier){
        this.customerVATIdentifier = new CustomerVATIdentifier(vatIdentifier);
        return this;
    }

    public CustomerBuilder withBirthday(final CustomerBirthDay birthday){
        this.customerBirthDay=birthday;
        return this;
    }

    public CustomerBuilder withBirthday(final Date birthday){
        this.customerBirthDay = new CustomerBirthDay(birthday);
        return this;
    }

    public CustomerBuilder withGender(final CustomerGender gender){
        this.customerGender=gender;
        return this;
    }

    /*public CustomerBuilder withGender(final String gender){
        this.customerGender = new CustomerGender(gender);
        return this;
    }*/

    public CustomerBuilder withPostalAdresses(final Set<CustomerPostalAddress> postalAdresses){
        this.customerPostalAddresses=postalAdresses;
        return this;
    }

    public CustomerBuilder withPostalAdresses(final String street, final int doorNumber, final String city,final String country, final int postalCode){
        Set<CustomerPostalAddress> postalAddresses = new HashSet<>();
        for (CustomerPostalAddress cpa: postalAddresses
             ) {
            postalAddresses.add(new CustomerPostalAddress(street,doorNumber,city,country,postalCode));
        }
        this.customerPostalAddresses = postalAddresses;
        return this;
    }


    @Override
    public Customer build() {
        return new Customer(this.customerId, this.customerFirstName, this.customerLastName, this.customerEmailAddress, this.customerPhoneNumber, this.customerVATIdentifier, this.customerBirthDay, this.customerGender, this.customerPostalAddresses);
    }
}

