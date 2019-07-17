package br.com.workshopjava.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.workshopjava.domain.Categoria;
import br.com.workshopjava.repository.CategoriaRepository;
import br.com.workshopjava.service.exception.ObjectNotFoundException;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository repository;
	
	public List<Categoria> findAll() {
		return repository.findAll();
	}

	public Categoria findById(Integer id) {
		Optional<Categoria> categoria = repository.findById(id);
		return categoria.orElseThrow( () -> new ObjectNotFoundException("Object not found! Id: " + id));
	}

}
