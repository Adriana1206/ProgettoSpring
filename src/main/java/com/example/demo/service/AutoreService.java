package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.annotation.Propagation;

import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientRequestException;

import com.example.demo.DTO.AutoreDTO;
import com.example.demo.DTO.QuoteDTO;
import com.example.demo.DTO.QuoteListDTO;
import com.example.demo.model.Autore;
import com.example.demo.repository.AutoreRepository;
import com.example.demo.service.iface.IfaceAutoreService;




@Service
public class AutoreService implements IfaceAutoreService {

	// WebClient è una classe di Spring utilizzata per chiamate HTTP asincrone
	private final WebClient webClient;

	// configurare l'URL di base (baseUrl) a cui verranno effettuate tutte le
	// richieste.
	public AutoreService(WebClient.Builder webClientBuilder) {
		this.webClient = webClientBuilder.baseUrl("https://dummyjson.com").build();
	}

	@Autowired
	private AutoreRepository autoreRepository;

	/*
	 * Il metodo saveAutore riceve un AutoreDTO, crea un oggetto Autore che viene
	 * salvato nel database tramite il repository, infine restituisce un AutoreDTO
	 * con i dati aggiornati dell'autore.
	 */
	
	@Transactional(propagation = Propagation.REQUIRED) //Rollback automatico
	@Override // aggiungere un autore
	public AutoreDTO saveAutore(AutoreDTO autoreDTO) {
		
		try {
			Autore autore = new Autore();
			autore.setId(autoreDTO.getId());
			autore.setNome(autoreDTO.getNome());
			autore.setCognome(autoreDTO.getCognome());

			Autore autoreUpdated = autoreRepository.saveAndFlush(autore);
			return convertEntityDTO(autoreUpdated);
		}catch (IllegalArgumentException e) {
			// Gestisce gli errori di validazione (ad esempio, libro non trovato)
			throw new IllegalArgumentException(e.getMessage());
		} catch (DataAccessException e) {
			// Gestisce gli errori di accesso al database
			throw new IllegalArgumentException(e.getMessage());

		} catch (Exception e) {
			// Gestisce qualsiasi altro errore imprevisto
			throw new IllegalArgumentException(e.getMessage());
		}

		
	}

	// elenco autori
	@Override
	public List<AutoreDTO> getAutori() {
		try {
			List<Autore> autori = autoreRepository.findAll();
			List<AutoreDTO> autoriDTO = new ArrayList<AutoreDTO>();
			for (Autore autore : autori) {
				autoriDTO.add(convertEntityDTO(autore));
			}
			return autoriDTO;
		}catch (IllegalArgumentException e) {
			// Gestisce gli errori di validazione (ad esempio, libro non trovato)
			throw new IllegalArgumentException(e.getMessage());
		} catch (DataAccessException e) {
			// Gestisce gli errori di accesso al database
			throw new IllegalArgumentException(e.getMessage());

		} catch (Exception e) {
			// Gestisce qualsiasi altro errore imprevisto
			throw new IllegalArgumentException(e.getMessage());
		}
		
	}

	// autore tramite id
	@Override
	public AutoreDTO getAutore(Long id) {
		try {
			Autore autore = autoreRepository.findById(id).orElse(null);
			AutoreDTO autoreDTO = convertEntityDTO(autore);

			return autoreDTO;
		}catch (IllegalArgumentException e) {
			// Gestisce gli errori di validazione (ad esempio, libro non trovato)
			throw new IllegalArgumentException(e.getMessage());
		} catch (DataAccessException e) {
			// Gestisce gli errori di accesso al database
			throw new IllegalArgumentException(e.getMessage());

		} catch (Exception e) {
			// Gestisce qualsiasi altro errore imprevisto
			throw new IllegalArgumentException(e.getMessage());
		}
		
	}

	@Override
	public void eliminaAutore(long id) {
		try {
			autoreRepository.deleteById(id);
		}catch (IllegalArgumentException e) {
			// Gestisce gli errori di validazione (ad esempio, libro non trovato)
			throw new IllegalArgumentException(e.getMessage());
		} catch (DataAccessException e) {
			// Gestisce gli errori di accesso al database
			throw new IllegalArgumentException(e.getMessage());

		} catch (Exception e) {
			// Gestisce qualsiasi altro errore imprevisto
			throw new IllegalArgumentException(e.getMessage());
		}
		

	}

	@Override
	public List<AutoreDTO> cercaAutore(String search) {
		try {
			List<Autore> autori = autoreRepository.cercaAutore(search);
			List<AutoreDTO> autoriDTO = new ArrayList<AutoreDTO>();

			for (Autore autore : autori) {
				autoriDTO.add(convertEntityDTO(autore));

			}
			return autoriDTO;
		}catch (IllegalArgumentException e) {
			// Gestisce gli errori di validazione (ad esempio, libro non trovato)
			throw new IllegalArgumentException(e.getMessage());
		} catch (DataAccessException e) {
			// Gestisce gli errori di accesso al database
			throw new IllegalArgumentException(e.getMessage());

		} catch (Exception e) {
			// Gestisce qualsiasi altro errore imprevisto
			throw new IllegalArgumentException(e.getMessage());
		}
		
	}

	private AutoreDTO convertEntityDTO(Autore autore) {

		AutoreDTO autoreDTO = new AutoreDTO();
		autoreDTO.setId(autore.getId());
		autoreDTO.setNome(autore.getNome());
		autoreDTO.setCognome(autore.getCognome());

		return autoreDTO;
	}

	@Override
	public List<AutoreDTO> autoriFromExternalEndpoint(){
		//lista vuota di autori
		 List<AutoreDTO> autoriSalvati = new ArrayList<>();
		
		//richiamo il metodo getQuoteList che ritorna una lista con 30 citazioni
		QuoteListDTO quoteList = getQuoteList(0, 30);
		
		//controllo che la lista non sia vuota
		if(quoteList != null) {
			//itero nella lista delle citazioni per prendere gli autori
			for (QuoteDTO quote : quoteList.getQuotes()) {
				 List<AutoreDTO> autore = cercaAutore(quote.getAuthor());		 
				 if (autore == null || autore.size() == 0) {
		                // Creo un nuovo AutoreDTO
		                AutoreDTO autoreNuovo = new AutoreDTO();
		                //nome dell'autore preso dalla citazione
		                autoreNuovo.setNome(quote.getAuthor()); 
		                
		                // Salvo l'autore
		                AutoreDTO autoreSalvato = saveAutore(autoreNuovo);
		                
		                // Aggiungo l'autore salvato alla lista
		                autoriSalvati.add(autoreSalvato);
		            } else {
		                // Se l'autore esiste già, lo aggiungo  alla lista
		                autoriSalvati.add(autore.get(0));
		            }
				 
			}
		}
		
		return autoriSalvati;
	}

	
	
	
	// fa una richiesta HTTP GET a un'API per ottenere una lista di citazioni.
		public QuoteListDTO getQuoteList(int skip, int limit) {
			try {
				return webClient.get()
						.uri(uriBuilder -> uriBuilder.path("/quotes")
						.queryParam("skip", skip)
						.queryParam("limit", limit).build())
						.retrieve()
						.bodyToMono(QuoteListDTO.class)
						.block();
			}catch(WebClientRequestException e) {
				throw new IllegalArgumentException(e.getMessage());
			}catch (Exception e) {
		        // Gestisce altri errori imprevisti
				throw new IllegalArgumentException(e.getMessage());
		    }
			
		}
	
	
	

}

















