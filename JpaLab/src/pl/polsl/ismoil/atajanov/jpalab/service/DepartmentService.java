/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.polsl.ismoil.atajanov.jpalab.service;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import pl.polsl.ismoil.atajanov.jpalab.model.Department;
import pl.polsl.ismoil.atajanov.jpalab.model.Employee;
/**
 *
 * @author Ismail
 */
public class DepartmentService extends BaseService<Department>{

    public DepartmentService(EntityManagerFactory emf){
        super(emf);
    }
     @Override
    public Class getEntityClass() {
        return Department.class;
    }

    @Override
    String getFindAllQuery() {
        return Department.FIND_ALL;
    }

    @Override
    public String getEntityName() {
        return "Department";
    }
    
    /**
     * Generates the query from the parameters passed
     * @param departmentName piece of department name to look for
     * @param address piece of department address to look for
     * @return a CriteriaQuery for departments
     */
    private CriteriaQuery<Department> getCriteriaQuery(String departmentName, String address){
        Expression expr; // refers to the attributes of entity class
        Root<Department> queryRoot; // entity/table from which the selection is performed
        CriteriaQuery<Department> queryDefinition; // query being built
        List<Predicate> predicates = new ArrayList<>(); // list of conditions in the where clause
        CriteriaBuilder builder = em.getCriteriaBuilder(); // creates predicates

        queryDefinition = builder.createQuery(Department.class);
        // defines the from part of the query
        queryRoot = queryDefinition.from(Department.class);
        // defines the select part of the query
        // at this point we have a query select s from Student s (select * from student in SQL)
        queryDefinition.select(queryRoot);
        if (departmentName != null) {
            // gets access to the field called name in the Student class
            expr = queryRoot.get("departmentName");
            // creates condition of the form s.name LIKE name
            predicates.add(builder.like(expr, "%" + departmentName + "%"));
        }
        if (address != null) {
            // gets access to the field called name in the Student class
            expr = queryRoot.get("address");
            // creates condition of the form s.average >= average
            predicates.add(builder.like(expr,"%" + address + "%"));
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
    
    /**
     * Implementation of searching by matching entity's parameters
     * @param depName piece of department name to look for
     * @param addresss piece of department address to look for
     * @return a list of departments that were found
     */
    public List<Department> findByParemeters(String depName, String addresss) {
        CriteriaQuery<Department> query = getCriteriaQuery(depName, addresss);

        return em.createQuery(query).getResultList();
    }
    
}
