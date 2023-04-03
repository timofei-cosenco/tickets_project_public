package com.example.usm.tickets_project.repository;

import com.example.usm.tickets_project.model.Block;
import org.springframework.stereotype.Repository;

@Repository
public interface BlockRepository {
    Block findById(Long id);
    
}
