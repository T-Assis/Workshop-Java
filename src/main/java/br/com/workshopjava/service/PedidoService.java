package br.com.workshopjava.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.workshopjava.domain.Pedido;
import br.com.workshopjava.repository.PedidoRepository;
import br.com.workshopjava.service.exception.ObjectNotFoundException;

@Service
public class PedidoService {

	@Autowired
	private PedidoRepository repository;

	public List<Pedido> findAll() {
		return repository.findAll();
	}

	public Pedido findById(Integer id) {
		Optional<Pedido> pedido = repository.findById(id);
		return pedido.orElseThrow( () -> new ObjectNotFoundException("Object not found! Id: " + id));
	}
	
}