package com.example.bank_ex.Model;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Customer {

    private long id;
    private String username;
    private double balance;

    // Getters, Setters, and Constructor

}
