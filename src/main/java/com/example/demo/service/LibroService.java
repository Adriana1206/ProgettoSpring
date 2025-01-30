package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientRequestException;

import com.example.demo.DTO.AutoreDTO;
import com.example.demo.DTO.LibroDTO;
import com.example.demo.DTO.QuoteDTO;
import com.example.demo.DTO.QuoteListDTO;
import com.example.demo.model.Autore;
import com.example.demo.model.Libro;
import com.example.demo.repository.LibroRepository;
import com.example.demo.service.iface.IfaceLibroService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class LibroService implements IfaceLibroService {

	private static final Logger logger = LoggerFactory.getLogger(LibroService.class);

	private final WebClient webClient;

	@Autowired
	private LibroRepository libroRepository;

	@Autowired
	private AutoreService autoreService;

	public LibroService(WebClient.Builder webClientBuilder) {
		this.webClient = webClientBuilder.baseUrl("https://dummyjson.com").build();
	}
	
	
	/*
	 * public LibroDTO saveLibro(LibroDTO libroDTO) {

		try {

			// Recupero l'autore utilizzando l'id
			Autore autore = autoreService.getAutore(libroDTO.getAutoreId()).orElse(null);
			
			//indById di per sé restituisce un Optional<Autore>, 
			//ma il orElse(null) converte il risultato in un oggetto Autore o null
			//Se autore è null (perché l'Optional non contiene un valore), stai cercando di 
			//impostare un valore null nel campo autore di un oggetto Libro.


			if (autore == null) { throw new IllegalArgumentException("Autore con ID " +libroDTO.getAutoreId() + " non trovato"); }
			Libro libro = new Libro();
			libro.setTitolo(libroDTO.getTitolo());
			libro.setId(libroDTO.getId());
			libro.setAnnoPubblicazione(libroDTO.getAnnoPubblicazione());
			libro.setPrezzo(libroDTO.getPrezzo());
			libro.setAutore(autore);

			Libro libroUpdated = libroRepository.saveAndFlush(libro);// salva il libro nel DB
			return convertEntityDTO(libroUpdated);
		} catch (DataAccessException e) {
			// Gestione degli errori relativi all'accesso al database
			throw new IllegalArgumentException(e.getMessage());
		} catch (IllegalArgumentException e) {
			throw new IllegalArgumentException(e.getMessage());
		} catch (Exception e) {
			// Gestione di altri errori imprevisti
			throw new IllegalArgumentException(e.getMessage());
		}

	}
	 */

	@Transactional(propagation = Propagation.REQUIRED) // Rollback automatico
	@Override
	public LibroDTO saveLibro(LibroDTO libroDTO) {

	    try {
	        // Recupero l'autore utilizzando l'id
	        AutoreDTO autoreDTO = autoreService.getAutore(libroDTO.getAutoreId());

	        // Verifica se l'autore è stato trovato
	        if (autoreDTO == null) {
	            throw new IllegalArgumentException("Autore con ID " + libroDTO.getAutoreId() + " non trovato");
	        }

	        // Converte l'AutoreDTO in un oggetto Autore
	        Autore autore = convertDTOToEntity(autoreDTO);

	        // Crea il libro
	        Libro libro = new Libro();
	        libro.setTitolo(libroDTO.getTitolo());
	        libro.setId(libroDTO.getId());
	        libro.setAnnoPubblicazione(libroDTO.getAnnoPubblicazione());
	        libro.setPrezzo(libroDTO.getPrezzo());
	        libro.setAutore(autore); // Imposta l'autore nel libro

	        // Salva il libro nel DB
	        //flush garantisce che le modifiche vengano applicate immediatamente nel database
	        Libro libroUpdated = libroRepository.saveAndFlush(libro);
	        
	        // Converte l'entità Libro in DTO per restituirlo al client
	        return convertEntityDTO(libroUpdated);

	    } catch (DataAccessException e) {
	        // Gestione degli errori relativi all'accesso al database
	        throw new IllegalArgumentException("Errore nell'accesso al database: " + e.getMessage());
	    } catch (IllegalArgumentException e) {
	        // Gestione degli errori nei dati forniti
	        throw new IllegalArgumentException("Errore nei dati forniti: " + e.getMessage());
	    } catch (Exception e) {
	        // Gestione di altri errori imprevisti
	        throw new IllegalArgumentException("Errore imprevisto: " + e.getMessage());
	    }
	}

	// Metodo di conversione da AutoreDTO a Autore 
	private Autore convertDTOToEntity(AutoreDTO autoreDTO) {
	    Autore autore = new Autore();
	    autore.setId(autoreDTO.getId());
	    autore.setNome(autoreDTO.getNome()); 
	    autore.setCognome(autoreDTO.getCognome()); 
	    
	    return autore;
	}


	@Override
	public List<LibroDTO> getLibri() {
		List<LibroDTO> libriDTO = new ArrayList<>(); // lista vuota di libriDTO

		try {
			// Recupera tutti i libri dal DB
			List<Libro> libri = libroRepository.findAll();

			for (Libro libro : libri) { // per ogni elemento della lista libri, lo converto in un DTO
				libriDTO.add(convertEntityDTO(libro));
			}
		} catch (DataAccessException e) {
			throw new IllegalArgumentException(e.getMessage());

		} catch (Exception e) {
			throw new IllegalArgumentException(e.getMessage());
		}

		return libriDTO; // Restituisce la lista di DTO
	}

	// libro by id
	@Override
	public LibroDTO getLibro(long id) {
		LibroDTO libroDTO = null;
		try {
			Libro libro = libroRepository.findById(id).orElse(null);// recupera un libro dal DB usando il suo ID.
			if (libro != null) {
				libroDTO = convertEntityDTO(libro);
			} else {
				throw new IllegalArgumentException("Libro con ID " + id + " non trovato.");
			}
		} catch (DataAccessException e) {
			throw new IllegalArgumentException(e.getMessage());
		} catch (Exception e) {
			throw new IllegalArgumentException(e.getMessage());
		}

		return libroDTO;
	}

	// cancellare un libro dal DB
	@Override
	public void eliminaLibro(long id) {
		try {
			// Controllo se esiste un libro con questo ID
			if (libroRepository.existsById(id)) {
				libroRepository.deleteById(id);
			} else {
				// Lancia un'eccezione con un messaggio personalizzato
				throw new IllegalArgumentException("Libro con ID " + id + " non trovato.");
			}
		} catch (DataAccessException e) {
			// Gestione dell'errore in caso di problemi con l'accesso al database
			throw new IllegalArgumentException("Errore nell'accesso al database: " + e.getMessage());
		} catch (Exception e) {
			// Gestione di altre eccezioni generiche
			throw new IllegalArgumentException("Errore imprevisto: " + e.getMessage());
		}
	}

	@Override
	public List<LibroDTO> cercaLibro(String search) {
		List<LibroDTO> libriDTO = new ArrayList<>(); // Inizializza la lista di DTO

		try {
			// query per cercare libri nel DB
			List<Libro> libri = libroRepository.getByTitolOrAuthor(search);

			if (libri.isEmpty()) {
				throw new IllegalArgumentException("Nessun libro trovato per la ricerca: " + search);
			}

			// Converte ogni libro in un LibroDTO
			for (Libro libro : libri) {
				libriDTO.add(convertEntityDTO(libro));
			}

		} catch (DataAccessException e) {
			throw new IllegalArgumentException(e.getMessage());
		} catch (Exception e) {
			throw new IllegalArgumentException(e.getMessage());
		}

		return libriDTO;
	}

	private LibroDTO convertEntityDTO(Libro libro) {

		LibroDTO libroDTO = new LibroDTO();
		libroDTO.setTitolo(libro.getTitolo());
		libroDTO.setId(libro.getId());
		libroDTO.setAnnoPubblicazione(libro.getAnnoPubblicazione());
		libroDTO.setPrezzo(libro.getPrezzo());
		libroDTO.setAutore(libro.getAutore().getId());

		return libroDTO;
	}

	// WebClient per ottenere i libri dall'endpoint esterno
	@Transactional(propagation = Propagation.REQUIRED) // Rollback automatico
	@Override
	public List<LibroDTO> libriFromExternalEndpoint() {
		List<LibroDTO> libri = new ArrayList<LibroDTO>();

		QuoteListDTO quoteList = getQuoteList(0, 30);// ottengo una lista delle prime 30 citazioni

		if (quoteList != null) {
			// itera sulla lista delle citazioni
			// prima di salvare la citazione verifico se l'autore è già presente nella
			// tabella
			for (QuoteDTO quote : quoteList.getQuotes()) {
				// creo una lista con gli autori
				// ritorna una lista di autori che corrispondono al nome dell'autore della
				// citazione.
				List<AutoreDTO> autore = autoreService.cercaAutore(quote.getAuthor());
				Long id = null;
				if (autore == null || autore.size() == 0) {
					// se non trova nessun autore lo crea e salva l'id
					AutoreDTO autoreNuovo = saveAutore(quote.getAuthor());
					id = autoreNuovo.getId();
					logger.info("Autore creato con ID: {}", id);
				} else {
					// estrae l'id del primo autore trovato
					id = autore.get(0).getId();
					logger.info("Autore trovato con ID: {}", id);
				}

				// dopo aver gestito l'autore salvo la citazione nella tabella libro
				LibroDTO libro = new LibroDTO();
				libro.setAutore(id);
				libro.setTitolo(quote.getQuote());
				LibroDTO libroNuovo = saveLibro(libro);

				libri.add(libroNuovo);

			}
		}
		return libri;
	}

	// metodo per salvare l'autore se non esiste
	private AutoreDTO saveAutore(String autore) {
		AutoreDTO autoreDTO = new AutoreDTO();
		autoreDTO.setNome(autore);
		return autoreService.saveAutore(autoreDTO);

	}

	// fa una richiesta HTTP GET a un'API per ottenere una lista di citazioni.
	public QuoteListDTO getQuoteList(int skip, int limit) {
		try {
			return webClient.get().uri(uriBuilder -> uriBuilder.path("/quotes").queryParam("skip", skip)
					.queryParam("limit", limit).build()).retrieve().bodyToMono(QuoteListDTO.class).block();
		} catch (WebClientRequestException e) {
			throw new IllegalArgumentException(e.getMessage());
		} catch (Exception e) {
			// Gestisce altri errori imprevisti
			throw new IllegalArgumentException(e.getMessage());
		}

	}

}
