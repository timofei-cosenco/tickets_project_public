package com.example.usm.tickets_project.dto;

import com.example.usm.tickets_project.model.Category;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RequestDto {
    private Long requestId;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String cabinetNumber;
    private Category category;
    private String bodyRequest;
    private String employeeComment;
    private String dispatchTime;



    private Long employeeId;
    private String status;
    private Long blockNumber;
    private String description;
}
