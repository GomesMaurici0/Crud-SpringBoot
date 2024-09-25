package com.management.task.dtos;

import com.management.task.enums.StatusTask;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;


public record TaskManagementDTO(@NotBlank String titulo, @NotBlank String descricao, StatusTask status ) {

	@JsonCreator
	public TaskManagementDTO(@JsonProperty("titulo") String titulo, @JsonProperty("descricao") String descricao, @JsonProperty("status") StatusTask status) {
		this.titulo = titulo;
		this.descricao = descricao;
		this.status = (status != null) ? status : StatusTask.PENDENTE;
	}
}
