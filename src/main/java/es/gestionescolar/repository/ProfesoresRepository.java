package es.gestionescolar.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import es.gestionescolar.model.Profesor;

public interface ProfesoresRepository extends JpaRepository<Profesor, Integer>{
	
	@Query("SELECT p FROM Profesor p JOIN p.cursos c WHERE c.id = :cursoId")
	 Page<Profesor> findProfesoresByCursoId(@Param("cursoId") Integer cursoId, Pageable pageable);
	 
	@Query("SELECT p FROM Profesor p WHERE p.id NOT IN (SELECT pr.id FROM Curso c JOIN c.profesores pr WHERE c.id = :cursoId)")
	List<Profesor> findProfesoresNoAsignados(@Param("cursoId") Integer cursoId);
	
	@Modifying
	@Transactional
	@Query(value = "INSERT INTO Curso_Profesores (curso_id, profesor_id) VALUES (:cursoId, :profesorId)", nativeQuery = true)
	void asignarProfesorACurso(@Param("cursoId") Integer cursoId, @Param("profesorId") Integer profesorId);

	@Modifying
	@Transactional
	@Query(value = "DELETE FROM Curso_Profesores WHERE curso_id = :cursoId AND profesor_id = :profesorId", nativeQuery = true)
	void eliminarProfesorDeCurso(@Param("cursoId") Integer cursoId, @Param("profesorId") Integer profesorId);

}
