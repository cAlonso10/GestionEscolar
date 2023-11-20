package es.gestionescolar.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import es.gestionescolar.model.Nota;

public interface NotasRepository extends JpaRepository<Nota, Integer>{
	Page<Nota> findByAlumnoId(Long alumnoId, Pageable pageable);
	
	@Modifying
	 @Transactional
	 @Query(value = "delete from Notas", nativeQuery = true)
	 void borrarRegistrosNotas();
}
