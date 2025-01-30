package com.example.demo.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.DTO.LibroDTO;
import com.example.demo.service.LibroService;

@RestController
@RequestMapping("/libri")
public class LibroController {

	
	@Autowired
	private LibroService libroService;

	// Aggiungere un libro
	@PostMapping("")
	public ResponseEntity<LibroDTO> aggiungiLibro(@RequestBody LibroDTO libroDTO) {
		try {
			// Chiama il servizio per aggiungere il libro
			LibroDTO nuovoLibro = libroService.saveLibro(libroDTO);
			return ResponseEntity.status(HttpStatus.CREATED).body(nuovoLibro); // Restituisce una risposta con stato 201

		} catch (IllegalArgumentException e) {
			// Gestisce gli errori di validazione (ad esempio, libro non trovato)
			throw new IllegalArgumentException(e.getMessage());
		} catch (DataAccessException e) {
			// Gestisce gli errori di accesso al database
			throw new IllegalArgumentException(e.getMessage());

		} catch (Exception e) {
			// Gestisce qualsiasi altro errore imprevisto
			throw new IllegalArgumentException(e.getMessage());
		}
	}

	// ResponseEntity = classe Spring che rappresenta una risposta HTTP completa
	// @RequestBody = Spring cerca i dati e li deserializza in un oggetto di tipo
	// Libro
	// ResponseEntity.ok = restituisce una risposta HTTP 200e imposta nel corpo
	// l'oggetto passato

	// metodo per modificare il libro
	@PutMapping("")
	public ResponseEntity<LibroDTO> modificaLibro(@RequestBody LibroDTO libroDTO) {
		try {
			LibroDTO nuovoLibro = libroService.saveLibro(libroDTO);
			return ResponseEntity.ok(nuovoLibro);
		} catch (IllegalArgumentException e) {
			// Gestisce gli errori di validazione (ad esempio, libro non trovato)
			throw new IllegalArgumentException(e.getMessage());
		} catch (DataAccessException e) {
			// Gestisce gli errori di accesso al database
			throw new IllegalArgumentException(e.getMessage());

		} catch (Exception e) {
			// Gestisce qualsiasi altro errore imprevisto
			throw new IllegalArgumentException(e.getMessage());
		}

	}

	// metodo per ottenere la lista di libri
	@GetMapping("")
	public ResponseEntity<List<LibroDTO>> getLibri() {
		try {
			List<LibroDTO> libri = libroService.getLibri();
			return ResponseEntity.ok(libri);
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

	// metodo per ottenere un libro tramite l'id
	@GetMapping("/{id}")
	public LibroDTO getLibro(@PathVariable Long id) {
		try {
			LibroDTO libro = libroService.getLibro(id); // Ottieni l'Optional dal service

			return libro;
		} catch (IllegalArgumentException e) {
			// Gestisce gli errori di validazione (ad esempio, libro non trovato)
			throw new IllegalArgumentException(e.getMessage());
		} catch (DataAccessException e) {
			// Gestisce gli errori di accesso al database
			throw new IllegalArgumentException(e.getMessage());

		} catch (Exception e) {
			// Gestisce qualsiasi altro errore imprevisto
			throw new IllegalArgumentException(e.getMessage());
		}

	}

	// metodo per eliminare un libro
	@DeleteMapping("/{id}")
	public void cancellaLibro(@PathVariable Long id) {
		try {
			libroService.eliminaLibro(id);
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

	@GetMapping("/search/{search}")
	public List<LibroDTO> cercaLibro(@PathVariable String search) {
		try {
			return libroService.cercaLibro(search);
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

	@PostMapping("/libriFromExternalEndpoint")
	public List<LibroDTO> libriFromExternalEndpoint() {
		try {
			return libroService.libriFromExternalEndpoint();
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
