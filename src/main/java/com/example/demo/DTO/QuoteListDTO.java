package com.example.demo.DTO;
import java.util.List;

public class QuoteListDTO {

	private List<QuoteDTO> quotes;
	private int total;
	private int skip;
	private int limit;
	
	public QuoteListDTO(List<QuoteDTO> quotes, int total, int skip, int limit) {
		super();
		this.quotes = quotes;
		this.total = total;
		this.skip = skip;
		this.limit = limit;
	}
	public QuoteListDTO() {
		super();
	}
	
	//lista di citazioni
	public List<QuoteDTO> getQuotes() {
		return quotes;
	}
	
	
	public void setQuotes(List<QuoteDTO> quotes) {
		this.quotes = quotes;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public int getSkip() {
		return skip;
	}
	public void setSkip(int skip) {
		this.skip = skip;
	}
	public int getLimit() {
		return limit;
	}
	public void setLimit(int limit) {
		this.limit = limit;
	}
	
	
	
}
