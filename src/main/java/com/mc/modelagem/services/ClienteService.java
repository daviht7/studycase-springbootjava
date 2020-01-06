package com.mc.modelagem.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.mc.modelagem.domain.Cliente;
import com.mc.modelagem.exceptions.ServiceConstraintViolationException;
import com.mc.modelagem.exceptions.ServiceObjectNotFoundException;
import com.mc.modelagem.repositories.ClienteRepository;

@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository clienteRepository;

	
	public Cliente findById(Integer id) {
		Optional<Cliente> cliente = clienteRepository.findById(id);
		if(cliente.isEmpty()) 
			throw new ServiceObjectNotFoundException("Objeto não foi encontrado");
			
		return cliente.get();
	}
	
	public Cliente save(Cliente cliente) {
		Cliente c = clienteRepository.save(cliente);
		return c;
	}

	public Page<Cliente> findAll(Integer page, Integer linesPerPage, String orderBy, String direction) {		
		PageRequest pageRequest = PageRequest.of(page,linesPerPage,Direction.valueOf(direction),orderBy);
		return clienteRepository.findAll(pageRequest);
	}
	
	public void delete(Integer id) {
		Cliente c = findById(id);
		
		if(c.getPedidos().size() > 0)
			throw new ServiceConstraintViolationException("O cliente possui Pedidos, não é possível excluí-lo.");
		
		clienteRepository.deleteById(id);		
	}
	
}
