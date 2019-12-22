package com.mc.modelagem.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mc.modelagem.domain.Cliente;
import com.mc.modelagem.exceptions.ObjectNotFoundException;
import com.mc.modelagem.repositories.ClienteRepository;

@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository clienteRepository;

	
	public Cliente findById(Integer id) {
		
		Optional<Cliente> cliente = clienteRepository.findById(id);
		if(cliente.isEmpty()) 
			throw new ObjectNotFoundException("Objeto não foi encontrado");
			
		return cliente.get();
		
	}
	
	public Cliente save(Cliente cliente) {
		
		Cliente c = clienteRepository.save(cliente);
		
		return c;
		
	}

	public List<Cliente> findAll() {
		List<Cliente> clientes = clienteRepository.findAll();
		return clientes;
	}
	
}
