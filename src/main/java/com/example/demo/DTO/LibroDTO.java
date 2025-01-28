package com.example.demo.DTO;

import java.math.BigDecimal;

public class LibroDTO {

	    private Long id; 
	    private String titolo; 
	    private Long autoreId;
	    private Integer annoPubblicazione; 
	    private BigDecimal prezzo; 
	    
	    public LibroDTO() {
	    	
	    }
	    
	    
	    public LibroDTO(Long id, String titolo, Long autoreId, Integer annoPubblicazione, BigDecimal prezzo) {
	    	this.id = id;
	    	this.titolo = titolo;
	    	this.autoreId = autoreId;
	    	this.annoPubblicazione = annoPubblicazione;
	    	this.prezzo = prezzo; 	    	
	    }


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


		public Long getAutoreId() {
			return autoreId;
		}


		public void setAutore(Long autoreId) {
			this.autoreId = autoreId;
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
