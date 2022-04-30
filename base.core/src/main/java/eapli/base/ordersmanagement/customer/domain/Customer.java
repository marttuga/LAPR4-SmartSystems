
package eapli.base.ordersmanagement.customer.domain;

import eapli.framework.domain.model.AggregateRoot;
import eapli.framework.general.domain.model.EmailAddress;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Customer implements AggregateRoot<CustomerId> {


    @Id
    @Column(unique = true)
    private CustomerId customerId;
    private CustomerFirstName customerFirstName;
    private CustomerLastName customerLastName;
    private CustomerEmailAdress customerEmailAddress;
    @Temporal(TemporalType.DATE)
    private CustomerBirthDay customerBirthDay;

    private CustomerPhoneNumber customerPhoneNumber;

    private CustomerGender customerGender;
    private CustomerVATIdentifier customerVatIdentifier;
    @ElementCollection
    private Set<CustomerPostalAddress> customerPostalAddress;

    protected Customer() {
    }

    public Customer(CustomerId customerId, CustomerFirstName customerFirstName, CustomerLastName customerLastName, CustomerEmailAdress customerEmailAddress, CustomerPhoneNumber customerPhoneNumber, CustomerVATIdentifier customerVatIdentifier ){
        if (customerId == null || customerFirstName == null || customerLastName == null || customerEmailAddress == null || customerPhoneNumber == null || customerVatIdentifier == null ) {
            throw new IllegalArgumentException();
        }
        this.customerId = customerId;
        this.customerFirstName=customerFirstName;
        this.customerLastName=customerLastName;
        this.customerEmailAddress=customerEmailAddress;
        this.customerPhoneNumber=customerPhoneNumber;
        this.customerVatIdentifier=customerVatIdentifier;
    }
    public Customer(CustomerId customerId, CustomerFirstName customerFirstName, CustomerLastName customerLastName, CustomerEmailAdress customerEmailAddress, CustomerPhoneNumber customerPhoneNumber, CustomerVATIdentifier customerVatIdentifier, CustomerBirthDay customerBirthDay, CustomerGender customerGender, Set<CustomerPostalAddress> customerPostalAddresses){
        if (customerId == null || customerFirstName == null || customerLastName == null || customerEmailAddress == null || customerPhoneNumber == null || customerVatIdentifier == null || customerBirthDay == null || customerGender == null || customerPostalAddresses == null) {
            throw new IllegalArgumentException();
        }
        this.customerId = customerId;
        this.customerFirstName=customerFirstName;
        this.customerLastName=customerLastName;
        this.customerEmailAddress=customerEmailAddress;
        this.customerPhoneNumber=customerPhoneNumber;
        this.customerVatIdentifier=customerVatIdentifier;
        this.customerBirthDay = customerBirthDay;
        this.customerGender = customerGender;
        this.customerPostalAddress = customerPostalAddresses;
    }

    public CustomerId getCustomerId(){return this.customerId;}

    public CustomerFirstName getCustomerFirstName() {
        return customerFirstName;
    }

    public CustomerEmailAdress getCustomerEmailAddress() {
        return customerEmailAddress;
    }

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

