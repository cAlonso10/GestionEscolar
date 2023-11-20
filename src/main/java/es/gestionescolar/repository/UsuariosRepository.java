package es.gestionescolar.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import es.gestionescolar.model.Usuario;

public interface UsuariosRepository extends JpaRepository<Usuario, Integer>{
    
    Usuario findByUsername(String username);
    
    @Transactional
    @Modifying
    @Query("update Usuario u set u.estatus = 0 where u.id = :id")
    void blockUser(@Param("id") Integer id);
    
    @Transactional
    @Modifying
    @Query("update Usuario u set u.estatus = 1 where u.id = :id")
    void unblockUser(@Param("id") Integer id);
    
    @Transactional
    @Modifying
    @Query(value = "UPDATE usuarioperfil SET idPerfil = CASE WHEN idPerfil = 1 THEN 2 WHEN idPerfil = 2 THEN 1 END WHERE idUsuario = :id", nativeQuery = true)
    void switchRol(@Param("id") Integer id);
    
    boolean existsByUsername(String username);
}

