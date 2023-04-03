package com.example.usm.tickets_project.repository;

import com.example.usm.tickets_project.model.Category;
import com.example.usm.tickets_project.model.Request;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface RequestRepository {
    List<Request> findAll();
    List<Request> findAllDone();
    List<Request> findByBlockAndCategory(Long blockId, Category category);
    List<Request> findDoneInBlock(Long blockId, Category category);
    Request save(Request request);
    Request updateStatus(Long requestId, String employeeComment, Long employeeId);
    void delete(Long id);
    Request findById(Long id);

    Integer countAllRequests();
    Integer countAllFinalRequests();

    Integer countAllForCategory(Category category);
}
