package com.mc.modelagem.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mc.modelagem.domain.Pedido;
import com.mc.modelagem.services.PedidoService;

@RestController
@RequestMapping(value="/pedido")
public class PedidoResource {
	
	@Autowired
	private PedidoService PedidoService;
	
	@GetMapping(value="/{id}")
	public ResponseEntity<?> find(@PathVariable Integer id) {
		
		Pedido c  = PedidoService.buscar(id);
		
		return ResponseEntity.ok(c);
		
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<?> create(@RequestBody Pedido Pedido) {
		
		Pedido c  = PedidoService.save(Pedido);
		
		return ResponseEntity.ok(c);
		
	}

}
