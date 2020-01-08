package com.mc.modelagem.resources;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mc.modelagem.domain.Cliente;
import com.mc.modelagem.dto.ClienteNewDTO;
import com.mc.modelagem.services.ClienteService;

@RestController
@RequestMapping(value="/cliente")
public class ClienteResource {
	
	@Autowired
	private ClienteService clienteService;
	
	@GetMapping(value="/{id}")
	public ResponseEntity<?> findById(@PathVariable Integer id) {
		Cliente c  = clienteService.findById(id);
		return ResponseEntity.ok(c);
	}
	
	@GetMapping
	public ResponseEntity<Page<Cliente>> findAll(
			@RequestParam(value="page", defaultValue = "0") Integer page, 
			@RequestParam(value="lines", defaultValue = "100")Integer linesPerPage, 
			@RequestParam(value="orderBy", defaultValue = "nome")String orderBy, 
			@RequestParam(value="direction", defaultValue = "ASC") String direction) {
		Page<Cliente> lista  = clienteService.findAll(page,linesPerPage,orderBy,direction);
		return ResponseEntity.ok().body(lista);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<?> create(@Valid @RequestBody ClienteNewDTO cliente) {
		Cliente cli = clienteService.fromDTO(cliente);
		Cliente c  = clienteService.save(cli);
		return ResponseEntity.ok(c);
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<Cliente> update(@Valid @RequestBody Cliente cliente,@PathVariable Integer id) {
		findById(id);
		cliente.setId(id);
		Cliente c  = clienteService.save(cliente);
		return ResponseEntity.ok(c);
	}
	
	@DeleteMapping(value="/{id}")
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		clienteService.delete(id);
		return ResponseEntity.noContent().build();
	}

}
