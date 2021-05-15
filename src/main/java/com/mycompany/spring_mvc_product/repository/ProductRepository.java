/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.spring_mvc_product.repository;

import com.mycompany.spring_mvc_product.entities.ProductEntity;
import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author HMT
 */
@Repository
public interface ProductRepository extends CrudRepository<ProductEntity, Integer>{
    List<ProductEntity>findByNameContainingOrCategoryNameContaining(String name1, String name2);
    
}
