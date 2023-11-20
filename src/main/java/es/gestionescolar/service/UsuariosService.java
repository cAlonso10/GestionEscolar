package es.gestionescolar.service;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import es.gestionescolar.model.Usuario;
import es.gestionescolar.repository.UsuariosRepository;

@Service
public class UsuariosService implements IUsuariosService{

    @Autowired
    private UsuariosRepository usuariosRepo;
    
    @Override
    public void guardar(Usuario usuario) {
        usuariosRepo.save(usuario);
    }

    @Override
    public void eliminar(Integer idUsuario) {
        usuariosRepo.deleteById(idUsuario);
    }

    @Override
    public List<Usuario> buscarTodos() {
        return usuariosRepo.findAll();
    }

    @Override
    public Page<Usuario> buscarTodas(Pageable page) {
        return usuariosRepo.findAll(page);
    }

    @Override
    public Usuario buscarPorUsername(String username) {
        return usuariosRepo.findByUsername(username);
    }

	@Override
	public void blockUser(Integer idUsuario) {
		usuariosRepo.blockUser(idUsuario);
		
	}

	@Override
	public void unblockUser(Integer idUsuario) {
		usuariosRepo.unblockUser(idUsuario);
		
	}

	@Override
	public void switchRol(Integer idUsuario) {
		usuariosRepo.switchRol(idUsuario);
		
	}

	@Override
	public boolean existsByUsername(String username) {
		return usuariosRepo.existsByUsername(username);
	}
    
}