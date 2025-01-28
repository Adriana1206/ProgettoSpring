package com.example.demo.model;

import java.math.BigDecimal;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Libro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; 

    private String titolo; 
    
    @ManyToOne  //gni libro ha un autore, e molti libri possono avere lo stesso autore
    private Autore autore;
    
    private Integer annoPubblicazione; 
    private BigDecimal prezzo; 

    // Costruttore senza argomenti (richiesto da JPA)
    public Libro() {
    }

    public Libro(String titolo,Autore autore, Integer annoPubblicazione, BigDecimal prezzo) {
        this.titolo = titolo;
        this.autore = autore;
        this.annoPubblicazione = annoPubblicazione;
        this.prezzo = prezzo;
    }

    // Getter e Setter 
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitolo() {
        return titolo;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }
    
    public Autore getAutore() {
    	return autore;
    }
    
    public void setAutore(Autore autore) {
    	this.autore = autore;
    }


    public Integer getAnnoPubblicazione() {
        return annoPubblicazione;
    }

    public void setAnnoPubblicazione(Integer annoPubblicazione) {
        this.annoPubblicazione = annoPubblicazione;
    }

    public BigDecimal getPrezzo() {
        return prezzo;
    }

    public void setPrezzo(BigDecimal prezzo) {
        this.prezzo = prezzo;
    }
}

