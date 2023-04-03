package com.example.usm.tickets_project.service;

import com.example.usm.tickets_project.dto.EmployeeDto;
import com.example.usm.tickets_project.model.Category;
import com.example.usm.tickets_project.model.Employee;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public interface EmployeeService {
    void save(EmployeeDto employeeDto);
    void delete(Long id);
    List<EmployeeDto> findByBlock(Integer numberBlock);
    List<EmployeeDto> findAll();
    EmployeeDto findByLogin(String login);
    EmployeeDto update(Long employeeId, EmployeeDto employeeDto);
    List<EmployeeDto> findAllByCategory(Category category);
}
