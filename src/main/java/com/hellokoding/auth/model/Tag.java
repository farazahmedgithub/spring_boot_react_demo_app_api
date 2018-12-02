package com.hellokoding.auth.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "tags", uniqueConstraints = {@UniqueConstraint(columnNames = {"identifier","user_id"})})
public class Tag {

    private  Integer id;
    private  String identifier;
    private User user;

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tag tag = (Tag) o;
        return Objects.equals(id, tag.id) &&
                Objects.equals(identifier, tag.identifier);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, identifier);
    }

    @Override
    public String toString() {
        return "Tag{" +
                "id=" + id +
                ", identifier='" + identifier + '\'' +
                '}';
    }
}
