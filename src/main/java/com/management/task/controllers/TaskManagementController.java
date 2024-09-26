package com.management.task.controllers;

import com.management.task.dtos.TaskManagementDTO;
import com.management.task.entities.EntityManagementTask;
import com.management.task.services.ServiceManagementTask;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
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

	@Operation(summary = "Criar uma nova tarefa",
			description = "Cria uma nova tarefa no sistema, atribuindo título, descrição e status. Retorna a tarefa criada ou um erro caso os dados já existam.",
			responses = {
					@ApiResponse(responseCode = "200",
							description = "Sucesso",
							content = @Content(mediaType = "application/json",
									schema = @Schema(implementation = EntityManagementTask.class),
									examples = @ExampleObject(value = "{\"id\": 2, \"titulo\": \"Estudar Java\", \"descricao\": \"Exemplo de um estudo Java\", \"status\": \"PENDENTE\", \"_links\": {\"self\": {\"href\": \"http://localhost:8080/apitask/1\"}}}"))),
					@ApiResponse(responseCode = "400",
							description = "Tarefa não Criada",content = @Content)
			})
	@PostMapping("task")
	public ResponseEntity<EntityManagementTask> post (@RequestBody @Valid TaskManagementDTO taskDtoDto) {
		try {
			return ResponseEntity.status(HttpStatus.CREATED).body(service.postValid(taskDtoDto));
		} catch (IllegalArgumentException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}

	@Operation(summary = "Listar todas as tarefas",
			description = "Retorna uma lista paginada de todas as tarefas registradas no sistema, com opção de paginação.",
			responses = {
					@ApiResponse(responseCode = "200",
							description = "Sucesso",

							content = @Content(mediaType = "application/json",
									schema = @Schema(implementation = EntityManagementTask.class),
									examples = @ExampleObject(value = "{\"id\": 2, \"titulo\": \"Estudar Java\", \"descricao\": \"Exemplo de um estudo Java\", \"status\": \"PENDENTE\", \"_links\": {\"self\": {\"href\": \"http://localhost:8080/apitask/1\"}}}"))),

			})
	@GetMapping("/task")
	public ResponseEntity<Page<EntityManagementTask>> getAll (@Parameter(hidden = true)@PageableDefault(page = 0,size = 10) Pageable pageable) {

		return ResponseEntity.status(HttpStatus.OK).body(service.getTaskfindAllPage(pageable));
	}

	@Operation(summary = "Buscar tarefa por ID",
			description = "Busca e retorna uma tarefa específica pelo seu ID. Retorna erro se o ID não for encontrado.",
			responses = {
			@ApiResponse(responseCode = "200",
					description = "Sucesso",
					content = @Content(mediaType = "application/json",
							schema = @Schema(implementation = EntityManagementTask.class),
							examples = @ExampleObject(value = "{\"id\": 2, \"titulo\": \"Estudar Java\", \"descricao\": \"Exemplo de um estudo Java\", \"status\": \"PENDENTE\", \"_links\": {\"self\": {\"href\": \"http://localhost:8080/apitask/1\"}}}"))),
			@ApiResponse(responseCode = "404",
					description = "Tarefa não encontrada",content = @Content)
	})
	@GetMapping("task/{id}")
	public ResponseEntity<EntityManagementTask> getOne (@PathVariable(name = "id") Long id) {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(service.validationID(id));
		} catch (IllegalArgumentException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}

	@Operation(summary = "Atualizar uma tarefa existente",
			description = "Atualiza os dados de uma tarefa com base no ID fornecido. Retorna erro se a tarefa não for encontrada.",
			responses = {
					@ApiResponse(responseCode = "200",
							description = "Sucesso",
							content = @Content(mediaType = "application/json",
									schema = @Schema(implementation = EntityManagementTask.class),
									examples = @ExampleObject(value = "{\"id\": 2, \"titulo\": \"Estudar Java\", \"descricao\": \"Exemplo de um estudo Java\", \"status\": \"PENDENTE\", \"_links\": {\"self\": {\"href\": \"http://localhost:8080/apitask/1\"}}}"))),
					@ApiResponse(responseCode = "404",
							description = "Tarefa não encontrada",content = @Content)
			})
	@PutMapping("task/{id}")
	public ResponseEntity<EntityManagementTask> put (@PathVariable(name = "id") Long id, @RequestBody @Valid TaskManagementDTO crudDto) {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(service.putValid(id, crudDto));
		} catch (IllegalArgumentException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}

	@Operation(summary = "Deletar uma tarefa",
			description = "Remove uma tarefa do sistema com base no ID fornecido. Retorna erro se o ID não for encontrado.",
			responses = {
					@ApiResponse(responseCode = "204",
							description = "Deletado",
							content = @Content),
					@ApiResponse(responseCode = "404",
							description = "Tarefa não encontrada",content = @Content)
							

			})
	@DeleteMapping("task/{id}")
	public ResponseEntity delete(@PathVariable(name = "id") Long id) {
		try {
			service.deleteValid(id);
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		} catch (IllegalArgumentException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
	}
}








