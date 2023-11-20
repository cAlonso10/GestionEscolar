package es.gestionescolar.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import es.gestionescolar.model.Curso;



public interface ICursosService {
	List<Curso> buscarTodas();
	Page<Curso>buscarTodas(Pageable page);
	void guardar(Curso curso);
	Curso buscarPorId(Integer idCurso);	
	void eliminar(Integer idCurso);
	void desasignarAlumnosDeTodosLosCursos();
	void desasignarProfesoresDeTodosLosCursos();
}
