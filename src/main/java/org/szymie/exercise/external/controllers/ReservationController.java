package org.szymie.exercise.external.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.szymie.exercise.external.dtos.ReservationDto;
import org.szymie.exercise.external.services.ReservationService;

import javax.validation.Valid;
import java.net.URI;
import java.security.Principal;

@RestController
@RequestMapping("/api/reservations")
public class ReservationController extends BaseResourceController {

    private ReservationService reservationService;

    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @GetMapping
    public String getAll(Principal principal) {
        return "name: " + principal.getName();
    }

    @PostMapping
    public ResponseEntity<?> add(@Valid @RequestBody ReservationDto reservationDto, Principal principal) {
        Long reservationId = reservationService.addReservation(principal.getName(), reservationDto);
        URI location = buildURIForCreatedResource(String.valueOf(reservationId));
        return ResponseEntity.created(location).build();
    }

}
