/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.app.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.app.ecommerce.entity.Product;

/**
 *
 * @author Leona
 */
public interface ProductRepository extends JpaRepository<Product , Integer>{
    
}
