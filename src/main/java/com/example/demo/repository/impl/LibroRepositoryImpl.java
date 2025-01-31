package com.example.demo.repository.impl;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.demo.DTO.LibroDTO;
import com.example.demo.model.Autore;
import com.example.demo.model.Libro;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Root;


import jakarta.persistence.criteria.Predicate;


@Repository
public class LibroRepositoryImpl{

	@Autowired
	private EntityManager entityManager;
	
	
	
	
	public List<Libro> getByTitolOrAuthor(String search){
		
		
		String searchPattern = "%" + search + "%";

	    // Ottieni l'entity manager
	    CriteriaBuilder cb = entityManager.getCriteriaBuilder();
	    
	    // Crea una query di tipo Libro
	    CriteriaQuery<Libro> query = cb.createQuery(Libro.class);
	    
	    // Definisci la root per la query (la tabella principale)
	    Root<Libro> libroRoot = query.from(Libro.class);
	    
	    // Crea la condizione per la ricerca sul titolo
	    Predicate titoloPredicate = cb.like(libroRoot.get("titolo"),searchPattern);
	    
	    // Applica la condizione finale alla query
	    query.where(titoloPredicate);
	    
	    // Esegui la query
	    TypedQuery<Libro> typedQuery = entityManager.createQuery(query);
	    
	    // Restituisci i risultati
	    return typedQuery.getResultList();
		
	}
	
	
	public List<Libro> getBooksByAuthor(String search) {
	    String searchPattern = "%" + search + "%";  // Prepara il pattern di ricerca

	    // Ottieni CriteriaBuilder
	    CriteriaBuilder cb = entityManager.getCriteriaBuilder();
	    
	    // Crea la query per ottenere LibroDTO
	    CriteriaQuery<Libro> query = cb.createQuery(Libro.class);
	    Root<Libro> libroRoot = query.from(Libro.class);
	    Join<Libro, Autore> autoreJoin = libroRoot.join("autore");

	    // Predicato per cercare l'autore
	    Predicate autorePredicate = cb.like(autoreJoin.get("nome"), searchPattern);
	    
	    // Seleziona i campi necessari per il DTO
	    query.select(libroRoot);

	    // Applica la condizione WHERE
	    query.where(autorePredicate);

	    // Esegui la query
	    return entityManager.createQuery(query).getResultList();
	}



	
}


