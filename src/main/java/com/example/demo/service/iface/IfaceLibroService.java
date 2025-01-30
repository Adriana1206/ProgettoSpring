package com.example.demo.service.iface;

import java.util.List;
import com.example.demo.DTO.LibroDTO;


public interface IfaceLibroService {

		public LibroDTO saveLibro(LibroDTO libroDTO);
	
	    
		
		//libro by id
		//findById restituisce un Optional
		public LibroDTO getLibro(long id) ;
		
		//cancellare un libro
		public void eliminaLibro(long id);
		
		//cercare un libro tramite una stringa
		public List<LibroDTO> cercaLibro(String search);
		
		public List<LibroDTO> libriFromExternalEndpoint();



		List<LibroDTO> getLibri();
}
