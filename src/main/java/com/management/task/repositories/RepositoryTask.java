package com.management.task.repositories;

import com.management.task.entities.EntityManagementTask;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositoryTask extends JpaRepository<EntityManagementTask,Long> {

	boolean existsByTituloAndDescricao(String titulo, String descricao);

}
