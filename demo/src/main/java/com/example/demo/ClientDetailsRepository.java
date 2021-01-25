package com.example.demo;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
//import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Optional;

//@RepositoryRestResource(collectionResourceRel = "people", path = "people")
@Repository
public interface ClientDetailsRepository extends PagingAndSortingRepository<VpcgClientConfig, Long> {
    Optional<VpcgClientConfig> findById(long id);
}