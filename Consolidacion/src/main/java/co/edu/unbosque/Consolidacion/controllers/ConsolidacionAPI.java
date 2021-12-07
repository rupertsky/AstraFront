package co.edu.unbosque.Consolidacion.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import co.edu.unbosque.Consolidacion.models.Consolidacion;
import co.edu.unbosque.Consolidacion.repositories.ConsolidacionDAO;

@RestController
@CrossOrigin(origins = "*", methods = {RequestMethod.POST, RequestMethod.GET,
		     RequestMethod.PUT, RequestMethod.DELETE})
@RequestMapping("/api/consolidacion")
public class ConsolidacionAPI {
	
	@Autowired
	private ConsolidacionDAO consolidaciondao;
	
	@PostMapping("/guardar")
	public Consolidacion guardar(@Validated @RequestBody Consolidacion consolidacion) {
		return consolidaciondao.insert(consolidacion);
	}
	
	@GetMapping("/listar")
	public List<Consolidacion> listar(){
		return consolidaciondao.findAll();
	}

}
