package com.example.usm.tickets_project.service.impl;

import com.example.usm.tickets_project.converter.RequestConverter;
import com.example.usm.tickets_project.dto.RequestDto;
import com.example.usm.tickets_project.model.Category;
import com.example.usm.tickets_project.model.Request;
import com.example.usm.tickets_project.repository.RequestRepository;
import com.example.usm.tickets_project.service.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RequestServiceImpl implements RequestService {

    private final RequestConverter requestConverter;
    private final RequestRepository requestRepository;

    @Autowired
    public RequestServiceImpl(final RequestRepository requestRepository, final RequestConverter requestConverter){
        this.requestRepository = requestRepository;
        this.requestConverter = requestConverter;
    }

    @Override
    public RequestDto save(RequestDto requestDto){
        Request savedRequest =
        requestRepository.save(requestConverter.fromRequestDtoToRequest(requestDto));
        return requestConverter.fromRequestToRequestDto(savedRequest);
    }

    @Override
    public void delete(Long id){
        requestRepository.delete(id);
    }

    @Override
    public RequestDto findById(Long id){
        Request request = requestRepository.findById(id);
        if(request != null){
            return requestConverter.fromRequestToRequestDto(request);
        }
        return null;
    }

    @Override
    public List<RequestDto> findAll(){
        return requestRepository.findAll().stream().map(requestConverter::fromRequestToRequestDto).collect(Collectors.toList());
    }

    @Override
    public List<RequestDto> findAllDone(){
        return requestRepository.findAllDone().stream().map(requestConverter::fromRequestToRequestDto).collect(Collectors.toList());
    }

    @Override
    public List<RequestDto> findByBlockAndCategory(Long blockId, Category category){
        return requestRepository.findByBlockAndCategory(blockId,category).stream().map(requestConverter::fromRequestToRequestDto).collect(Collectors.toList());
    }

    @Override
    public List<RequestDto> findDoneInBlock(Long blockId, Category category){
        return requestRepository.findDoneInBlock(blockId,category).stream().map(requestConverter::fromRequestToRequestDto).collect(Collectors.toList());
    }

    @Override
    public RequestDto updateStatus(Long requestId, String employeeComment, Long employeeId){
        Request request = requestRepository.updateStatus(requestId, employeeComment,employeeId);
        return requestConverter.fromRequestToRequestDto(request);
    }

    @Override
    public Integer countAllRequests(){
        return requestRepository.countAllRequests();
    }

    @Override
    public Integer countAllFinalRequests(){
        return requestRepository.countAllFinalRequests();
    }

    @Override
    public Integer countAllForCategory(Category category){
        return requestRepository.countAllForCategory(category);
    }
}
