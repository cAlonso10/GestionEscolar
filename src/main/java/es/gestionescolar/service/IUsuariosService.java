package es.gestionescolar.service;


import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import es.gestionescolar.model.Usuario;


public interface IUsuariosService {

	
	void guardar(Usuario usuario);
	void eliminar(Integer idUsuario);
	List<Usuario> buscarTodos();
        Page<Usuario>buscarTodas(Pageable page);
        Usuario buscarPorUsername(String username);
        void blockUser(Integer idUsuario);
        void unblockUser(Integer idUsuario);
        void switchRol(Integer idUsuario);
        boolean existsByUsername(String username);
}
