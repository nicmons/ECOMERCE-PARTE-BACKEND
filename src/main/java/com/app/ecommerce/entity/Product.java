/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.app.ecommerce.entity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import java.util.Collection;
/**
 *
 * @author Leona
 */
@Entity
@Table(name="product")
public class Product {
    /* Atributos */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private float price;
    
    @NotNull
    @OneToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_pictures", joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "picture_id"))
    private Collection<Picture> picture;
    
    /*Constructores */

    public Product() {
    }

    public Product(String name, float price, Collection<Picture> picture) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.picture = picture;
    }
    /*Getters and Setters*/

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

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public Collection<Picture> getPicture() {
        return picture;
    }

    public void setPicture(Collection<Picture> picture) {
        this.picture = picture;
    }
    
}   
