package com.mc.modelagem.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mc.modelagem.domain.Cliente;
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
	public ResponseEntity<?> findAll() {
		
		List<Cliente> clientes  = clienteService.findAll();
		
		return ResponseEntity.ok(clientes);
		
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<?> create(@RequestBody Cliente cliente) {
		
		Cliente c  = clienteService.save(cliente);
		
		return ResponseEntity.ok(c);
		
	}

}
