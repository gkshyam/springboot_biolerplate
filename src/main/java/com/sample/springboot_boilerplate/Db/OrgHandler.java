package com.sample.springboot_boilerplate.Db;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class OrgHandler {

    @PersistenceContext
    private EntityManager entityManager;

    @SuppressWarnings("unchecked")
    public List<Object[]> listAllOrgs() {
        StringBuilder query = new StringBuilder();
        query.append("select * from t_organization ");

        Query nativeQuery = entityManager.createNativeQuery(query.toString());
        return nativeQuery.getResultList();
    }

    @SuppressWarnings("unchecked")
    public List<Object[]> getProductList(Integer id) {
        StringBuilder query = new StringBuilder();
        query.append("select id, product_name from t_product where org_id = :id ");

        Query nativeQuery = entityManager.createNativeQuery(query.toString());
        return nativeQuery.setParameter("id", id).getResultList();
    }

    @SuppressWarnings("unchecked")
    public List<Object[]> getEmployeeList(Integer id) {
        StringBuilder query = new StringBuilder();
        query.append("select id, org_id, employee_name ,emp_email , manager_email from Employee where id = :id ");

        Query nativeQuery = entityManager.createNativeQuery(query.toString());
        return nativeQuery.setParameter("id", id ).getResultList();
    }

    @SuppressWarnings("unchecked")
    public List<Object[]> getManagerEmails(Integer id, String emp_email) {
        StringBuilder query = new StringBuilder();
        query.append("select id, org_id, employee_name ,emp_email , manager_email from Employee where org_id = :id and emp_email= :emp_email");

        Query nativeQuery = entityManager.createNativeQuery(query.toString());
        return nativeQuery.setParameter("id", id ).setParameter("emp_email" ,emp_email).getResultList();
    }

    @SuppressWarnings("unchecked")
    public List<Object[]> getEmployeeEmails(Integer id, String manager_email) {
        StringBuilder query = new StringBuilder();
        query.append("select id, org_id, employee_name ,emp_email , manager_email from Employee where org_id = :id and manager_email= :manager_email");

        Query nativeQuery = entityManager.createNativeQuery(query.toString());
        return nativeQuery.setParameter("id", id ).setParameter("manager_email" ,manager_email).getResultList();
    }
}