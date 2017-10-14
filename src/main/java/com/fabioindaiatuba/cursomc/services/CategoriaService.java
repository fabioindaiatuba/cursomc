package com.fabioindaiatuba.cursomc.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fabioindaiatuba.cursomc.domain.Categoria;
import com.fabioindaiatuba.cursomc.repositories.CategoriaRepository;

@Service
public class CategoriaService {
	
	@Autowired
	private CategoriaRepository repo;
	
	public Categoria buscar(Integer id) {
		return repo.findOne(id);
	}
}
