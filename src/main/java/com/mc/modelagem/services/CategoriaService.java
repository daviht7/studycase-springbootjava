package com.mc.modelagem.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mc.modelagem.domain.Categoria;
import com.mc.modelagem.exceptions.ServiceConstraintViolationException;
import com.mc.modelagem.exceptions.ServiceObjectNotFoundException;
import com.mc.modelagem.repositories.CategoriaRepository;

@Service
public class CategoriaService {
	
	@Autowired
	private CategoriaRepository categoriaRepository;

	
	public Categoria find(Integer id) {
		
		Optional<Categoria> categoria = categoriaRepository.findById(id);
		if(categoria.isEmpty()) 
			throw new ServiceObjectNotFoundException("Objeto não foi encontrado");
			
		return categoria.get();
		
	}
	
	public List<Categoria> findAll() {		
		List<Categoria> categorias = categoriaRepository.findAll();
		return categorias;	
	}
	
	public Categoria save(Categoria categoria) {	
		Categoria c = categoriaRepository.save(categoria);	
		return c;
	}
	public Categoria update(Categoria categoria) {	
		Categoria c = categoriaRepository.save(categoria);	
		return c;
	}
	
	public void delete(Integer id) {
		
		Categoria c = find(id);
		
		if(c.getProdutos().size() > 0)
			throw new ServiceConstraintViolationException("A categoria possui Produtos, não é possível excluí-la.");
		
		categoriaRepository.deleteById(id);	
		
	}
	
	
}
