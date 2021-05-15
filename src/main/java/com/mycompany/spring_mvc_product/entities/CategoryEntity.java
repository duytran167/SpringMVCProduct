/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.spring_mvc_product.entities;

import java.awt.print.Book;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author HMT
 */
@Entity
@Table (name = "Category")
public class CategoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    @Column(length = 50)
    private String name;
    
    @Column(length = 100)
    private String description;

    public CategoryEntity() {
    }
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "CategoryEntity{" + "id=" + id + ", name=" + name + ", description=" + description + '}';
    }
    
    @OneToMany(mappedBy = "category",fetch=FetchType.LAZY,cascade = CascadeType.ALL)
    private Set<ProductEntity> product;

    public Set<ProductEntity> getProduct() {
        return product;
    }

    public void setProduct(Set<ProductEntity> product) {
        this.product = product;
    }
    
    
}
