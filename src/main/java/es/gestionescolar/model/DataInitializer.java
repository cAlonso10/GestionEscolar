package es.gestionescolar.model;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import es.gestionescolar.service.IPerfilesService;
import es.gestionescolar.service.IUsuariosService;

@Component
public class DataInitializer implements CommandLineRunner {
	
	@Autowired
    private IUsuariosService serviceUsuarios;
	
	@Autowired
    private IPerfilesService servicePerfiles;

	@Autowired
    private PasswordEncoder passwordEncoder;
	
    @Override
    public void run(String... args) throws Exception {
        if (!serviceUsuarios.existsByUsername("admin")) {
            Usuario admin = new Usuario();
            admin.setUsername("admin");
            admin.setNombre("admin");
            admin.setEmail("admin@admin.com");
            String pwdPlano = "admin";
            String pwdEncriptado = passwordEncoder.encode(pwdPlano);
            admin.setPassword(pwdEncriptado);
            admin.setEstatus(1);
            admin.setFechaRegistro(new Date());
            
            Perfil perfilA = new Perfil();
            perfilA.setId(1);
            perfilA.setPerfil("ADMINISTRADOR");
            Perfil perfilU = new Perfil();
            perfilU.setId(2);
            perfilU.setPerfil("USUARIO");
            servicePerfiles.guardar(perfilA);
            servicePerfiles.guardar(perfilU);
            
            admin.agregar(perfilA);
            
            serviceUsuarios.guardar(admin);
            
            
        } else {
            System.out.println("El usuario admin ya existe.");
        }
    }
}