package br.com.workshopjava.resource;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.workshopjava.domain.Categoria;

@RestController
@RequestMapping(value = "/categorias")
public class CategoriaResource {
	
	@GetMapping
	public List<Categoria> findAll() {
		List<Categoria> list = new ArrayList<>();
		list.add(new Categoria(1, "Informática"));
		list.add(new Categoria(2, "Escritório"));
		return list;
	}

}
