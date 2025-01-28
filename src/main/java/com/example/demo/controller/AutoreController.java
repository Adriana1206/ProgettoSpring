package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.DTO.AutoreDTO;
import com.example.demo.service.AutoreService;



@RestController
@RequestMapping("/autori")
public class AutoreController {
	
	@Autowired
	private AutoreService autoreService;
	
	
	//riceve il contenuto JSON e lo converte automaticamente in un oggetto Java AutoreDTO 
	@PostMapping("") //aggiungere un autore
	public ResponseEntity<AutoreDTO> aggiungiAutore(@RequestBody AutoreDTO autoreDTO){
		AutoreDTO nuovoAutore = autoreService.saveAutore(autoreDTO);
		return ResponseEntity.ok(nuovoAutore);
	}
	
	//modificare un libro
	@PutMapping("")
	public ResponseEntity<AutoreDTO> modificaAutore(@RequestBody AutoreDTO autoreDTO){
		AutoreDTO nuovoAutore = autoreService.saveAutore(autoreDTO);
		return ResponseEntity.ok(nuovoAutore);
	}
	
	//lista autori
	@GetMapping("")
	public ResponseEntity<List<AutoreDTO>> getAutori(){
		List<AutoreDTO> autoriDTO = autoreService.getAutori();
		return ResponseEntity.ok(autoriDTO);
	}
	
	//autore tramite id
	@GetMapping("/{id}")
	public AutoreDTO getAutore(@PathVariable Long id){
		AutoreDTO autore = autoreService.getAutore(id);
		
		return autore;
	}
	
	//cancella autore
	public void cancellaAutore(@PathVariable Long id) {
		autoreService.eliminaAutore(id);
	}
	
	@GetMapping("/search/{search}")
	public List<AutoreDTO> cercaAutore(@PathVariable String search){
		return autoreService.cercaAutore(search);
	}
	
	
}
















