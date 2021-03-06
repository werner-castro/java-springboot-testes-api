package com.example.api.resources;

import com.example.api.domain.dto.UserDTO;
import com.example.api.services.UserService;
import org.jetbrains.annotations.NotNull;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/user")
public class UserController {

    public static final String ID = "/{id}";
    @Autowired
    private ModelMapper mapper;
    @Autowired
    UserService service;

    @PostMapping
    public ResponseEntity<UserDTO> create(@Valid @RequestBody UserDTO obj){
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path(ID).buildAndExpand(
                service.create(obj).getId()
        ).toUri();
        return ResponseEntity.created(uri).build();
    }

    @GetMapping
    public ResponseEntity<List<UserDTO>> findAll(){
        return ResponseEntity.ok().body(
                service.findAll().stream().map(x -> mapper.map(x, UserDTO.class)).collect(Collectors.toList())
        );
    }

    @PutMapping(value = ID)
    public ResponseEntity<UserDTO> update(@Valid @PathVariable Integer id, @RequestBody @NotNull UserDTO obj){
        obj.setId(id);
        return ResponseEntity.ok().body(
                mapper.map(service.update(obj), UserDTO.class)
        );
    }

    @DeleteMapping(value = ID)
    public ResponseEntity<UserDTO> delete(@PathVariable Integer id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping(value = ID)
    public ResponseEntity<UserDTO> findById(@PathVariable Integer id){
        return ResponseEntity.ok().body(
                mapper.map(service.findById(id), UserDTO.class)
        );
    }
}
