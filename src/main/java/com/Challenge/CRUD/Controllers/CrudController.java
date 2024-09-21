package com.Challenge.CRUD.Controllers;

import com.Challenge.CRUD.dtos.CrudDto;
import com.Challenge.CRUD.entities.EntityCrud;
import com.Challenge.CRUD.repositories.CrudRepository;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class CrudController {

	@Autowired
	CrudRepository repository;

	@PostMapping("api/task")
	public ResponseEntity<EntityCrud> post (@RequestBody @Valid CrudDto crudDto){
		var entity = new EntityCrud();
		BeanUtils.copyProperties(crudDto,entity);
		return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(entity));
	}
	@GetMapping("api/task")
	public ResponseEntity<Page<EntityCrud>> getAll (@PageableDefault(value = 10) Pageable pageable){
		Page<EntityCrud> crudList = repository.findAll(pageable);
		if (!crudList.isEmpty()){
			for (EntityCrud entity : crudList){
				entity.add(linkTo(methodOn(CrudController.class).getOne(entity.getId())).withSelfRel());
			}
		}
		return ResponseEntity.status(HttpStatus.OK).body(crudList);
	}

	@GetMapping("api/task/{id}")
	public ResponseEntity<EntityCrud> getOne (@PathVariable(name = "id")Long id){

		Optional<EntityCrud> crudOptional = repository.findById(id) ;
		if (crudOptional.isEmpty()){
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
		var entity = crudOptional.get();
		entity.add(linkTo(methodOn(CrudController.class).getAll(Pageable.unpaged())).withSelfRel());
		return ResponseEntity.status(HttpStatus.FOUND).body(entity);
	}

	@DeleteMapping("api/task/{id}")
	public ResponseEntity delete(@PathVariable(name = "id")Long id){
		Optional<EntityCrud> crudOptional = repository.findById(id);
		if (crudOptional.isEmpty()){
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
		repository.delete(crudOptional.get());
		return ResponseEntity.status(HttpStatus.OK).body(null);
	}

	@PutMapping("api/task/{id}")
	public ResponseEntity<EntityCrud> put (@PathVariable(name = "id")Long id, @RequestBody @Valid CrudDto crudDto){
		Optional<EntityCrud> crudOptional = repository.findById(id);
		if (crudOptional.isEmpty()){
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
		var entity = crudOptional.get();
		BeanUtils.copyProperties(crudDto,entity);
		return ResponseEntity.status(HttpStatus.OK).body(entity);
	}
}







