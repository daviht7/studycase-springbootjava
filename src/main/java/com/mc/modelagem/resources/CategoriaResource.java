package com.mc.modelagem.resources;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mc.modelagem.domain.Categoria;
import com.mc.modelagem.dto.CategoriaDTO;
import com.mc.modelagem.services.CategoriaService;

@RestController
@RequestMapping(value="/categoria")
public class CategoriaResource {
	
	@Autowired
	private CategoriaService categoriaService;
	
	@GetMapping(value="/{id}")
	public ResponseEntity<?> find(@PathVariable Integer id) {
		Categoria c  = categoriaService.find(id);
		return ResponseEntity.ok(c);
	}
	
	@GetMapping
	public ResponseEntity<Page<CategoriaDTO>> findAll(
			@RequestParam(value="page", defaultValue = "0") Integer page, 
			@RequestParam(value="lines", defaultValue = "100")Integer linesPerPage, 
			@RequestParam(value="orderBy", defaultValue = "nome")String orderBy, 
			@RequestParam(value="direction", defaultValue = "ASC") String direction) {
		Page<Categoria> lista  = categoriaService.findAll(page,linesPerPage,orderBy,direction);
		Page<CategoriaDTO> listaDTO = lista.map(obj -> new CategoriaDTO(obj));
		return ResponseEntity.ok().body(listaDTO);
	}
	
	@PostMapping
	public ResponseEntity<Categoria> create(@Valid @RequestBody CategoriaDTO category) {
		Categoria c  = categoriaService.save(category);
		return ResponseEntity.ok(c);
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<Categoria> update(@Valid @RequestBody CategoriaDTO category,@PathVariable Integer id) {
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
