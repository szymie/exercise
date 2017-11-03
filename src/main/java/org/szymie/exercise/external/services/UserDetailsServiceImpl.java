package org.szymie.exercise.external.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.szymie.exercise.external.entities.PersonEntity;
import org.szymie.exercise.external.entities.RoleEntity;
import org.szymie.exercise.external.repositories.JpaPersonRepository;
import org.szymie.exercise.external.repositories.JpaRoleRepository;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private JpaPersonRepository personRepository;

    @Autowired
    public UserDetailsServiceImpl(JpaPersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<PersonEntity> personEntityOptional = personRepository.findByName(username);

        if(!personEntityOptional.isPresent()) {
            throw new UsernameNotFoundException(String.format("Person '%s' does not exist", username));
        } else {

            PersonEntity personEntity = personEntityOptional.get();

            Set<SimpleGrantedAuthority> grantedAuthorities = personEntity.getRoles().stream()
                    .map(roleEntity -> new SimpleGrantedAuthority(roleEntity.getName()))
                    .collect(Collectors.toSet());

            return new User(personEntity.getName(), personEntity.getPassword(), grantedAuthorities);
        }
    }
}
