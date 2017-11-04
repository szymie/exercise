package org.szymie.exercise.external.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.szymie.exercise.application_model.Reservation;
import org.szymie.exercise.external.dtos.ReservationDto;
import org.szymie.exercise.external.services.ReservationService;

import javax.validation.Valid;
import java.net.URI;
import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/reservations")
public class ReservationController extends BaseResourceController {

    private ReservationService reservationService;

    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @GetMapping
    public ResponseEntity<?> getAll(@RequestParam(value = "page", defaultValue = "0") int page, @RequestParam(value = "size", defaultValue = "10") int size) {
        List<ReservationDto> tables = reservationService.getReservations(page, size);
        return ResponseEntity.ok(tables);
    }


    @PostMapping
    public ResponseEntity<?> add(@Valid @RequestBody ReservationDto reservationDto, Principal principal) {
        Long reservationId = reservationService.addReservation(principal.getName(), reservationDto);
        URI location = buildURIForCreatedResource(String.valueOf(reservationId));
        return ResponseEntity.created(location).build();
    }

}
