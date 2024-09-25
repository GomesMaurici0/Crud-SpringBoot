package com.management.task.services;

import com.management.task.controllers.TaskManagementController;
import com.management.task.dtos.TaskManagementDTO;
import com.management.task.entities.EntityManagementTask;
import com.management.task.repositories.RepositoryTask;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;


@Service
public class ServiceManagementTask {

	@Autowired
	RepositoryTask repository;

	public EntityManagementTask entityTaskSave(TaskManagementDTO taskDTO) {
		EntityManagementTask entity = new EntityManagementTask();
		BeanUtils.copyProperties(taskDTO,entity);
		return repository.save(entity);
	}

	public Page<EntityManagementTask> getTaskfindAllPage(Pageable pageable) {
		Page<EntityManagementTask> taskPage = repository.findAll(pageable);
		if (!taskPage.isEmpty()) {
			for (EntityManagementTask entity : taskPage) {
				entity.add(linkTo(methodOn(TaskManagementController.class).getOne(entity.getId())).withSelfRel());
			}
		}
		return taskPage;
	}


	public EntityManagementTask validationID(Long id) {
		Optional<EntityManagementTask> taskOptional = repository.findById(id);
		if (taskOptional.isEmpty()) {
			throw new IllegalArgumentException("Dados não existe.");
		} else {
			EntityManagementTask entityIdTask = taskOptional.get();
			entityIdTask.add(linkTo(methodOn(TaskManagementController.class).getOne(entityIdTask.getId())).withSelfRel());
			return entityIdTask;
		}
	}

	public EntityManagementTask postValid(TaskManagementDTO taskDTO) {
		if (repository.existsByTituloAndDescricao(taskDTO.titulo(), taskDTO.descricao())) {
			throw new IllegalArgumentException("Dados Já existente.");
		}
		EntityManagementTask entity = new EntityManagementTask();
		BeanUtils.copyProperties(taskDTO,entity);
		return repository.save(entity);
	}
	public void deleteValid(Long id){
		repository.delete(validationID(id));
	}
	public EntityManagementTask putValid(Long id, TaskManagementDTO taskDTO){
		EntityManagementTask entityValid = validationID(id);
		BeanUtils.copyProperties(taskDTO,entityValid);
		return repository.save(entityValid);
	}
}


