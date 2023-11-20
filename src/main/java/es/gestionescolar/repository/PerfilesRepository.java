package es.gestionescolar.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import es.gestionescolar.model.Perfil;

public interface PerfilesRepository extends JpaRepository<Perfil, Integer>{
    
    
}
