package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.model.Autore;

public interface AutoreRepository extends JpaRepository<Autore, Long>{
	
	//metodo per cercare un autore tramite una stringa
	List<Autore> cercaAutore(String search);
	
	Optional<Autore> findById(Long id);

}
