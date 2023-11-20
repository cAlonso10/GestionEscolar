package es.gestionescolar.service;

import java.util.List;

import es.gestionescolar.model.Asignatura;
import es.gestionescolar.model.Curso;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IAsignaturasService {
	
	List<Asignatura> buscarTodas();
	Page<Asignatura>buscarTodas(Pageable page);
	void guardar(Asignatura asignatura);
	Asignatura buscarPorId(Integer idAsignatura);	
	void eliminar(Integer idAsignatura);
	Page<Asignatura> buscarAsignaturasPorCurso(Integer idCurso, Pageable page);
	Page<Asignatura> buscarAsignaturasPorProfesor(Integer idProfesor, Pageable page);
	List<Asignatura> buscarAsignaturasSinAsignar(Integer idCurso);
	List<Asignatura> buscarAsignaturasSinAsignarP(Integer idProfesor);
	public void asignarAsignaturaACurso(Integer cursoId, Integer asignaturaId);
	public void eliminarAsignaturaDeCurso(Integer cursoId, Integer asignaturaId);
	public void asignarAsignaturaAProfesor(Integer asignaturaId, Integer profesorId);
	public void eliminarAsignaturaDeProfesor(Integer asignaturaId, Integer profesorId);
}
