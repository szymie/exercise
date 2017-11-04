package org.szymie.exercise.external.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.szymie.exercise.external.dtos.PersonDto;
import org.szymie.exercise.external.dtos.ReservationDto;
import org.szymie.exercise.external.services.PersonService;
import org.szymie.exercise.external.services.ReservationService;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/people")
public class PersonController extends BaseResourceController {

    private PersonService personService;
    private ReservationService reservationService;

    public PersonController(PersonService personService, ReservationService reservationService) {
        this.personService = personService;
        this.reservationService = reservationService;
    }

    @PostMapping
    public ResponseEntity<?> add(@Valid @RequestBody PersonDto personDto) {
        Long personId = personService.addPerson(personDto);
        URI location = buildURIForCreatedResource(String.valueOf(personId));
        return ResponseEntity.created(location).build();
    }

    @GetMapping("/{personId}/reservations")
    public ResponseEntity<?> getReservations(@RequestParam(value = "page", defaultValue = "0") int page, @RequestParam(value = "size", defaultValue = "10") int size,
                                             @PathVariable Long personId) {
        List<ReservationDto> reservations = reservationService.getReservationsByPersonId(personId, page, size);
        return ResponseEntity.ok(reservations);
    }
}
