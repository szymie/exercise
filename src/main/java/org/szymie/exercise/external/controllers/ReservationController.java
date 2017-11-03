package org.szymie.exercise.external.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/api/reservations")
public class ReservationController {

    @GetMapping
    public String getAll(Principal principal) {
        return "name: " + principal.getName();
    }

}
