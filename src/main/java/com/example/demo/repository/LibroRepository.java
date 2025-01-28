package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Libro;

public interface LibroRepository extends JpaRepository<Libro, Long>{
	
	//metodo per cercare i libri per titolo o autore
	List<Libro> getByTitolOrAuthor(String search);

}
