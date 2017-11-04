package org.szymie.exercise.external.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.szymie.exercise.application_model.Table;
import org.szymie.exercise.external.dtos.ReservationDto;
import org.szymie.exercise.external.dtos.TableDto;
import org.szymie.exercise.external.repositories.JpaTableRepository;
import org.szymie.exercise.external.services.ReservationService;
import org.szymie.exercise.external.services.TableService;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/tables")
public class TableController extends BaseResourceController {

    private TableService tableService;
    private ReservationService reservationService;

    public TableController(TableService tableService, ReservationService reservationService) {
        this.tableService = tableService;
        this.reservationService = reservationService;
    }

    @PostMapping
    public ResponseEntity<?> add(@Valid @RequestBody TableDto tableDto) {
        tableService.addTable(tableDto.getName());
        URI location = buildURIForCreatedResource(tableDto.getName());
        return ResponseEntity.created(location).build();
    }

    @GetMapping
    public ResponseEntity<?> getAll(@RequestParam(value = "page", defaultValue = "0") int page, @RequestParam(value = "size", defaultValue = "10") int size) {
        List<TableDto> tables = tableService.getTables(page, size);
        return ResponseEntity.ok(tables);
    }

    @GetMapping("/{tableName}/reservations")
    public ResponseEntity<?> getReservations(@RequestParam(value = "page", defaultValue = "0") int page, @RequestParam(value = "size", defaultValue = "10") int size,
                                             @PathVariable String tableName) {
        List<ReservationDto> reservations = reservationService.getReservationsByTableName(tableName, page, size);
        return ResponseEntity.ok(reservations);
    }
}
