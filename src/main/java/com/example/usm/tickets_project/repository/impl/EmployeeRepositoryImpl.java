package com.example.usm.tickets_project.repository.impl;

import com.example.usm.tickets_project.model.Category;
import com.example.usm.tickets_project.model.Employee;
import com.example.usm.tickets_project.repository.EmployeeRepository;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class EmployeeRepositoryImpl implements EmployeeRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public void save(Employee employee){
        entityManager.persist(employee);
    }

    @Override
    @Transactional
    public Employee update(Long employeeId,Employee employee){
        return entityManager.merge(employee);

    }

    @Override
    public List<Employee> findAll(){
        String query = "SELECT e FROM Employee e";
        return entityManager.createQuery(query, Employee.class).getResultList();
    }

    @Override
    public List<Employee> findAllByCategory(Category category){
        return entityManager.createQuery("SELECT e FROM Employee e WHERE e.category = :category", Employee.class).setParameter("category",category).getResultList();
    }

    @Override
    @Transactional
    public void delete(Long id){
        Employee employee = entityManager.find(Employee.class, id);
        entityManager.remove(employee);
    }

    @Override
    public List<Employee> findByBlock(Integer numberBlock){
        return entityManager.createQuery("SELECT e FROM Employee e WHERE e.blocks = :number_block",Employee.class)
                .setParameter("number_block",numberBlock).getResultList();
    }

    @Override
    public Employee findByLogin(String login){
        return entityManager.createQuery("SELECT e FROM Employee e where e.login = :login", Employee.class).setParameter("login",login).getSingleResult();
    }
}
