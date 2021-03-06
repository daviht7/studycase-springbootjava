package com.mc.modelagem.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.mc.modelagem.domain.Categoria;
import com.mc.modelagem.dto.CategoriaDTO;
import com.mc.modelagem.exceptions.ServiceConstraintViolationException;
import com.mc.modelagem.exceptions.ServiceObjectNotFoundException;
import com.mc.modelagem.repositories.CategoriaRepository;

@Service
public class CategoriaService {
	
	@Autowired
	private CategoriaRepository categoriaRepository;

	
	public Categoria findById(Integer id) {
		Optional<Categoria> categoria = categoriaRepository.findById(id);
		if(categoria.isEmpty()) 
			throw new ServiceObjectNotFoundException("Objeto não foi encontrado");
			
		return categoria.get();
	}
	
	public Page<Categoria> findAll(Integer page, Integer linesPerPage, String orderBy, String direction) {		
		PageRequest pageRequest = PageRequest.of(page,linesPerPage,Direction.valueOf(direction),orderBy);
		return categoriaRepository.findAll(pageRequest);
	}
	
	public Categoria save(CategoriaDTO categoria) {	
		Categoria c = categoriaRepository.save(new Categoria(categoria.getId(), categoria.getNome()));	
		return c;
	}

	public void delete(Integer id) {
		Categoria c = findById(id);
		if(c.getProdutos().size() > 0)
			throw new ServiceConstraintViolationException("A categoria possui Produtos, não é possível excluí-la.");
		categoriaRepository.deleteById(id);		
	}
	
}
