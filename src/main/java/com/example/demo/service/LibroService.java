package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.DTO.LibroDTO;
import com.example.demo.model.Autore;
import com.example.demo.model.Libro;
import com.example.demo.repository.LibroRepository;
import com.example.demo.service.iface.IfaceLibroService;

@Service
public class LibroService implements IfaceLibroService{

	@Autowired
	private LibroRepository libroRepository;
	
	@Autowired
	private AutoreService autoreService;
	
	//aggiungere un libro
	@Override
	public LibroDTO saveLibro(LibroDTO libroDTO) {
		
		
		//Recupero l'autore utilizzando l'id
    	Autore autore = autoreService.getAutore(libroDTO.getAutoreId()).orElse(null);
    	
    	Libro libro = new Libro();
    	libro.setTitolo(libroDTO.getTitolo());
    	libro.setId(libroDTO.getId());
    	libro.setAnnoPubblicazione(libroDTO.getAnnoPubblicazione());
    	libro.setPrezzo(libroDTO.getPrezzo());
    	libro.setAutore(autore);
    	
    	Libro libroUpdated = libroRepository.save(libro);
		return convertEntityDTO(libroUpdated);
	}
	
	//elenco libri
	@Override
	public List<LibroDTO> getLibri(){
		List<Libro> libri = libroRepository.findAll();  //lista di entity
		List<LibroDTO> libriDTO = new ArrayList<LibroDTO>(); //lista vuota di libriDTO
		for (Libro libro : libri) {  //per ogni elemento della lista libri, lo converto in un DTO
			libriDTO.add(convertEntityDTO(libro));
		}
		return libriDTO;
	}
	
	
	//libro by id
	//findById restituisce un Optional
	@Override
	public LibroDTO getLibro(long id) {
		Libro libro = libroRepository.findById(id).orElse(null);
		LibroDTO libroDTO = convertEntityDTO(libro); 
		
	    return libroDTO;
	}
	
	//cancellare un libro
	@Override
	public void eliminaLibro(long id) {
		libroRepository.deleteById(id);
	}

	@Override
	public List<LibroDTO> cercaLibro(String search) {
		List<Libro> libri = libroRepository.getByTitolOrAuthor(search);
		List<LibroDTO> libriDTO = new ArrayList<LibroDTO>();
		
		for(Libro libro : libri) {
			libriDTO.add(convertEntityDTO(libro));
		}
		
		return libriDTO;
	}
	
	
	private LibroDTO convertEntityDTO(Libro libro) {
        
    	LibroDTO libroDTO = new LibroDTO();
    	libroDTO.setTitolo(libro.getTitolo());
    	libroDTO.setId(libro.getId());
    	libroDTO.setAnnoPubblicazione(libro.getAnnoPubblicazione());
    	libroDTO.setPrezzo(libro.getPrezzo());
    	libroDTO.setAutore(libro.getAutore().getId());
    	
    	return libroDTO;
	}

} 
