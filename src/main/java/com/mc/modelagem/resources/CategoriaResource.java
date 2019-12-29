package com.mc.modelagem.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mc.modelagem.domain.Categoria;
import com.mc.modelagem.domain.Produto;
import com.mc.modelagem.services.CategoriaService;
import com.mc.modelagem.services.ProdutoService;

@RestController
@RequestMapping(value="/categoria")
public class CategoriaResource {
	
	@Autowired
	private CategoriaService categoriaService;
	@Autowired
	private ProdutoService produtoService;
	
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

}
