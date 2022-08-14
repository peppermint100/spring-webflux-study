package com.peppermint100.springwebflux;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApplicationUserRepository extends ReactiveCrudRepository<ApplicationUser, Long> {
}
