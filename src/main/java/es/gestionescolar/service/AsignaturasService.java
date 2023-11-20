package es.gestionescolar.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import es.gestionescolar.model.Asignatura;
import es.gestionescolar.model.Nota;
import es.gestionescolar.repository.AsignaturasRepository;

@Service
public class AsignaturasService implements IAsignaturasService{

	@Autowired
	private AsignaturasRepository asignaturasRepo;
	
	@Override
	public List<Asignatura> buscarTodas() {
		return asignaturasRepo.findAll();
	}
	
	
	@Override
	public Page<Asignatura> buscarTodas(Pageable page) {
		return asignaturasRepo.findAll(page);
	}

	@Override
	public void guardar(Asignatura asignatura) {
		asignaturasRepo.save(asignatura);
		
	}

	

	@Override
	public Asignatura buscarPorId(Integer idAsignatura) {
		 Optional<Asignatura> optional = asignaturasRepo.findById(idAsignatura);
	        if (optional.isPresent()) {
	            return optional.get();
	        }
	        return null;
	}

	@Override
	public void eliminar(Integer idAsignatura) {
		asignaturasRepo.deleteById(idAsignatura);
		
	}


	@Override
	public Page<Asignatura> buscarAsignaturasPorCurso(Integer idCurso, Pageable page) {
		return asignaturasRepo.findAsignaturasByCursoId(idCurso, page);
	}


	@Override
	public void asignarAsignaturaACurso(Integer cursoId, Integer asignaturaId) {
		asignaturasRepo.asignarAsignaturaACurso(cursoId, asignaturaId);
		
	}


	@Override
	public void eliminarAsignaturaDeCurso(Integer cursoId, Integer asignaturaId) {
		 asignaturasRepo.eliminarAsignaturaDeCurso(cursoId, asignaturaId);
		
	}


	@Override
	public List<Asignatura> buscarAsignaturasSinAsignar(Integer idCurso) {
		
		return asignaturasRepo.findAsignaturasNoAsignadas(idCurso);
	}


	@Override
	public Page<Asignatura> buscarAsignaturasPorProfesor(Integer idProfesor, Pageable page) {
		return asignaturasRepo.findAsignaturasByProfesorId(idProfesor, page);
	}


	@Override
	public void asignarAsignaturaAProfesor(Integer asignaturaId, Integer profesorId) {
		asignaturasRepo.asignarAsignaturaAProfesor(asignaturaId, profesorId);
		
	}


	@Override
	public void eliminarAsignaturaDeProfesor(Integer asignaturaId, Integer profesorId) {
		asignaturasRepo.eliminarAsignaturaDeProfesor(asignaturaId, profesorId);
		
	}


	@Override
	public List<Asignatura> buscarAsignaturasSinAsignarP(Integer idProfesor) {
		return asignaturasRepo.findAsignaturasNoAsignadasP(idProfesor);
	}




	


	

}
