package com.example.usm.tickets_project.controller;

import com.example.usm.tickets_project.dto.EmployeeDto;
import com.example.usm.tickets_project.dto.RequestDto;
import com.example.usm.tickets_project.model.Block;
import com.example.usm.tickets_project.model.Category;
import com.example.usm.tickets_project.model.Employee;
import com.example.usm.tickets_project.repository.BlockRepository;
import com.example.usm.tickets_project.service.EmailService;
import com.example.usm.tickets_project.service.EmployeeService;
import com.example.usm.tickets_project.service.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v1/requests")
public class RequestRestControllerV1 {
    private final RequestService requestService;
    private final EmailService emailService;
    private final BlockRepository blockRepository;

    @Autowired
    public RequestRestControllerV1(final RequestService requestService, EmailService emailService, EmployeeService employeeService, BlockRepository blockRepository){
        this.requestService = requestService;
        this.emailService = emailService;
        this.blockRepository = blockRepository;
    }


    @GetMapping("/findAll")
    @PreAuthorize("hasAuthority('developers:read')")
    public List<RequestDto> findAllRequest(){
        return requestService.findAll();
    }

    @GetMapping("/findAllDone")
    @PreAuthorize("hasAuthority('developers:read')")
    public List<RequestDto> findAllDoneRequest(){ return requestService.findAllDone();}

    @GetMapping("/findByBlock/{blockId},{category}")
    @PreAuthorize("hasAuthority('developers:read')")
    public List<RequestDto> findByBlockAndCategory(@PathVariable Long blockId,@PathVariable Category category){return requestService.findByBlockAndCategory(blockId,category);}

    @GetMapping("/findDoneInBlock/{blockId},{category}")
    @PreAuthorize("hasAuthority('developers:read')")
    public List<RequestDto> findDoneInBlock(@PathVariable Long blockId,@PathVariable Category category){return requestService.findDoneInBlock(blockId,category);}

    @GetMapping("/countAllRequests")
    @PreAuthorize("hasAuthority('developers:read')")
    public Integer countAllRequests(){return requestService.countAllRequests();}

    @GetMapping("/countAllFinalRequests")
    @PreAuthorize("hasAuthority('developers:read')")
    public Integer countAllFinalRequests(){return requestService.countAllFinalRequests();}

    @GetMapping("/countAllForCategory/{category}")
    @PreAuthorize("hasAuthority('developers:read')")
    public Integer countAllForCategory(@PathVariable Category category){return requestService.countAllForCategory(category);}


    @PostMapping("/save")
    public ResponseEntity<Object> saveRequest(@RequestBody RequestDto requestDto){
        Block block = blockRepository.findById(requestDto.getBlockNumber());
        emailService.sendSimpleEmail(block.getEmployees().stream().filter(i -> (i.getCategory() == requestDto.getCategory())).map(Employee::getLogin).collect(Collectors.toList()));
        requestService.save(requestDto);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PutMapping("/updateStatus/{requestId},{employeeComment},{employeeId}")
    @PreAuthorize("hasAuthority('developers:read')")
    public RequestDto updateStatusRequest(@PathVariable Long requestId, @PathVariable String employeeComment, @PathVariable Long employeeId){
        return requestService.updateStatus(requestId, employeeComment, employeeId);
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasAuthority('developers:write')")
    public ResponseEntity<Void> deleteRequest(@PathVariable Long id){
        requestService.delete(id);
        return ResponseEntity.ok().build();
    }
}
