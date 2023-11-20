package es.gestionescolar.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import es.gestionescolar.model.Profesor;
import es.gestionescolar.repository.AsignaturasRepository;
import es.gestionescolar.repository.ProfesoresRepository;

@Service
public class ProfesoresService implements IProfesoresService{

	@Autowired
	private ProfesoresRepository profesoresRepo;
	
	@Override
	public List<Profesor> buscarTodas() {
		return profesoresRepo.findAll();
	}

	@Override
	public Page<Profesor> buscarTodas(Pageable page) {
		return profesoresRepo.findAll(page);
	}

	@Override
	public void guardar(Profesor profesor) {
		profesoresRepo.save(profesor);
	}

	@Override
	public Profesor buscarPorId(Integer idProfesor) {
		Optional<Profesor> optional = profesoresRepo.findById(idProfesor);
        if (optional.isPresent()) {
            return optional.get();
        }
        return null;
	}

	@Override
	public void eliminar(Integer idProfesor) {
		profesoresRepo.deleteById(idProfesor);
		
	}

	@Override
	public Page<Profesor> buscarProfesoresPorCurso(Integer idCurso, Pageable page) {
		return profesoresRepo.findProfesoresByCursoId(idCurso, page);
	}

	@Override
	public List<Profesor> buscarProfesoresSinAsignar(Integer idCurso) {
		return profesoresRepo.findProfesoresNoAsignados(idCurso);
	}

	@Override
	public void asignarProfesoresACurso(Integer cursoId, Integer profesorId) {
		profesoresRepo.asignarProfesorACurso(cursoId, profesorId);
		
	}

	@Override
	public void eliminarProfesoresDeCurso(Integer cursoId, Integer profesorId) {
		profesoresRepo.eliminarProfesorDeCurso(cursoId, profesorId);
		
	}

}
