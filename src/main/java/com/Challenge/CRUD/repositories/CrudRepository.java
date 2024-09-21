package com.Challenge.CRUD.repositories;

import com.Challenge.CRUD.entities.EntityCrud;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CrudRepository extends JpaRepository<EntityCrud,Long> {
}
