package org.szymie.exercise.external.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.szymie.exercise.external.dtos.PersonDto;
import org.szymie.exercise.external.repositories.JpaPersonRepository;
import org.szymie.exercise.external.services.PersonService;

import javax.validation.Valid;
import java.net.URI;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/people")
public class PersonController {

    private PersonService personService;

    @Autowired
    private JpaPersonRepository jpaPersonRepository;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @PostMapping
    public ResponseEntity<?> add(@Valid @RequestBody PersonDto personDto) {

        Long personId = personService.addPerson(personDto);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(personId).toUri();

        return ResponseEntity.created(location).build();
    }

    @GetMapping
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(jpaPersonRepository.findAll().stream().map(personEntity -> new PersonDto(personEntity.getId(), personEntity.getName(), personEntity.getPassword())).collect(Collectors.toList()));
    }
}
