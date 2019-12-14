package com.mc.modelagem.resources;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mc.modelagem.domain.Categoria;

@RestController
@RequestMapping(value="/categorias")
public class CategoriaResource {
	
	@RequestMapping(method = RequestMethod.GET)
	public List<Categoria> listar() {
		
		Categoria c  = new Categoria();
		c.setId(10);
		c.setNome("davi");
		Categoria c1  = new Categoria();
		c1.setId(12);
		c1.setNome("kelcya");
		List<Categoria> lista = new ArrayList<Categoria>();
		lista.add(c);
		lista.add(c1);
		return lista;
		
	}

}
