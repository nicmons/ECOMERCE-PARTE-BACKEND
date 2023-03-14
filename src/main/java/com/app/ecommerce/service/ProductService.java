/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.app.ecommerce.service;

import com.app.ecommerce.entity.Picture;
import com.app.ecommerce.repository.ProductRepository;
import com.app.ecommerce.entity.Product;
import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Leona
 */
@Service
public class ProductService {
    @Autowired
    private ProductRepository repoProducto;
    @Autowired
    private PictureService servicePictures;
    
    public void saveNewProduct(String name , float price , Collection<MultipartFile> pictures){
        //Repo pictures para devolver Obj pictures
        if(validateProduct(name , price)){
            try {
                Collection<Picture> fotosProducto = servicePictures.savePics(pictures);
                Product finalProduct = new Product(name, price, fotosProducto);
                repoProducto.save(finalProduct);
            } catch (Exception e) {
                System.out.println(e.getCause());
            }
        }        
    }
    
    
    /*Servicios privados*/
    private boolean validateProduct(String name , float price){
        if(!name.matches("[A-Za-z]*+\\s")){
            return false;
        }
        if(!String.valueOf(price).matches("[\\d]")){
            return false;
        }
        return true;
    }
}
