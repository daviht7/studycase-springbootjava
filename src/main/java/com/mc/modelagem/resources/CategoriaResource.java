package com.mc.modelagem.resources;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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
	public ResponseEntity<List<CategoriaDTO>> findAll() {
		List<Categoria> lista  = categoriaService.findAll();
		List<CategoriaDTO> listaDTO = lista.stream().map(obj -> new CategoriaDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listaDTO);
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
