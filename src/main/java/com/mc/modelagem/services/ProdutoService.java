package com.mc.modelagem.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mc.modelagem.domain.Produto;
import com.mc.modelagem.repositories.ProdutoRepository;

@Service
public class ProdutoService {
	
	@Autowired
	private ProdutoRepository produtoRepository;

	
	public Produto buscar(Integer id) {
		
		Optional<Produto> produto = produtoRepository.findById(id);
		
		return produto.orElse(null);
		
	}
	
	public Produto save(Produto produto) {
		
		Produto c = produtoRepository.save(produto);
		
		return c;
		
	}
	
}
