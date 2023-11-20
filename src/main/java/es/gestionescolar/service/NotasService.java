package es.gestionescolar.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import es.gestionescolar.model.Alumno;
import es.gestionescolar.model.Nota;
import es.gestionescolar.repository.NotasRepository;

@Service
public class NotasService implements INotasService{
	
	@Autowired
	private NotasRepository notasRepo;

	@Override
	public void guardar(Nota nota) {
		notasRepo.save(nota);
		
	}

	@Override
	public Nota buscarPorId(Integer idNota) {
		Optional<Nota> optional = notasRepo.findById(idNota);
        if (optional.isPresent()) {
            return optional.get();
        }
        return null;
	}

	@Override
	public void eliminar(Integer idNota) {
		notasRepo.deleteById(idNota);
		
	}

	@Override
	public Page<Nota> buscarTodas(Pageable page) {
		return notasRepo.findAll(page);
	}

	@Override
	public Page<Nota> buscarNotasPorAlumno(Long idAlumno, Pageable page) {
		return notasRepo.findByAlumnoId(idAlumno, page);
	}

	@Override
	public void borrarRegistrosNotas() {
		notasRepo.borrarRegistrosNotas();
		
	}

	

}
