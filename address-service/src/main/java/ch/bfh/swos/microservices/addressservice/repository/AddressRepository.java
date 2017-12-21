package ch.bfh.swos.microservices.addressservice.repository;

import ch.bfh.swos.microservices.addressservice.model.Address;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import java.util.List;

@RepositoryRestResource
public interface AddressRepository extends CrudRepository<Address, String> {

    @RestResource(path = "person")
    List<Address> findByPersonId(@Param("personId") String personId);
}
