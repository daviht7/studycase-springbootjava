package com.mc.modelagem.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mc.modelagem.domain.Categoria;
import com.mc.modelagem.exceptions.ObjectNotFoundException;
import com.mc.modelagem.repositories.CategoriaRepository;

@Service
public class CategoriaService {
	
	@Autowired
	private CategoriaRepository categoriaRepository;

	
	public Categoria buscar(Integer id) {
		
		Optional<Categoria> categoria = categoriaRepository.findById(id);
		if(categoria.isEmpty()) 
			throw new ObjectNotFoundException("Objeto não foi encontrado");
			
		return categoria.get();
		
	}
	
	public Categoria save(Categoria categoria) {
		
		Categoria c = categoriaRepository.save(categoria);
		
		return c;
		
	}
	
}
