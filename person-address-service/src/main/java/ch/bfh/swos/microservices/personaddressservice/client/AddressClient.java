package ch.bfh.swos.microservices.personaddressservice.client;

import ch.bfh.swos.microservices.personaddressservice.model.Address;
import ch.bfh.swos.microservices.personaddressservice.model.Person;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "address-service", fallback = AddressClientFallback.class)
public interface AddressClient {
    @RequestMapping(method = RequestMethod.GET, value = "/addresses")
    Resources<Address> getAddresses();

    @RequestMapping(method = RequestMethod.GET, value = "/addresses/search/person")
    Resources<Address> getAddressesForPerson(@RequestParam("personId") String personId);
}
