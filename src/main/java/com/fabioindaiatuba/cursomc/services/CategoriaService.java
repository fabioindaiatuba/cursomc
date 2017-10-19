package com.fabioindaiatuba.cursomc.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.fabioindaiatuba.cursomc.domain.Categoria;
import com.fabioindaiatuba.cursomc.repositories.CategoriaRepository;
import com.fabioindaiatuba.cursomc.services.exceptions.DataIntegrityException;
import com.fabioindaiatuba.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class CategoriaService {
	
	@Autowired
	private CategoriaRepository repo;
	
	public Categoria find(Integer id) {
		Categoria obj = repo.findOne(id);
		if (obj == null) {
			throw new ObjectNotFoundException("Objeto não encontrado! Id: "+id
					+ ", Tipo: "+ Categoria.class.getName());
		}
		return obj;
	}
	
	public Categoria insert(Categoria obj) {
		obj.setId(null);
		return repo.save(obj);
	}

	public Categoria update(Categoria obj) {
		find(obj.getId());
		return repo.save(obj);
	}

	public void delete(Integer id) {
		find(id);
		try {
			repo.delete(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possivel excluir categoria com produtos!");
		}
	}

	public List<Categoria> findAll() {
		return repo.findAll();
	}
}
