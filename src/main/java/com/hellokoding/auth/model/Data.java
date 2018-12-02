package com.hellokoding.auth.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "data", uniqueConstraints = {@UniqueConstraint(columnNames = {"identifier","tag_id"})})
public class Data {

    private Integer id;
    private String identifier;
    private String value;
    private Tag tag;

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

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @ManyToOne(targetEntity = Tag.class)
    @JoinColumn(name = "tag_id", referencedColumnName = "id")
    public Tag getTag() {
        return tag;
    }

    public void setTag(Tag tag) {
        this.tag = tag;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Data data = (Data) o;
        return Objects.equals(id, data.id) &&
                Objects.equals(identifier, data.identifier) &&
                Objects.equals(value, data.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, identifier, value);
    }

    @Override
    public String toString() {
        return "Data{" +
                "id=" + id +
                ", identifier='" + identifier + '\'' +
                ", value='" + value + '\'' +
                '}';
    }
}
