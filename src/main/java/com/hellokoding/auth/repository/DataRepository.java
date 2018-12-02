package com.hellokoding.auth.repository;

import com.hellokoding.auth.model.Data;
import com.hellokoding.auth.model.Tag;
import com.hellokoding.auth.model.User;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(collectionResourceRel = "data", path = "data")
public interface DataRepository extends PagingAndSortingRepository<Data, Integer> {

    List<Data> findAllByTagIn(List<Tag> tags);


}
