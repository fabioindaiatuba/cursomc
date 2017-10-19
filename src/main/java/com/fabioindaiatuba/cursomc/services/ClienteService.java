package com.fabioindaiatuba.cursomc.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fabioindaiatuba.cursomc.domain.Cliente;
import com.fabioindaiatuba.cursomc.repositories.ClienteRepository;
import com.fabioindaiatuba.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository repo;
	
	public Cliente find(Integer id) {
		Cliente obj = repo.findOne(id);
		if (obj == null) {
			throw new ObjectNotFoundException("Objeto não encontrado! Id: "+id
					+ ", Tipo: "+ Cliente.class.getName());
		}
		return obj;
	}
}
