package com.example.demo.repository.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Autore;


import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

@Repository
public class AutoreRepositoryImpl {

	@Autowired
	private EntityManager entityManager;
	
	public List<Autore> cercaAutore(String search){
		
		String searchPattern = "%" + search + "%";
		
		//query JPA
		String queryStr = "SELECT l FROM Autore l WHERE l.nome LIKE :search OR l.cognome LIKE :search";
		
		// Esegue la query utilizzando il parametro di ricerca
		//la query restituisce oggetti di tipo libro
		TypedQuery<Autore> query = entityManager.createQuery(queryStr, Autore.class); //creo una query utilizzando la stringa queryStr
		//imposta il valore del parametro :search
		query.setParameter("search", searchPattern);

		// Restituisci i risultati
		return query.getResultList();
	}
	
	
	
}
