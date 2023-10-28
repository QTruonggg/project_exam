package com.example.project.dao.impl;

import com.example.project.dao.CustomerDAO;
import com.example.project.entity.CustomerEntity;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAOImpl implements CustomerDAO {
    EntityManager en;
    EntityTransaction tran;

    public CustomerDAOImpl() {
        en = PersistenceUtil.createEntityManagerFactory().createEntityManager();
        tran = en.getTransaction();
    }

    @Override
    public void createCustomer(CustomerEntity customer) {
        try {
            tran.begin();
            en.persist(customer);
            tran.commit();
        } catch (Exception ex) {
            System.out.printf(ex.getMessage());
            tran.rollback();
        }
    }

    @Override
    public List<CustomerEntity> getAllCustomer() {
        List<CustomerEntity> customers = new ArrayList<>();
        try {
            Query query = en.createQuery("select c from CustomerEntity c");
            return query.getResultList();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return customers;
    }
    @Override
    public CustomerEntity findById(int id) {
        Query query = en.createQuery("SELECT s FROM CustomerEntity s WHERE s.id = :id");
        query.setParameter("id", id);
        try {
            return (CustomerEntity) query.getSingleResult();
        } catch (NoResultException ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }
}
