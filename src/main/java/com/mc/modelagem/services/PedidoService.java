package com.mc.modelagem.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mc.modelagem.domain.Pedido;
import com.mc.modelagem.repositories.PedidoRepository;

@Service
public class PedidoService {
	
	@Autowired
	private PedidoRepository PedidoRepository;

	
	public Pedido buscar(Integer id) {
		
		Optional<Pedido> Pedido = PedidoRepository.findById(id);
		
		return Pedido.orElse(null);
		
	}
	
	public Pedido save(Pedido Pedido) {
		
		Pedido c = PedidoRepository.save(Pedido);
		
		return c;
		
	}
	
}
