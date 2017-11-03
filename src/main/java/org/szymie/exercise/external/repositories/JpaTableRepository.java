package org.szymie.exercise.external.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.szymie.exercise.external.entities.PersonEntity;
import org.szymie.exercise.external.entities.TableEntity;

public interface JpaTableRepository extends PagingAndSortingRepository<TableEntity, String> {
}
