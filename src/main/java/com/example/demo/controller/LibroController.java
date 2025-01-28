package com.example.demo.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
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

    	LibroDTO nuovoLibro = libroService.saveLibro(libroDTO);
        return ResponseEntity.ok(nuovoLibro);
        
    }
	
    //ResponseEntity = classe Spring che rappresenta una risposta HTTP completa
    //@RequestBody = Spring cerca i dati e li deserializza in un oggetto di tipo Libro
    //ResponseEntity.ok = restituisce una risposta HTTP 200e imposta nel corpo l'oggetto passato
    
    
    //metodo per modificare il libro
    @PutMapping("")
    public ResponseEntity<LibroDTO> modificaLibro(@RequestBody LibroDTO libroDTO) {
        LibroDTO nuovoLibro = libroService.saveLibro(libroDTO);
        return ResponseEntity.ok(nuovoLibro);
    }
    
    
    //metodo per ottenere la lista di libri
    @GetMapping("")
    public ResponseEntity<List<LibroDTO>> getLibri(){
    	List<LibroDTO> libri = libroService.getLibri();
    	return ResponseEntity.ok(libri);
    }
    
    
    //metodo per ottenere un libro tramite l'id
    @GetMapping("/{id}")
    public LibroDTO getLibro(@PathVariable Long id) {
        LibroDTO libro = libroService.getLibro(id);  // Ottieni l'Optional dal service

        return libro;
               
    }

    
    //metodo per eliminare un libro
    @DeleteMapping("/{id}")
    public void cancellaLibro(@PathVariable Long id){
    	libroService.eliminaLibro(id);
    }
    
    
    @GetMapping("/search/{search}")
    public List<LibroDTO> cercaLibro(@PathVariable String search) {
    	return libroService.cercaLibro(search);
    }
    
}














