package ch.bfh.swos.microservices.personaddressservice.client;

import ch.bfh.swos.microservices.personaddressservice.model.Address;
import org.springframework.hateoas.Resources;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class AddressClientFallback implements AddressClient {

    @Override
    public Resources<Address> getAddresses() {
        return createDummyAddresses();
    }

    @Override
    public Resources<Address> getAddressesForPerson(String personId) {
        return createDummyAddresses();
    }

    private Resources<Address> createDummyAddresses() {
        List<Address> addressList = new ArrayList<>();
        Address address = new Address();
        address.setId("-- DUMMY ADDRESS --");
        address.setPersonId("-- DUMMY PERSONID --");
        address.setStreet("-- DUMMY STREET --");
        address.setZip(0);
        address.setLocation("-- DUMMY LOCATION --");
        addressList.add(address);
        return new Resources<>(addressList);
    }
}
