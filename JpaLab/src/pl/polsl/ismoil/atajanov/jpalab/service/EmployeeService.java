/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.polsl.ismoil.atajanov.jpalab.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import pl.polsl.ismoil.atajanov.jpalab.exceptions.NoEntityException;
import pl.polsl.ismoil.atajanov.jpalab.model.Department;
import pl.polsl.ismoil.atajanov.jpalab.model.Employee;
/**
 * Implementation of BaseService class for Employee 
 * @author Ismail
 * @version 1.0
 */
public class EmployeeService extends BaseService<Employee>{

    /**
     * Constructor
     * @param emf EntityManagerFactory used to create EntityManager
     */
    public EmployeeService(EntityManagerFactory emf){
        super(emf);
    }
    
    /**
     * Provides access to Class of the Controller's entity
     *
     * @return instance of Class of currently used entity
     */
    @Override
    public Class getEntityClass() {
        return Employee.class;
    }

    /**
     * Provides access to findAll named query of entity classes
     *
     * @return named query string
     */
    @Override
    String getFindAllQuery() {
        return Employee.FIND_ALL;
    }

    /**
     * Provides access to a string name of the Controller's entity
     * Generally used for displaying messages related to each entity
     * @return a String name 
     */
    @Override
    public String getEntityName() {
        return "Employee";
    }
    
    /**
     * Implementation of searching by matching entity's parameters
     * @param empName piece of employee name to search by
     * @param department department to search by
     * @param bDate birthday to search by
     * @return a list of Employees that were found
     */
    public List<Employee> findByParemeters(String empName, Department department, Date bDate) {
        CriteriaQuery<Employee> query = getCriteriaQuery(empName, department, bDate);
        return em.createQuery(query).getResultList();
    }
    
    /**
     * Generates the query from the parameters passed
     * @param employeeName piece of employee's name to look for
     * @param department employee's department to look for
     * @param bDate employee's date of birth to look for
     * @return a CriteriaQuery for departments
     */
    private CriteriaQuery<Employee> getCriteriaQuery(String employeeName, Department department, Date bDate) {
        Expression expr; // refers to the attributes of entity class
        Root<Employee> queryRoot; // entity/table from which the selection is performed
        CriteriaQuery<Employee> queryDefinition; // query being built
        List<Predicate> predicates = new ArrayList<>(); // list of conditions in the where clause
        CriteriaBuilder builder = em.getCriteriaBuilder(); // creates predicates

        queryDefinition = builder.createQuery(Employee.class);
        // defines the from part of the query
        queryRoot = queryDefinition.from(Employee.class);
        // defines the select part of the query
        // at this point we have a query select s from Student s (select * from student in SQL)
        queryDefinition.select(queryRoot);
        if (employeeName != null) {
            // gets access to the field called name in the Student class
            expr = queryRoot.get("fullName");
            // creates condition of the form s.name LIKE name
            predicates.add(builder.like(expr, "%" + employeeName + "%"));
        }
        if (bDate != null) {
            // gets access to the field called name in the Student class
            expr = queryRoot.get("birthDate");
            // creates condition of the form s.average >= average
            predicates.add(builder.equal(expr, bDate));
        }
        if (department != null) {
            expr = queryRoot.get("department");
            predicates.add(builder.equal(expr, department));
        }
        // if there are any conditions defined
        if (!predicates.isEmpty()) {
            // build the where part in which we combine the conditions using AND operator
            queryDefinition.where(
                    builder.and(predicates.toArray(
                            new Predicate[predicates.size()])));
        }
        return queryDefinition;
    }

    
}
