package com.example.usm.tickets_project.service;

import com.example.usm.tickets_project.dto.RequestDto;
import com.example.usm.tickets_project.model.Category;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public interface RequestService {
    RequestDto save(RequestDto requestDto);
    void delete(Long id);
    RequestDto findById(Long id);
    List<RequestDto> findAll();
    List<RequestDto> findAllDone();
    List<RequestDto> findDoneInBlock(Long blockId, Category category);
    List<RequestDto> findByBlockAndCategory(Long blockId, Category category);
    RequestDto updateStatus(Long requestId, String employeeComment, Long employeeId);

    Integer countAllRequests();
    Integer countAllFinalRequests();
    Integer countAllForCategory(Category category);
}
