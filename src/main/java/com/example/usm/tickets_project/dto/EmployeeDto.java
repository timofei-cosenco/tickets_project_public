package com.example.usm.tickets_project.dto;

import com.example.usm.tickets_project.model.Category;
import com.example.usm.tickets_project.model.Role;
import com.example.usm.tickets_project.model.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EmployeeDto {
    private Long employeeId;
    private List<Long> blocks;
    private String firstName;
    private String lastName;
    private String login;
    private String password;
    private Category category;
    private Role role;
    private Status status;
}
