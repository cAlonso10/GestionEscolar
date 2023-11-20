package es.gestionescolar.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import es.gestionescolar.model.Alumno;
import es.gestionescolar.model.Asignatura;

public interface AlumnosRepository extends JpaRepository<Alumno, Integer>{
	
	 @Query("SELECT a FROM Alumno a JOIN a.curso c WHERE c.id = :cursoId")
	 Page<Alumno> findAlumnosByCursoId(@Param("cursoId") Integer cursoId, Pageable pageable);
	 
	 @Modifying
	 @Transactional
	 @Query(value = "UPDATE Alumnos SET curso_id = :cursoId WHERE id = :alumnoId", nativeQuery = true)
	 void asignarAlumnoACurso(@Param("cursoId") Integer cursoId, @Param("alumnoId") Integer alumnoId);

	 @Modifying
	 @Transactional
	 @Query(value = "UPDATE Alumnos SET curso_id = NULL WHERE id = :alumnoId", nativeQuery = true)
	 void eliminarAlumnoDeCurso(@Param("alumnoId") Integer alumnoId);

	 @Query("SELECT a FROM Alumno a WHERE a.curso IS NULL")
	 List<Alumno> findAlumnosNoAsignados(@Param("cursoId") Integer cursoId);

	 @Modifying
	 @Transactional
	 @Query(value = "delete from Alumnos", nativeQuery = true)
	 void borrarRegistrosAlumnos();
	 
	 
	 
}

