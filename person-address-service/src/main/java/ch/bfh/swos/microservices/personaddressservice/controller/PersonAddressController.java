package ch.bfh.swos.microservices.personaddressservice.controller;

import ch.bfh.swos.microservices.personaddressservice.client.AddressClient;
import ch.bfh.swos.microservices.personaddressservice.client.PersonClient;
import ch.bfh.swos.microservices.personaddressservice.model.Person;
import ch.bfh.swos.microservices.personaddressservice.model.PersonAddress;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryLinksResource;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceProcessor;
import org.springframework.hateoas.Resources;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
public class PersonAddressController implements ResourceProcessor<RepositoryLinksResource> {

    private static final Logger LOG = LoggerFactory.getLogger(PersonAddressController.class);

    @Autowired
    private PersonClient personClient;

    @Autowired
    private AddressClient addressClient;

    @RequestMapping(value = "/personAddress/{id}", method = RequestMethod.GET, produces = "application/hal+json")
    @ResponseBody
    public Resource<PersonAddress> getPersonAddress(@PathVariable("id") String id) {
        Person person = personClient.getPerson(id).getContent();
        PersonAddress personAddress = new PersonAddress();
        personAddress.setFirstname(person.getFirstname());
        personAddress.setLastname(person.getLastname());
        personAddress.setAddresses(addressClient.getAddressesForPerson(person.getId()).getContent());
        LOG.info("----------- Orchestrating a Person and Address -----------");
        return new Resource<>(personAddress);
    }

    @RequestMapping(value = "/personAddress", method = RequestMethod.GET, produces = "application/hal+json")
    @ResponseBody
    public Resources<PersonAddress> listPersonAddresses() {
        List<PersonAddress> personAddressList = new ArrayList<>();
        Resources<Person> personList = personClient.getPersons();
        for (Person person : personList) {
            PersonAddress personAddress = new PersonAddress();
            personAddress.add(linkTo(methodOn(PersonAddressController.class).getPersonAddress(person.getId())).withSelfRel());
            personAddress.add(linkTo(methodOn(PersonAddressController.class).getPersonAddress(person.getId())).withRel("personAddress"));
            personAddress.setFirstname(person.getFirstname());
            personAddress.setLastname(person.getLastname());
            personAddress.setAddresses(addressClient.getAddressesForPerson(person.getId()).getContent());
            personAddressList.add(personAddress);
        }
        LOG.info("----------- Orchestrating multiple Persons and Addresses -----------");
        return new Resources<>(personAddressList);
    }

    @Override
    public RepositoryLinksResource process(RepositoryLinksResource repositoryLinksResource) {
        repositoryLinksResource.add(linkTo(methodOn(PersonAddressController.class).listPersonAddresses()).withRel("personAddress"));
        return repositoryLinksResource;
    }
}
