
package eapli.base.ordersmanagement.customer.domain;

import eapli.framework.domain.model.AggregateRoot;
import eapli.framework.general.domain.model.EmailAddress;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Customer implements AggregateRoot<MecanographicNumber> {


    @EmbeddedId
    @Column(unique = true)
    private MecanographicNumber mecanographicNumber;

  /*  *//**
     * cascade = CascadeType.NONE as the systemUser is part of another aggregate
     *//*
    @OneToOne()
    private SystemUser systemUser;*/


    private CustomerFirstName customerFirstName;
    private CustomerLastName customerLastName;
    private EmailAddress customerEmailAddress;

    @Temporal(TemporalType.DATE)
    private CustomerBirthDay customerBirthDay;

    private CustomerPhoneNumber customerPhoneNumber;

    private CustomerGender customerGender;
    private CustomerVATIdentifier customerVatIdentifier;
    @ElementCollection
    private Set<CustomerPostalAddress> customerPostalAddress;

    public Customer() {
    }

    public Customer(MecanographicNumber mecanographicNumber, CustomerFirstName customerFirstName, CustomerLastName customerLastName, EmailAddress customerEmailAddress, CustomerPhoneNumber customerPhoneNumber, CustomerVATIdentifier customerVatIdentifier ){
        if (mecanographicNumber == null || customerFirstName == null || customerLastName == null || customerEmailAddress == null || customerPhoneNumber == null || customerVatIdentifier == null ) {
            throw new IllegalArgumentException();
        }
        this.mecanographicNumber = mecanographicNumber;
        this.customerFirstName=customerFirstName;
        this.customerLastName=customerLastName;
        this.customerEmailAddress=customerEmailAddress;
        this.customerPhoneNumber=customerPhoneNumber;
        this.customerVatIdentifier=customerVatIdentifier;
    }
    public Customer(MecanographicNumber mecanographicNumber, CustomerFirstName customerFirstName, CustomerLastName customerLastName, EmailAddress customerEmailAddress, CustomerPhoneNumber customerPhoneNumber, CustomerVATIdentifier customerVatIdentifier, CustomerBirthDay customerBirthDay, CustomerGender customerGender, Set<CustomerPostalAddress> customerPostalAddresses){
        if (mecanographicNumber == null || customerFirstName == null || customerLastName == null || customerEmailAddress == null || customerPhoneNumber == null || customerVatIdentifier == null || customerBirthDay == null || customerGender == null || customerPostalAddresses == null) {
            throw new IllegalArgumentException();
        }
        this.mecanographicNumber = mecanographicNumber;
        this.customerFirstName=customerFirstName;
        this.customerLastName=customerLastName;
        this.customerEmailAddress=customerEmailAddress;
        this.customerPhoneNumber=customerPhoneNumber;
        this.customerVatIdentifier=customerVatIdentifier;
        this.customerBirthDay = customerBirthDay;
        this.customerGender = customerGender;
        this.customerPostalAddress = customerPostalAddresses;
    }


  /*  public SystemUser user() {
        return this.systemUser;
    }*/

    public MecanographicNumber getCustomerId(){return this.mecanographicNumber;}

    public CustomerFirstName getCustomerFirstName() {
        return customerFirstName;
    }

    public CustomerLastName getCustomerLastName() {
        return customerLastName;
    }

    public EmailAddress getCustomerEmailAddress() {
        return customerEmailAddress;
    }

    public CustomerBirthDay getCustomerBirthDay() {
        return customerBirthDay;
    }

    public CustomerPhoneNumber getCustomerPhoneNumber() {
        return customerPhoneNumber;
    }

    public CustomerGender getCustomerGender() {
        return customerGender;
    }

    public CustomerVATIdentifier getCustomerVatIdentifier() {
        return customerVatIdentifier;
    }

    public Set<CustomerPostalAddress> getCustomerPostalAddress() {
        return customerPostalAddress;
    }

    @Override
    public boolean sameAs(Object other) {
        return false;
    }

    @Override
    public MecanographicNumber identity() {
        return null;
    }

    @Override
    public boolean hasIdentity(MecanographicNumber id) {
        return AggregateRoot.super.hasIdentity(id);
    }


}

