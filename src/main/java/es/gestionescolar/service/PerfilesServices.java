package es.gestionescolar.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.gestionescolar.model.Perfil;
import es.gestionescolar.repository.PerfilesRepository;


	

@Service
public class PerfilesServices implements IPerfilesService{

	@Autowired
    private PerfilesRepository perfilesRepo;
	
	@Override
	public void guardar(Perfil perfil) {
		perfilesRepo.save(perfil);
		
	}

}
