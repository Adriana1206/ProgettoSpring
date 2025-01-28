package com.example.demo.DTO;

import com.example.demo.model.Autore;

public class AutoreDTO {
	
	private String nome;
	private String cognome;
	
	
	public AutoreDTO() {
		
	}
	
	public AutoreDTO(Long id, String nome, String cognome){
		this.id = id;
		this.nome = nome;
		this.cognome = cognome;
	}

	private Long id; 
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

	public Autore orElse(Object object) {
		// TODO Auto-generated method stub
		return null;
	}


	
}
