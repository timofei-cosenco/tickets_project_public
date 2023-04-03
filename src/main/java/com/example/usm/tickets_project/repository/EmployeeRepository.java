package com.example.usm.tickets_project.repository;

import com.example.usm.tickets_project.model.Category;
import com.example.usm.tickets_project.model.Employee;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface EmployeeRepository {
    List<Employee> findAll();
    void save(Employee employee);
    Employee update(Long employeeId, Employee employee);
    void delete(Long id);
    List<Employee> findByBlock(Integer numberBlock);
    Employee findByLogin(String login);

    List<Employee> findAllByCategory(Category category);
}
