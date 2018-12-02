package com.hellokoding.auth.repository;

import com.hellokoding.auth.model.User;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;

@RepositoryRestResource(collectionResourceRel = "user", path = "user")
@Transactional
public interface UserRepository extends PagingAndSortingRepository<User, Long> {

    //@PreAuthorize("hasRole('ROLE_ADMIN')")
    @RestResource
    User findByAuthToken(@Param("token") String token);

    User findByUsernameAndPassword(String username, String password);

    User findByUsername(String username);
}
