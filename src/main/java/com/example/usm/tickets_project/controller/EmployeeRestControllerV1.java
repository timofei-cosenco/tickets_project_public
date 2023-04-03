package com.example.usm.tickets_project.controller;

import com.example.usm.tickets_project.dto.EmployeeDto;
import com.example.usm.tickets_project.model.Category;
import com.example.usm.tickets_project.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v1/employees")

public class EmployeeRestControllerV1 {
    private final EmployeeService employeeService;

    @Autowired
    public EmployeeRestControllerV1(final EmployeeService employeeService){
        this.employeeService = employeeService;
    }

    @GetMapping("/findAll")
    @PreAuthorize("hasAuthority('developers:write')")
    public List<EmployeeDto> findAllEmployees(){
        return employeeService.findAll();
    }

    @GetMapping("/findByLogin/{login}")
    @PreAuthorize("hasAuthority('developers:read')")
    public EmployeeDto findByLogin(@PathVariable String login){
        return employeeService.findByLogin(login);
    }

    @GetMapping("/findAllByCategory/{category}")
    @PreAuthorize("hasAuthority('developers:read')")
    public List<EmployeeDto> findAllByCategory(@PathVariable Category category){return employeeService.findAllByCategory(category);}

    @PostMapping("/save")
    @PreAuthorize("hasAuthority('developers:write')")
    public ResponseEntity<Object> saveEmployee(@RequestBody EmployeeDto employeeDto){
        employeeService.save(employeeDto);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PutMapping("update/{employeeId}")
    @PreAuthorize("hasAuthority('developers:write')")
    public ResponseEntity<EmployeeDto> updateEmployee(@PathVariable Long employeeId, @RequestBody EmployeeDto employeeDto){
        EmployeeDto updatedEmployee = employeeService.update(employeeId, employeeDto);
        return ResponseEntity.ok(updatedEmployee);
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasAuthority('developers:write')")
    public ResponseEntity<Void> deleteEmployee(@PathVariable Long id){
        employeeService.delete(id);
        return ResponseEntity.ok().build();
    }
}
