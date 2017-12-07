package ch.bfh.swos.microservices.addressservice.repository;

import ch.bfh.swos.microservices.addressservice.model.Address;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface AddressRepository extends CrudRepository<Address, String> {
}
