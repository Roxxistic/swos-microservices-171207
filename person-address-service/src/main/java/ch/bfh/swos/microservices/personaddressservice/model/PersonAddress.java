package ch.bfh.swos.microservices.personaddressservice.model;

import org.springframework.hateoas.ResourceSupport;

import java.util.Collection;

public class PersonAddress extends ResourceSupport {

    private String firstname;

    private String lastname;

    private Collection<Address> addresses;

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public Collection<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(Collection<Address> addresses) {
        this.addresses = addresses;
    }
}
