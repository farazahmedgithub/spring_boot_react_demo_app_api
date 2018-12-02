package com.hellokoding.auth.repository;

import com.hellokoding.auth.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "role", path = "role")
public interface RoleRepository extends PagingAndSortingRepository<Role, Long> {

    Role findByName(String name);
}

