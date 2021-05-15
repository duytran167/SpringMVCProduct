/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.spring_mvc_product.service;

import com.mycompany.spring_mvc_product.entities.ImageEntity;
import com.mycompany.spring_mvc_product.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author HMT
 */
@Service
public class ImageService {
    @Autowired
    private ImageRepository imageRepository;
    
    public void saveImage(ImageEntity imageEntity){
        imageRepository.save(imageEntity);
    }
    
}
