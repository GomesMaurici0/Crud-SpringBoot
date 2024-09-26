package com.management.task.entities;

import com.management.task.enums.StatusTask;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Links;
import org.springframework.hateoas.RepresentationModel;

import java.util.Map;

@Entity
@Table(name = "tabelaCrud")
@Schema(description = "Entidade que representa a Tarefa")
public class EntityManagementTask extends RepresentationModel<EntityManagementTask> {

	@Schema(description = "ID da tarefa", example = "2")
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Schema(description = "Titulo da tarefa",example = "Estudar Java" )
	private String titulo;

	@Schema(description = "Descrição da tarefa ", example = "Exemplo de um estudo Java")
	private String descricao;

	@Schema(description = "Status da tarefa",example = "PENDENTE")
	private StatusTask status;



	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public StatusTask getStatus() {
		return status;
	}

	public void setStatus(StatusTask status) {
		this.status = status;
	}
}
