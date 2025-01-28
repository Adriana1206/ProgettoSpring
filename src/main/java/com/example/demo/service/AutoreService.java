package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.DTO.AutoreDTO;
import com.example.demo.model.Autore;
import com.example.demo.repository.AutoreRepository;
import com.example.demo.service.iface.IfaceAutoreService;

@Service
public class AutoreService implements IfaceAutoreService{
	
	@Autowired
	private AutoreRepository autoreRepository;

	
	
	/*
	 * Il metodo saveAutore riceve un AutoreDTO, 
	 * crea un oggetto Autore che viene salvato nel database tramite il repository, 
	 * infine restituisce un AutoreDTO con i dati aggiornati dell'autore.
	 */
	@Override  //aggiungere un autore
	public AutoreDTO saveAutore(AutoreDTO autoreDTO) {
		
		Autore autore = new Autore();
		autore.setId(autoreDTO.getId());
		autore.setNome(autoreDTO.getNome());
		autore.setCognome(autoreDTO.getCognome());
		
		Autore autoreUpdated = autoreRepository.save(autore);
		return convertEntityDTO(autoreUpdated);
	}
	
	//elenco autori
	@Override
	public List<AutoreDTO> getAutori() {
		List<Autore> autori = autoreRepository.findAll();
		List<AutoreDTO> autoriDTO = new ArrayList<AutoreDTO>();
		for (Autore autore : autori) {
			autoriDTO.add(convertEntityDTO(autore));
		}
		return autoriDTO;
	}
	
	//autore tramite id
	@Override
	public AutoreDTO getAutore(long id) {
		Autore autore = autoreRepository.findById(id).orElse(null);
		AutoreDTO autoreDTO = convertEntityDTO(autore);
		
		return autoreDTO;
	}


	@Override
	public void eliminaAutore(long id) {
		autoreRepository.deleteById(id);
		
	}
	
	@Override
	public List<AutoreDTO> cercaAutore(String search) {
		List<Autore> autori = autoreRepository.cercaAutore(search);
		List<AutoreDTO> autoriDTO = new ArrayList<AutoreDTO>();
		
		for (Autore autore : autori) {
			autoriDTO.add(convertEntityDTO(autore));
			
		}
		return autoriDTO;
	}
	
	


	private AutoreDTO convertEntityDTO(Autore autore) {
		
		AutoreDTO autoreDTO = new AutoreDTO();
		autoreDTO.setId(autore.getId());
		autoreDTO.setNome(autore.getNome());
		autoreDTO.setCognome(autore.getCognome());
		
		return autoreDTO;
	}

	

	
	
	

}
