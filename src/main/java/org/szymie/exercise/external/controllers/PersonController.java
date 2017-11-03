package org.szymie.exercise.external.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.szymie.exercise.external.dtos.PersonDto;
import org.szymie.exercise.external.services.PersonService;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/api/people")
public class PersonController extends BaseResourceController {

    private PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @PostMapping
    public ResponseEntity<?> add(@Valid @RequestBody PersonDto personDto) {
        Long personId = personService.addPerson(personDto);
        URI location = buildURIForCreatedResource(String.valueOf(personId));
        return ResponseEntity.created(location).build();
    }
}
