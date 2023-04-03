package com.example.usm.tickets_project.model;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "employees")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="employee_id")
    private Long employeeId;

    @Column(name = "first_name")
    private String firstName;

    @Column(name="last_name")
    private String lastName;

    @Column(name="login")
    private String login;

    @Column(name="password")
    private String password;

    @Enumerated(value = EnumType.STRING)
    @Column(name="category")
    private Category category;

    @Enumerated(value = EnumType.STRING)
    @Column(name="role")
    private Role role;

    @Enumerated(value = EnumType.STRING)
    @Column(name="status")
    private Status status;

    @ManyToMany(fetch = FetchType.EAGER, cascade = { CascadeType.DETACH,CascadeType.DETACH})
    @JoinTable(name = "employee_block",
        joinColumns = {@JoinColumn(name = "employee_id") },
            inverseJoinColumns = { @JoinColumn(name = "block_id") } )
    private Set<Block> blocks = new HashSet<>();
}
