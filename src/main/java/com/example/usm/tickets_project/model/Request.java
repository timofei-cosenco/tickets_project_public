package com.example.usm.tickets_project.model;

import javax.persistence.*;
import javax.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "requests")
public class Request {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "request_id")
    private Long requestId;

    @ManyToOne
    @JoinColumn(name="blocks_id_block")
    private Block block;

    @Column(name = "first_name")
    private String firstName;

    @Column(name="last_name")
    private String lastName;

    @Email
    @Column(name="email")
    private String email;

    @Column(name="phone")
    private String phone;

    @Column(name="cabinet_number")
    private String cabinetNumber;

    @Enumerated(value = EnumType.STRING)
    @Column(name="category")
    private Category category;

    @Column(name = "body_request")
    private String bodyRequest;

    @Column(name = "employee_comment")
    private String employeeComment;

    @Column(name = "dispatch_time")
    private String dispatchTime;

    @Column(name = "lead_time")
    private String leadTime;

    @Column(name = "employeeId")
    private Long employeeId;

    @Column(name="status")
    private String status;
}
