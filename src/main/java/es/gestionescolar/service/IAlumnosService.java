package es.gestionescolar.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import es.gestionescolar.model.Alumno;
import es.gestionescolar.model.Asignatura;

public interface IAlumnosService {
	List<Alumno> buscarTodas();
	Page<Alumno>buscarTodas(Pageable page);
	void guardar(Alumno alumno);
	Alumno buscarPorId(Integer idAlumno);	
	void eliminar(Integer idAlumno);
	Page<Alumno> buscarAlumnosPorCurso(Integer idCurso, Pageable page);
	List<Alumno> buscarAlumnosSinAsignar(Integer idCurso);
	public void asignarAlumnoACurso(Integer cursoId, Integer alumnoId);
	public void eliminarAlumnoDeCurso(Integer cursoId, Integer alumnoId);
	public void borrarRegistrosAlumnos();
}
