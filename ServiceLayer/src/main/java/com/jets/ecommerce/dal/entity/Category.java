
package com.jets.ecommerce.dal.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;


@Entity(name = "categories")
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {
    "name", "parentCategory_id"
}))
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Category implements Serializable, com.jets.ecommerce.dal.dao.Entity<Integer> {

    @Id
    @GeneratedValue
    private Integer id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = true)
    private String picture;

    @ManyToOne
    private Category parentCategory;

    @OneToMany(mappedBy = "parentCategory")
    private Set<Category> subCategories = new HashSet<>(0);

    public Category() {
    }

    public Category(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public Category getParentCategory() {
        return parentCategory;
    }

    public void setParentCategory(Category parentCategory) {
        this.parentCategory = parentCategory;
    }

    public Set<Category> getSubCategories() {
        return subCategories;
    }

    void setSubCategories(Set<Category> subCategories) {
        this.subCategories = subCategories;
    }

    public void addSubCategory(Category subCategory) {
        if (subCategory.getParentCategory() != null) {
            subCategory.getParentCategory().getSubCategories().remove(subCategory);
        }
        subCategory.setParentCategory(this);
        this.getSubCategories().add(subCategory);
    }

}
