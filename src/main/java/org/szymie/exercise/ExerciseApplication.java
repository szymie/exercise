package org.szymie.exercise;

import org.h2.server.web.WebServlet;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.szymie.exercise.application_model.Person;
import org.szymie.exercise.boundaries.adapters.PasswordEncoder;
import org.szymie.exercise.boundaries.repositories.PersonRepository;
import org.szymie.exercise.boundaries.repositories.TableRepository;
import org.szymie.exercise.boundaries.use_cases.create_person.CreatePerson;
import org.szymie.exercise.boundaries.use_cases.create_table.CreateTable;
import org.szymie.exercise.boundaries.use_cases.list_tables.ListTables;
import org.szymie.exercise.external.adapters.PasswordEncoderImpl;
import org.szymie.exercise.external.entities.PersonEntity;
import org.szymie.exercise.external.entities.RoleEntity;
import org.szymie.exercise.external.repositories.JpaPersonRepository;
import org.szymie.exercise.external.repositories.JpaRoleRepository;
import org.szymie.exercise.use_cases.CreatePersonImpl;
import org.szymie.exercise.use_cases.CreateTableImpl;
import org.szymie.exercise.use_cases.ListTablesImpl;

import java.util.Collections;
import java.util.Optional;

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
    public ListTables listTables(TableRepository tableRepository) {
        return new ListTablesImpl(tableRepository);
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
