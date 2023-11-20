package es.gestionescolar.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import es.gestionescolar.model.Asignatura;
import es.gestionescolar.model.Profesor;



public interface IProfesoresService {

	List<Profesor> buscarTodas();
	Page<Profesor>buscarTodas(Pageable page);
	void guardar(Profesor asignatura);
	Profesor buscarPorId(Integer idProfesor);	
	void eliminar(Integer idProfesor);
	Page<Profesor> buscarProfesoresPorCurso(Integer idCurso, Pageable page);
	List<Profesor> buscarProfesoresSinAsignar(Integer idCurso);
	public void asignarProfesoresACurso(Integer cursoId, Integer profesorId);
	public void eliminarProfesoresDeCurso(Integer cursoId, Integer profesorId);
}
