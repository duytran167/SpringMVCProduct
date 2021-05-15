/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.spring_mvc_product.controller;

import com.mycompany.spring_mvc_product.entities.ImageEntity;
import com.mycompany.spring_mvc_product.entities.ProductEntity;
import com.mycompany.spring_mvc_product.service.CategoryService;
import com.mycompany.spring_mvc_product.service.ImageService;
import com.mycompany.spring_mvc_product.service.ProductService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;


/**
 *
 * @author HMT
 */
@Controller
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ImageService imageService;

    @InitBinder
    public void initBinder(WebDataBinder webDataBinder) {
        StringTrimmerEditor stringTrimerEditor = new StringTrimmerEditor(true);
        webDataBinder.registerCustomEditor(String.class, stringTrimerEditor);
    }

    @RequestMapping(value = {"/", "/home"}, method = RequestMethod.GET)
    public String home(Model model) {
        model.addAttribute("products", productService.getProducts());
        return "home";
    }

    @RequestMapping("/add-product")
    public String addProduct(Model model) {
        model.addAttribute("categories", categoryService.getCategories());
        model.addAttribute("product", new ProductEntity());
        model.addAttribute("action", "add");
        return "add_product";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String resultAddProduct(Model model,
            @ModelAttribute("product") ProductEntity product,
            @RequestParam("file") List<MultipartFile> files,
            BindingResult result,
            HttpServletRequest servletRequest) {
        if (result.hasErrors() || files.size() > 1000000) {
            return ("product");
        } else {
            productService.save(product);
            
            String urlPath = 
                    servletRequest.getServletContext().getRealPath("/resources/images/");
            int index = urlPath.indexOf("target");
            String foderPath = urlPath.substring(0, index)+
                    "src\\main\\webapp\\resources\\images\\";
            for (MultipartFile file : files) {
                Path path=Paths.get(foderPath,file.getOriginalFilename());
                try {
                    byte[] bytes = file.getBytes();
                    Files.write(path, bytes);
                } catch (IOException ex) {
                    Logger.getLogger(ProductController.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                
                ImageEntity imageEntity = new ImageEntity();                
                String newFileName= file.getOriginalFilename();
                imageEntity.setName(newFileName);
                imageEntity.setProduct(product);

                imageService.saveImage(imageEntity);
            }        
        }
        return "redirect:/home";
    }

    @RequestMapping("/edit-product/{id}")
    public String editProduct(Model model,
            @PathVariable("id") int id) {
        model.addAttribute("categories", categoryService.getCategories());
        model.addAttribute("product", productService.findProductById(id));
        model.addAttribute("action", "edit");
        return "edit_product";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String resultEditProduct(Model model,
            @ModelAttribute("product") ProductEntity product,
            @RequestParam("file") List<MultipartFile> files,
            BindingResult result,
            HttpServletRequest servletRequest) 
    {
        if (result.hasErrors() || files.size() > 1000000) {
            return ("product");
        } else {
             productService.save(product);
            
            String urlPath = 
                    servletRequest.getServletContext().getRealPath("/resources/images/");
            int index = urlPath.indexOf("target");
            String foderPath = urlPath.substring(0, index)+
                    "src\\main\\webapp\\resources\\images\\";
            for (MultipartFile file : files) {
                Path path=Paths.get(foderPath,file.getOriginalFilename());
                try {
                    byte[] bytes = file.getBytes();
                    Files.write(path, bytes);
                } catch (IOException ex) {
                    Logger.getLogger(ProductController.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                
                ImageEntity imageEntity = new ImageEntity();                
                String newFileName= file.getOriginalFilename();
                imageEntity.setName(newFileName);
                imageEntity.setProduct(product);

                imageService.saveImage(imageEntity);
            }        
        }
        return "redirect:/home";
    }

    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public String searchBook(Model model,
            @ModelAttribute("strSearch") String strSearch) {
        model.addAttribute("products", productService.searchProduct(strSearch));
        return "home";
    }

    @RequestMapping("/delete-product/{id}")
    public String deleteProduct(Model model,
            @PathVariable("id") int id) {
        if (!productService.deleteProduct(id)) {
            return "redirect:/home?"
                    + "mesType=success&mes=Delete product id:" + id + " success!!!";
        } else {
            return "redirect:/home?"
                    + "mesType=error&mes=Delete product id:" + id + " fail!!!";
        }

    }
}
