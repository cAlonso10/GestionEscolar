package es.gestionescolar.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import es.gestionescolar.model.Asignatura;

public interface AsignaturasRepository extends JpaRepository<Asignatura, Integer>{
	 @Query("SELECT a FROM Asignatura a JOIN a.cursos c WHERE c.id = :cursoId")
	 Page<Asignatura> findAsignaturasByCursoId(@Param("cursoId") Integer cursoId, Pageable pageable);

	 @Query("SELECT a FROM Asignatura a JOIN a.profesores p WHERE p.id = :profesorId")
	 Page<Asignatura> findAsignaturasByProfesorId(@Param("profesorId") Integer profesorId, Pageable pageable);

	 @Modifying
	 @Transactional
	 @Query(value = "INSERT INTO Curso_Asignaturas (curso_id, asignatura_id) VALUES (:cursoId, :asignaturaId)", nativeQuery = true)
	 void asignarAsignaturaACurso(@Param("cursoId") Integer cursoId, @Param("asignaturaId") Integer asignaturaId);
	
	 @Modifying
	 @Transactional
	 @Query(value = "INSERT INTO Asignatura_Profesores (asignatura_id, profesor_id) VALUES (:asignaturaId, :profesorId)", nativeQuery = true)
	 void asignarAsignaturaAProfesor(@Param("asignaturaId") Integer asignaturaId, @Param("profesorId") Integer profesorId);
	 
	 @Modifying
	 @Transactional
	 @Query(value = "DELETE FROM Curso_Asignaturas WHERE curso_id = :cursoId AND asignatura_id = :asignaturaId", nativeQuery = true)
	 void eliminarAsignaturaDeCurso(@Param("cursoId") Integer cursoId, @Param("asignaturaId") Integer asignaturaId);
	 
	 @Modifying
	 @Transactional
	 @Query(value = "DELETE FROM Asignatura_Profesores WHERE asignatura_id = :asignaturaId AND profesor_id = :profesorId", nativeQuery = true)
	 void eliminarAsignaturaDeProfesor(@Param("asignaturaId") Integer asignaturaId, @Param("profesorId") Integer profesorId);
	
	 @Query("SELECT a FROM Asignatura a WHERE a.id NOT IN (SELECT ca.id FROM Curso c JOIN c.asignaturas ca WHERE c.id = :cursoId)")
	 List<Asignatura> findAsignaturasNoAsignadas(@Param("cursoId") Integer cursoId);
	 
	 @Query("SELECT a FROM Asignatura a WHERE a.id NOT IN (SELECT ap.id FROM Profesor p JOIN p.asignaturas ap WHERE p.id = :profesorId)")
	 List<Asignatura> findAsignaturasNoAsignadasP(@Param("profesorId") Integer profesorId);

}
