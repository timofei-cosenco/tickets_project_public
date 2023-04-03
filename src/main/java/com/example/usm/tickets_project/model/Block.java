package com.example.usm.tickets_project.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "blocks")
public class Block {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="block_id")
    private Long blockId;
    @Column(name="description")
    private String description;
    @ManyToMany(mappedBy="blocks")
    private List<Employee> employees;
}
