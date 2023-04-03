package com.example.usm.tickets_project.converter;

import com.example.usm.tickets_project.dto.RequestDto;
import com.example.usm.tickets_project.model.Request;
import com.example.usm.tickets_project.repository.BlockRepository;
import com.example.usm.tickets_project.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.time.Clock;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class RequestConverter {

    private final BlockRepository blockRepository;
    private final Clock clock;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");

    @Autowired
    public RequestConverter(final BlockRepository blockRepository, final Clock clock){
        this.blockRepository = blockRepository;
        this.clock = clock;
    }
    public RequestDto fromRequestToRequestDto(Request request){
        return RequestDto.builder()
                .requestId(request.getRequestId())
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .phone(request.getPhone())
                .cabinetNumber(request.getCabinetNumber())
                .category(request.getCategory())
                .bodyRequest(request.getBodyRequest())
                .employeeComment(request.getEmployeeComment())
                .description(request.getBlock().getDescription())
                .dispatchTime(request.getDispatchTime())
                .leadTime(request.getLeadTime())
                .status(request.getStatus())
                .build();
    }

    public Request fromRequestDtoToRequest(RequestDto requestDto){

        Request request = new Request();
        request.setRequestId(requestDto.getRequestId());
        request.setFirstName(requestDto.getFirstName());
        request.setLastName(requestDto.getLastName());
        request.setEmail(requestDto.getEmail());
        request.setPhone(requestDto.getPhone());
        request.setCabinetNumber(requestDto.getCabinetNumber());
        request.setCategory(requestDto.getCategory());
        request.setBodyRequest(requestDto.getBodyRequest());
        request.setEmployeeComment(requestDto.getEmployeeComment());
        request.setDispatchTime(LocalDateTime.now(clock).format(formatter));
        request.setLeadTime("PENDING");
        request.setStatus("ACTIVE");
        request.setBlock(blockRepository.findById(requestDto.getBlockNumber()));
        return request;
    }
}
