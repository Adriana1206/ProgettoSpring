package com.example.demo.repository.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Libro;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;


@Repository
public class LibroRepositoryImpl{

	@Autowired
	private EntityManager entityManager;
	
	public List<Libro> getByTitolOrAuthor(String search){
		
		
		String searchPattern = "%" + search + "%";
		
		
		//query JPA
		String queryStr = "SELECT l FROM Libro l WHERE l.titolo LIKE :search";
		
		
		// Esegui la query utilizzando il parametro di ricerca
		//la query restituisce oggetti di tipo libro
        TypedQuery<Libro> query = entityManager.createQuery(queryStr, Libro.class); //creo una query utilizzando la stringa queryStr
        //imposta il valore del parametro :search
        query.setParameter("search", searchPattern);

        // Restituisci i risultati
        return query.getResultList();
		
	}
	
}


