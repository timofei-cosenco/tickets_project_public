package com.example.usm.tickets_project.repository.impl;

import com.example.usm.tickets_project.model.Category;
import com.example.usm.tickets_project.model.Request;
import com.example.usm.tickets_project.repository.RequestRepository;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.time.Clock;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Repository
public class RequestRepositoryImpl implements RequestRepository {
    @PersistenceContext
    private EntityManager entityManager;

    private final Clock clock;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");
    @Autowired
    public RequestRepositoryImpl(final Clock clock) {
        this.clock = clock;
    }

    @Override
    @Transactional
    public Request save(Request request){
          entityManager.persist(request);
          return request;
    }

    @Override
    @Transactional
    public Request updateStatus(Long requestId, String employeeComment, Long employeeId){
        Request request = entityManager.find(Request.class,requestId);
        request.setStatus("DISABLE");
        request.setLeadTime(LocalDateTime.now(clock).format(formatter));
        request.setEmployeeComment(employeeComment);
        request.setEmployeeId(employeeId);
        entityManager.merge(request);
        return request;
    }

    @Override
    public List<Request> findAll(){
        return entityManager.createQuery("SELECT r FROM Request r WHERE r.status = :status", Request.class).setParameter("status","ACTIVE").getResultList();
    }
    @Override
    public List<Request> findAllDone(){
        return entityManager.createQuery("SELECT r FROM Request r WHERE r.status = :status",Request.class).setParameter("status","DISABLE").getResultList();
    }

    @Override
    public List<Request> findByBlockAndCategory(Long blockId, Category category){
        return entityManager.createQuery("SELECT r FROM Request r WHERE r.block.blockId = :blockId AND r.status = :status AND r.category = :category", Request.class)
                .setParameter("blockId",blockId).setParameter("status","ACTIVE").setParameter("category", category).getResultList();
    }

    @Override
    public List<Request> findDoneInBlock(Long blockId, Category category){
        return entityManager.createQuery("SELECT r FROM Request r WHERE r.block.blockId = :blockId AND r.status = :status AND r.category = :category", Request.class)
                .setParameter("blockId",blockId).setParameter("status","DISABLE").setParameter("category", category).getResultList();
    }

    @Override
    public Request findById(Long id){
        return entityManager.find(Request.class, id);
    }

    @Override
    @Transactional
    public void delete(Long id){
        Request request = entityManager.find(Request.class, id);
        entityManager.remove(request);
    }

    @Override
    public Integer countAllRequests(){
        return entityManager.createQuery("SELECT r FROM Request r",Request.class).getResultList().size();
    }
    @Override
    public Integer countAllFinalRequests(){
        return entityManager.createQuery("SELECT r FROM Request r WHERE r.status = :status",Request.class).setParameter("status","DISABLE").getResultList().size();
    }
    @Override
    public Integer countAllForCategory(Category category){
        return entityManager.createQuery("SELECT r FROM Request r WHERE r.category = :category",Request.class).setParameter("category", category).getResultList().size();
    }
}
