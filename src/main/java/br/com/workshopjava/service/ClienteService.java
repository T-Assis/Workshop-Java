package br.com.workshopjava.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.workshopjava.domain.Cliente;
import br.com.workshopjava.repository.ClienteRepository;
import br.com.workshopjava.service.exception.ObjectNotFoundException;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository repository;

	public List<Cliente> findAll() {
		return repository.findAll();
	}

	public Cliente findById(Integer id) {
		Optional<Cliente> cliente = repository.findById(id);
		return cliente.orElseThrow( () -> new ObjectNotFoundException("Object not found! Id: " + id));
	}

}
