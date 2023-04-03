package com.example.usm.tickets_project.converter;

import com.example.usm.tickets_project.dto.EmployeeDto;
import com.example.usm.tickets_project.model.Block;
import com.example.usm.tickets_project.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class EmployeeConverter {

    private final PasswordEncoder passwordEncoder;
    private final BlockConverter blockConverter;
    @Autowired
    public EmployeeConverter(final PasswordEncoder passwordEncoder, final BlockConverter blockConverter) {
        this.passwordEncoder = passwordEncoder;
        this.blockConverter = blockConverter;
    }

    public EmployeeDto fromEmployeeToEmployeeDto(Employee employee){
        return EmployeeDto.builder()
                .employeeId(employee.getEmployeeId())
                .blocks(employee.getBlocks().stream().map(blockConverter::fromBlockToLongBlockId).collect(Collectors.toList()))
                .firstName(employee.getFirstName())
                .lastName(employee.getLastName())
                .login(employee.getLogin())
                .password(employee.getPassword())
                .category(employee.getCategory())
                .role(employee.getRole())
                .status(employee.getStatus())
                .build();
    }

    public Employee fromEmployeeDtoToEmployee(EmployeeDto employeeDto){
        Employee employee = new Employee();
        employee.setEmployeeId(employeeDto.getEmployeeId());
        employee.setBlocks(employeeDto.getBlocks().stream().map(blockConverter::fromLongBlockIdToBlock).collect(Collectors.toSet()));
        employee.setFirstName(employeeDto.getFirstName());
        employee.setLastName(employeeDto.getLastName());
        employee.setLogin(employeeDto.getLogin());
        employee.setPassword(passwordEncoder.encode(employeeDto.getPassword()));
        employee.setCategory(employeeDto.getCategory());
        employee.setRole(employeeDto.getRole());
        employee.setStatus(employeeDto.getStatus());
        return employee;
    }
}
