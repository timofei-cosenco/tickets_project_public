package com.example.usm.tickets_project.service.impl;

import com.example.usm.tickets_project.converter.EmployeeConverter;
import com.example.usm.tickets_project.dto.EmployeeDto;
import com.example.usm.tickets_project.model.Category;
import com.example.usm.tickets_project.model.Employee;
import com.example.usm.tickets_project.repository.EmployeeRepository;
import com.example.usm.tickets_project.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final EmployeeConverter employeeConverter;

    @Autowired
    public EmployeeServiceImpl(final EmployeeRepository employeeRepository, final EmployeeConverter employeeConverter){
        this.employeeRepository = employeeRepository;
        this.employeeConverter = employeeConverter;
    }

    @Override
    public void save(EmployeeDto employeeDto){
        employeeRepository.save(employeeConverter.fromEmployeeDtoToEmployee(employeeDto));
    }

    @Override
    public void delete(Long id){
        employeeRepository.delete(id);
    }

    @Override
    public List<EmployeeDto> findAllByCategory(Category category){
        return employeeRepository.findAllByCategory(category).stream().map(employeeConverter::fromEmployeeToEmployeeDto).collect(Collectors.toList());
    }

    @Override
    public EmployeeDto findByLogin(String login){
        Employee employee = employeeRepository.findByLogin(login);
        if(employee != null){
            return employeeConverter.fromEmployeeToEmployeeDto(employee);
        }
        return null;
    }

    @Override
    public List<EmployeeDto> findAll(){
        return employeeRepository.findAll().stream().map(employeeConverter::fromEmployeeToEmployeeDto).collect(Collectors.toList());
    }

    @Override
    public EmployeeDto update(Long employeeId,EmployeeDto employeeDto){
       return employeeConverter.fromEmployeeToEmployeeDto(employeeRepository.update(employeeId,employeeConverter.fromEmployeeDtoToEmployee(employeeDto)));
    }

    @Override
    public List<EmployeeDto> findByBlock(Integer blockNumber){
        return employeeRepository.findAll().stream().map(employeeConverter::fromEmployeeToEmployeeDto).collect(Collectors.toList());
    }

}
