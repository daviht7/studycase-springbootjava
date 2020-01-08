package com.mc.modelagem.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.mc.modelagem.domain.Cidade;
import com.mc.modelagem.domain.Cliente;
import com.mc.modelagem.domain.Endereco;
import com.mc.modelagem.domain.enums.TipoCliente;
import com.mc.modelagem.dto.ClienteNewDTO;
import com.mc.modelagem.exceptions.ServiceConstraintViolationException;
import com.mc.modelagem.exceptions.ServiceObjectNotFoundException;
import com.mc.modelagem.repositories.CidadeRepository;
import com.mc.modelagem.repositories.ClienteRepository;
import com.mc.modelagem.repositories.EnderecoRepository;

@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private CidadeRepository cidadeRepository;
	
	@Autowired
	private EnderecoRepository enderecoRepository;

	
	public Cliente findById(Integer id) {
		Optional<Cliente> cliente = clienteRepository.findById(id);
		if(cliente.isEmpty()) 
			throw new ServiceObjectNotFoundException("Objeto não foi encontrado");
			
		return cliente.get();
	}
	
	public Cliente save(Cliente cliente) {
		cliente.setId(null);
		Cliente obj = clienteRepository.save(cliente);
		enderecoRepository.saveAll(obj.getEnderecos());
		return obj;
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
	
	public Cliente fromDTO(ClienteNewDTO c) {
		Cliente cli = new Cliente(null, c.getNome(), c.getEmail(), c.getCpfcnpj(),TipoCliente.toEnum(c.getTipo()));
		Optional<Cidade> cid = cidadeRepository.findById(c.getCidadeId());
		Endereco end = new Endereco(null, c.getLogradouro(), 
				c.getLogradouro(), c.getNumero(), c.getBairro(), c.getCep(),cli , cid.get());
		
		cli.getEnderecos().add(end);
		cli.getTelefones().add(c.getTelefone1());
		
		if(c.getTelefone2() != null) {
			cli.getTelefones().add(c.getTelefone2());
		}
		if(c.getTelefone3() != null) {
			cli.getTelefones().add(c.getTelefone3());
		}
		
		return cli;
	}
	
}
