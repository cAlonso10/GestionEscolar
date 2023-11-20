package es.gestionescolar.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.gestionescolar.model.Asignatura;
import es.gestionescolar.model.Curso;
import es.gestionescolar.repository.CursosRepository;

@Service

public class CursosService implements ICursosService{

	@Autowired
	private CursosRepository cursosRepo;
	
	@Override
	public List<Curso> buscarTodas() {
		return cursosRepo.findAll();
	}

	@Override
	public Page<Curso> buscarTodas(Pageable page) {
		return cursosRepo.findAll(page);
	}

	@Override
	public void guardar(Curso curso) {
		cursosRepo.save(curso);
		
	}

	@Override
	public Curso buscarPorId(Integer idCurso) {
		Optional<Curso> optional = cursosRepo.findById(idCurso);
        if (optional.isPresent()) {
            return optional.get();
        }
        return null;
	}

	@Override
	public void eliminar(Integer idCurso) {
		cursosRepo.deleteById(idCurso);
		
	}

	@Override
    public void desasignarAlumnosDeTodosLosCursos() {
		cursosRepo.desasignarAlumgosDeLosCursos();
    }

	@Override
	public void desasignarProfesoresDeTodosLosCursos() {
		cursosRepo.desasignarProfesoresDeLosCursos();
		
	}

	
}
