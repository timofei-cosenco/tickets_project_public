package com.example.usm.tickets_project.repository.impl;

import com.example.usm.tickets_project.model.Block;
import com.example.usm.tickets_project.repository.BlockRepository;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class BlockRepositoryImpl implements BlockRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Block findById(Long id){
        return entityManager.find(Block.class, id);
    }
}
