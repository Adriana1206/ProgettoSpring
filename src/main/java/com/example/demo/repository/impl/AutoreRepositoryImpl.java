package com.example.demo.repository.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Autore;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

@Repository
public class AutoreRepositoryImpl {

    @Autowired
    private EntityManager entityManager;
    
    public List<Autore> cercaAutore(String search) {
    	
    	
    	/*
    	 * Il CriteriaBuilder è uno strumento fornito da JPA che consente 
    	 * di costruire query dinamiche in modo tipizzato (senza scrivere direttamente SQL).
    	 * Si ottiene dal EntityManager, che è il punto di accesso per interagire con il database.
    	 */
        
        String searchPattern = "%" + search + "%";
        
        // Ottieni il CriteriaBuilder dal EntityManager
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        
        // rappresenta una query su una tabella di autori nel database
        CriteriaQuery<Autore> criteriaQuery = criteriaBuilder.createQuery(Autore.class);
        
        // root è l'oggetto che rappresenta la tabella degli autori
        Root<Autore> root = criteriaQuery.from(Autore.class);
        
        // Costruisci le condizioni "LIKE" per nome e cognome
        Predicate nomePredicate = criteriaBuilder.like(root.get("nome"), searchPattern); //Cerca la stringa searchPattern nella colonna nome della tabella Autore.
        Predicate cognomePredicate = criteriaBuilder.like(root.get("cognome"), searchPattern); //Cerca la stringa searchPattern nella colonna cognome della tabella Autore.
        
        // Combina le condizioni con un "OR"
        Predicate finalPredicate = criteriaBuilder.or(nomePredicate, cognomePredicate);
        
        // Costruzione della query finale
        criteriaQuery.select(root).where(finalPredicate);
        
        // Esecuzione della query
        TypedQuery<Autore> query = entityManager.createQuery(criteriaQuery);
        
        // recupera i risultati, cioè una lista di oggetti Autore che corrispondono al criterio di ricerca.
        return query.getResultList();
    }
}
