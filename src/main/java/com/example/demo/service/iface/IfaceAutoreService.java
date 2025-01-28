package com.example.demo.service.iface;

import java.util.List;
import com.example.demo.DTO.AutoreDTO;

public interface IfaceAutoreService {
	
	public AutoreDTO saveAutore(AutoreDTO autoreDTO);
	
	public List<AutoreDTO> getAutori();
	
	public AutoreDTO getAutore(long id);
	
	public void eliminaAutore(long id);
	
	public List<AutoreDTO> cercaAutore(String search);

	
}
