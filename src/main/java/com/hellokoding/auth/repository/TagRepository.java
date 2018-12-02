package com.hellokoding.auth.repository;

import com.hellokoding.auth.model.Tag;
import com.hellokoding.auth.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(collectionResourceRel = "tag", path = "tag")
public interface TagRepository extends PagingAndSortingRepository<Tag, Integer> {
    List<Tag> findAllByUserIs(User user);
    List<Tag> findAllByUserIsAndIdentifierLike(User user, String search);
}
