package es.gestionescolar.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import es.gestionescolar.model.Alumno;
import es.gestionescolar.model.Curso;
import es.gestionescolar.repository.AlumnosRepository;
import es.gestionescolar.repository.CursosRepository;

@Service
public class AlumnosService implements IAlumnosService{
	
	@Autowired
	private AlumnosRepository alumnosRepo;

	@Override
	public List<Alumno> buscarTodas() {
		return alumnosRepo.findAll();
	}

	@Override
	public Page<Alumno> buscarTodas(Pageable page) {
		return alumnosRepo.findAll(page);
	}

	@Override
	public void guardar(Alumno alumno) {
		alumnosRepo.save(alumno);
		
	}

	@Override
	public Alumno buscarPorId(Integer idAlumno) {
		Optional<Alumno> optional = alumnosRepo.findById(idAlumno);
        if (optional.isPresent()) {
            return optional.get();
        }
        return null;
	}

	@Override
	public void eliminar(Integer idAlumno) {
		alumnosRepo.deleteById(idAlumno);
		
	}

	@Override
	public Page<Alumno> buscarAlumnosPorCurso(Integer idCurso, Pageable page) {
		return alumnosRepo.findAlumnosByCursoId(idCurso, page);
	}

	@Override
	public List<Alumno> buscarAlumnosSinAsignar(Integer idCurso) {
		return alumnosRepo.findAlumnosNoAsignados(idCurso);
	}

	@Override
	public void asignarAlumnoACurso(Integer cursoId, Integer alumnoId) {
		alumnosRepo.asignarAlumnoACurso(cursoId, alumnoId);
		
	}

	@Override
	public void eliminarAlumnoDeCurso(Integer cursoId, Integer alumnoId) {
		alumnosRepo.eliminarAlumnoDeCurso(alumnoId);
		
	}

	@Override
	public void borrarRegistrosAlumnos() {
		alumnosRepo.borrarRegistrosAlumnos();
		
	}

	

	

}
