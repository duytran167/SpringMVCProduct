/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.spring_mvc_product.service;

import com.mycompany.spring_mvc_product.entities.ProductEntity;
import com.mycompany.spring_mvc_product.repository.ProductRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author HMT
 */
@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;
    
    public List<ProductEntity> getProducts(){
        return (List<ProductEntity>) productRepository.findAll();
    }
    
    public void save(ProductEntity productEntity){
       productRepository.save(productEntity);
    }
    
    public ProductEntity findProductById(int id){
        Optional <ProductEntity> optional 
                = productRepository.findById(id);
        if(optional.isPresent()){
            return optional.get();
        }
        return new ProductEntity();
    }
    
     public List<ProductEntity> searchProduct(String strSearch){
         return productRepository.findByNameContainingOrCategoryNameContaining(strSearch, strSearch);
     }
     
     public boolean deleteProduct(int id){
        productRepository.deleteById(id);
        return productRepository.existsById(id);
    }
}
