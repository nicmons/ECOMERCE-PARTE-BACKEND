/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.app.ecommerce.security.repository;

import com.app.ecommerce.security.entity.Picture;
import org.springframework.data.jpa.repository.JpaRepository;
/**
 *
 * @author Leona
 */

public interface PictureRepository extends JpaRepository<Picture , Integer>{
    
}
