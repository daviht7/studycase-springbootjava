package com.mc.modelagem.resources;

import javax.validation.ConstraintViolationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mc.modelagem.domain.Categoria;
import com.mc.modelagem.services.CategoriaService;

@RestController
@RequestMapping(value="/categoria")
public class CategoriaResource {
	
	@Autowired
	private CategoriaService categoriaService;
	
	@RequestMapping(value="/{id}",method = RequestMethod.GET)
	public ResponseEntity<?> find(@PathVariable Integer id) {
		Categoria c  = categoriaService.buscar(id);
		return ResponseEntity.ok(c);
	}
	
	@PostMapping
	public ResponseEntity<Categoria> create(@RequestBody Categoria category) {
		Categoria c  = categoriaService.save(category);
		return ResponseEntity.ok(c);
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<Categoria> update(@RequestBody Categoria category,@PathVariable Integer id) {
		find(id);
		category.setId(id);
		Categoria c  = categoriaService.update(category);
		return ResponseEntity.ok(c);
	}
	
	@DeleteMapping(value="/{id}")
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		categoriaService.delete(id);
		return ResponseEntity.noContent().build();
	}

}
