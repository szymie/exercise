package org.szymie.exercise;

import org.h2.server.web.WebServlet;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.support.TransactionTemplate;
import org.szymie.exercise.boundaries.adapters.TransactionExecutor;
import org.szymie.exercise.boundaries.adapters.PasswordEncoder;
import org.szymie.exercise.boundaries.repositories.PersonRepository;
import org.szymie.exercise.boundaries.repositories.ReservationRepository;
import org.szymie.exercise.boundaries.repositories.TableRepository;
import org.szymie.exercise.boundaries.use_cases.create_person.CreatePerson;
import org.szymie.exercise.boundaries.use_cases.create_table.CreateTable;
import org.szymie.exercise.boundaries.use_cases.list_reservations.ListReservations;
import org.szymie.exercise.boundaries.use_cases.list_tables.ListTables;
import org.szymie.exercise.boundaries.use_cases.make_reservation.MakeReservation;
import org.szymie.exercise.external.adapters.PasswordEncoderImpl;
import org.szymie.exercise.external.adapters.SpringTransactionExecutor;
import org.szymie.exercise.external.entities.PersonEntity;
import org.szymie.exercise.external.entities.RoleEntity;
import org.szymie.exercise.external.repositories.JpaPersonRepository;
import org.szymie.exercise.external.repositories.JpaRoleRepository;
import org.szymie.exercise.use_cases.*;

import java.util.Collections;

@EntityScan(basePackageClasses = {ExerciseApplication.class, Jsr310JpaConverters.class})
@SpringBootApplication
public class ExerciseApplication {

	public static void main(String[] args) {
		SpringApplication.run(ExerciseApplication.class, args);
	}

	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
	    return new BCryptPasswordEncoder();
    }

    @Bean
	public PasswordEncoder passwordEncoder(BCryptPasswordEncoder bCryptPasswordEncoder) {
	    return new PasswordEncoderImpl(bCryptPasswordEncoder);
    }

    @Bean
    public CreatePerson createPerson(PersonRepository personRepository, PasswordEncoder passwordEncoder) {
	    return new CreatePersonImpl(personRepository, passwordEncoder);
    }

    @Bean
    public CreateTable createTable(TableRepository tableRepository) {
        return new CreateTableImpl(tableRepository);
    }

    @Bean
    public ListReservations listReservations(ReservationRepository reservationRepository) {
        return new ListReservationImpl(reservationRepository);
    }

    @Bean
    public ListTables listTables(TableRepository tableRepository) {
        return new ListTablesImpl(tableRepository);
    }

    @Bean
    public TransactionExecutor transactionExecutor(TransactionTemplate transactionTemplate) {
	    return new SpringTransactionExecutor(transactionTemplate);
    }

    @Bean
    public MakeReservation makeReservation(TransactionExecutor transactionExecutor, ReservationRepository reservationRepository, PersonRepository personRepository) {
        return new MakeReservationImpl(transactionExecutor, reservationRepository, personRepository);
    }

	@Bean
	public CommandLineRunner commandLineRunner(JpaPersonRepository jpaPersonRepository, JpaRoleRepository jpaRoleRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
	    return (args) -> {
            jpaRoleRepository.save(new RoleEntity(null, "CUSTOMER", Collections.emptySet()));
            RoleEntity ownerRoleEntity = jpaRoleRepository.save(new RoleEntity(null, "OWNER", Collections.emptySet()));
            jpaPersonRepository.save(new PersonEntity(null, "admin", bCryptPasswordEncoder.encode("admin"), Collections.singleton(ownerRoleEntity)));
        };
    }

    @Bean
    ServletRegistrationBean h2servletRegistration() {
        ServletRegistrationBean registrationBean = new ServletRegistrationBean(new WebServlet());
        registrationBean.addUrlMappings("/console/*");
        return registrationBean;
    }
}
