/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.app.ecommerce.security.controller;

import com.app.ecommerce.security.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Leona
 */
@Controller
@RequestMapping("/administration")
public class ProductController {
    @Autowired
    private ProductService servisProducto;
    
    @GetMapping("/panel")
    public String initPageAdmin(){
        return "/administration/panelAdmin.html";
    }
}
