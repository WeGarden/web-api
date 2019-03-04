package com.wegarden.api.protocols;

import com.wegarden.api.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/protocols")
public class ProtocolController {

    @Autowired
    private ProtocolRepository protocolRepository;

    @GetMapping
    public List<Protocol> getProtocols(){
        return protocolRepository.findAll();
    }

    @GetMapping("/{protocolId}")
    public Protocol getProtocolById(@PathVariable(value = "protocolId") Long protocolId){
        return protocolRepository.findById(protocolId)
                .orElseThrow(() -> new ResourceNotFoundException("Protocol","id",protocolId));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Long> addProtocol(@RequestBody @Valid Protocol protocol){
        Protocol p = protocolRepository.save(protocol);
        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath().path("/api/protocols/{id}")
                .buildAndExpand(p.getId()).toUri();
        return ResponseEntity.created(location)
                .body(p.getId());
    }
}
