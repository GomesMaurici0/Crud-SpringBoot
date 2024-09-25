package com.management.task.controllers;

import com.management.task.dtos.TaskManagementDTO;
import com.management.task.entities.EntityManagementTask;
import com.management.task.repositories.RepositoryTask;
import com.management.task.services.ServiceManagementTask;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class TaskManagementController {

	@Autowired
	ServiceManagementTask service;
	@Autowired
	RepositoryTask repository;

	@PostMapping("task")
	public ResponseEntity<EntityManagementTask> post (@RequestBody @Valid TaskManagementDTO taskDtoDto){
		EntityManagementTask task = service.postValid(taskDtoDto);
		return ResponseEntity.status(HttpStatus.CREATED).body(task);
	}

	@GetMapping("task")
	public ResponseEntity<Page<EntityManagementTask>> getAll (@PageableDefault(value = 10) Pageable pageable){
		return ResponseEntity.status(HttpStatus.OK).body(service.getTaskfindAllPage(pageable));
	}

	@GetMapping("task/{id}")
	public ResponseEntity<EntityManagementTask> getOne (@PathVariable(name = "id")Long id){
		return ResponseEntity.status(HttpStatus.OK).body(service.validationID(id));
	}

	@DeleteMapping("task/{id}")
	public ResponseEntity delete(@PathVariable(name = "id")Long id){
		service.deleteValid(id);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
	}

	@PutMapping("task/{id}")
	public ResponseEntity<EntityManagementTask> put (@PathVariable(name = "id")Long id, @RequestBody @Valid TaskManagementDTO crudDto){
		return ResponseEntity.status(HttpStatus.OK).body(service.putValid(id,crudDto));
	}
}







