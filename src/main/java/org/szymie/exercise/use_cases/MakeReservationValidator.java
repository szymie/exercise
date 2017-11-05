package org.szymie.exercise.use_cases;

import org.szymie.exercise.application_model.Person;
import org.szymie.exercise.boundaries.Validator;
import org.szymie.exercise.boundaries.repositories.PersonRepository;
import org.szymie.exercise.boundaries.repositories.TableRepository;
import org.szymie.exercise.boundaries.use_cases.make_reservation.MakeReservationRequest;
import org.szymie.exercise.boundaries.use_cases.make_reservation.MakeReservationErrors;
import org.szymie.exercise.boundaries.use_cases.make_reservation.MakeReservationResponse;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.EnumSet;

public class MakeReservationValidator implements Validator<MakeReservationRequest, MakeReservationResponse> {

    private TableRepository tableRepository;
    private PersonRepository personRepository;

    public MakeReservationValidator(TableRepository tableRepository, PersonRepository personRepository) {
        this.tableRepository = tableRepository;
        this.personRepository = personRepository;
    }

    private void validateTableName(MakeReservationRequest request, MakeReservationResponse response) {

        if(!tableRepository.exists(request.tableName)) {
            response.setTableNotExistsTrue();
        }
    }

    @Override
    public MakeReservationResponse validate(MakeReservationRequest request) {

        MakeReservationResponse response = new MakeReservationResponse(EnumSet.noneOf(MakeReservationErrors.class), null, Collections.emptyList());

        validatePersonId(request, response);
        validateTableName(request, response);
        validateStartAndEnd(request, response);

        return response;
    }

    private void validatePersonId(MakeReservationRequest request, MakeReservationResponse response) {

        Person person = personRepository.findById(request.personId)
                .orElse(new Person(null, "", null, null));

        if(!person.getUsername().equals(request.username)) {
            response.setNotAuthorizedTrue();
        }
    }

    private void validateStartAndEnd(MakeReservationRequest request, MakeReservationResponse response) {

        LocalDateTime now = LocalDateTime.now();

        if(request.start.isBefore(now) || Duration.between(now, request.start).toMinutes() < 30) {
            response.setTooSoonTrue();
        }

        if(request.end.isBefore(request.start)) {
            response.setEndBeforeStartTrue();
        }

        long minutes = Duration.between(request.start, request.end).toMinutes();

        if(minutes > 60) {
            response.setTooLongTrue();
        }
    }
}
