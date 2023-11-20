package es.gestionescolar.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import es.gestionescolar.model.Curso;

public interface CursosRepository extends JpaRepository<Curso, Integer>{

	@Modifying
	@Transactional
	@Query(value = "UPDATE alumnos SET curso_id = NULL", nativeQuery = true)
	 void desasignarAlumgosDeLosCursos();
	
	@Modifying
	@Transactional
	@Query(value = "Delete from Curso_Profesores", nativeQuery = true)
	 void desasignarProfesoresDeLosCursos();
}

