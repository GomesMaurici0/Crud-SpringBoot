package com.Challenge.CRUD.dtos;

import com.Challenge.CRUD.entities.enums.Status;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


public record CrudDto(@NotBlank String titulo, @NotBlank String descricao, @NotNull Status status ) {

	@JsonCreator
	public CrudDto(@JsonProperty("titulo") String titulo, @JsonProperty("descricao") String descricao, @JsonProperty("status") Status status) {
		this.titulo = titulo;
		this.descricao = descricao;
		this.status = (status != null) ? status : Status.PENDENTE;
	}
}
