package com.example.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Autore {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; 

	private String nome;
	private String cognome;
	
	// Costruttore senza argomenti (richiesto da JPA)
    public Autore() {
    }
    
    public Autore(String nome, String cognome) {
    	this.nome = nome;
    	this.cognome = cognome;
    }
	 
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCognome() {
		return cognome;
	}
	public void setCognome(String cognome) {
		this.cognome = cognome;
	}
	
	
	
}
