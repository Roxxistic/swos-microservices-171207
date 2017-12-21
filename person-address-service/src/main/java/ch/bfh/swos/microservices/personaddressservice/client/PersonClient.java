package ch.bfh.swos.microservices.personaddressservice.client;

import ch.bfh.swos.microservices.personaddressservice.model.Person;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient("person-service")
public interface PersonClient {
    @RequestMapping(method = RequestMethod.GET, value = "/persons/{id}")
    Resource<Person> getPerson(@PathVariable("id") String id);

    @RequestMapping(method = RequestMethod.GET, value = "/persons")
    Resources<Person> getPersons();

}
