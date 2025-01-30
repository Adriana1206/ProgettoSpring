package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
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
		try {
			AutoreDTO nuovoAutore = autoreService.saveAutore(autoreDTO);
			return ResponseEntity.ok(nuovoAutore);
		} catch (IllegalArgumentException e) {
			// Gestisce gli errori di validazione (ad esempio, autore non trovato)
			throw new IllegalArgumentException(e.getMessage());
		} catch (DataAccessException e) {
			// Gestisce gli errori di accesso al database
			throw new IllegalArgumentException(e.getMessage());

		} catch (Exception e) {
			// Gestisce qualsiasi altro errore imprevisto
			throw new IllegalArgumentException(e.getMessage());
		}
		
	}
	
	//modificare un libro
	@PutMapping("")
	public ResponseEntity<AutoreDTO> modificaAutore(@RequestBody AutoreDTO autoreDTO){
		try {
			AutoreDTO nuovoAutore = autoreService.saveAutore(autoreDTO);
			return ResponseEntity.ok(nuovoAutore);
		} catch (IllegalArgumentException e) {
			// Gestisce gli errori di validazione 
			throw new IllegalArgumentException(e.getMessage());
		} catch (DataAccessException e) {
			// Gestisce gli errori di accesso al database
			throw new IllegalArgumentException(e.getMessage());

		} catch (Exception e) {
			// Gestisce qualsiasi altro errore imprevisto
			throw new IllegalArgumentException(e.getMessage());
		}
		
		
	}
	
	//lista autori
	@GetMapping("")
	public ResponseEntity<List<AutoreDTO>> getAutori(){
		try {
			List<AutoreDTO> autoriDTO = autoreService.getAutori();
			return ResponseEntity.ok(autoriDTO);
		} catch (IllegalArgumentException e) {
			// Gestisce gli errori di validazione 
			throw new IllegalArgumentException(e.getMessage());
		} catch (DataAccessException e) {
			// Gestisce gli errori di accesso al database
			throw new IllegalArgumentException(e.getMessage());

		} catch (Exception e) {
			// Gestisce qualsiasi altro errore imprevisto
			throw new IllegalArgumentException(e.getMessage());
		}
		
	}
	
	//autore tramite id
	@GetMapping("/{id}")
	public AutoreDTO getAutore(@PathVariable Long id){
		try {
			AutoreDTO autore = autoreService.getAutore(id);	
			return autore;
		}catch (IllegalArgumentException e) {
			// Gestisce gli errori di validazione 
			throw new IllegalArgumentException(e.getMessage());
		} catch (DataAccessException e) {
			// Gestisce gli errori di accesso al database
			throw new IllegalArgumentException(e.getMessage());

		} catch (Exception e) {
			// Gestisce qualsiasi altro errore imprevisto
			throw new IllegalArgumentException(e.getMessage());
		}
		
	}
	
	//cancella autore
	@DeleteMapping("/{id}")
	public void cancellaAutore(@PathVariable Long id) {
		try {
			autoreService.eliminaAutore(id);
		}catch (IllegalArgumentException e) {
			// Gestisce gli errori di validazione 
			throw new IllegalArgumentException(e.getMessage());
		} catch (DataAccessException e) {
			// Gestisce gli errori di accesso al database
			throw new IllegalArgumentException(e.getMessage());

		} catch (Exception e) {
			// Gestisce qualsiasi altro errore imprevisto
			throw new IllegalArgumentException(e.getMessage());
		}
		
	}
	
	@GetMapping("/search/{search}")
	public List<AutoreDTO> cercaAutore(@PathVariable String search){
		try {
			return autoreService.cercaAutore(search);
		}catch (IllegalArgumentException e) {
			// Gestisce gli errori di validazione 
			throw new IllegalArgumentException(e.getMessage());
		} catch (DataAccessException e) {
			// Gestisce gli errori di accesso al database
			throw new IllegalArgumentException(e.getMessage());

		} catch (Exception e) {
			// Gestisce qualsiasi altro errore imprevisto
			throw new IllegalArgumentException(e.getMessage());
		}
		
		
	}
	
	@PostMapping("/autoriFromExternalEndpoint")
	public List<AutoreDTO> autoriFromExternalEndpoint() throws Exception{
		try {
			return autoreService.autoriFromExternalEndpoint();
		}catch (IllegalArgumentException e) {
			// Gestisce gli errori di validazione 
			throw new IllegalArgumentException(e.getMessage());
		} catch (DataAccessException e) {
			// Gestisce gli errori di accesso al database
			throw new IllegalArgumentException(e.getMessage());

		} catch (Exception e) {
			// Gestisce qualsiasi altro errore imprevisto
			throw new IllegalArgumentException(e.getMessage());
		}
		
	}
}
















