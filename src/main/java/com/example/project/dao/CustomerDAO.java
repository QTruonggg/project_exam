package com.example.project.dao;


import com.example.project.entity.CustomerEntity;

import java.util.List;

public interface CustomerDAO {
    void createCustomer(CustomerEntity customer);
    List<CustomerEntity> getAllCustomer();
    CustomerEntity findById(int id);
}