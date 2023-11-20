package es.gestionescolar.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import es.gestionescolar.model.Perfil;
import es.gestionescolar.model.Usuario;
import es.gestionescolar.repository.AlumnosRepository;
import es.gestionescolar.service.IAlumnosService;
import es.gestionescolar.service.INotasService;
import es.gestionescolar.service.IUsuariosService;
import jakarta.servlet.http.HttpServletRequest;

@Controller
public class HomeController {
	
	@Autowired
    private IUsuariosService serviceUsuarios;
	
	@Autowired
    private IAlumnosService serviceAlumnos;
	
	@Autowired
    private INotasService serviceNotas;
	
	@Autowired
    private PasswordEncoder passwordEncoder;
	
	@GetMapping("/")
    public String mostrarHome(Model model, Authentication auth) {
		String username = auth.getName();
		model.addAttribute("username", username);
        return "home";
    }
	
	/*@GetMapping("/login")
    public String mostrarLogin() {
        return "formLogin";
    }*/
	
	@GetMapping("/login")
    public String loginPage(@RequestParam(value = "error", required = false) String error, Model model) {
        if (error != null) {
            model.addAttribute("error", "Credenciales incorrectas");
        }
        return "formLogin";
    }


    @GetMapping("/logout")
    public String logout(HttpServletRequest request) {
        SecurityContextLogoutHandler logoutHandler
                = new SecurityContextLogoutHandler();
        logoutHandler.logout(request, null, null);
        return "redirect:/login";
    }
    
    @GetMapping("/signup")
    public String registrarse(Usuario usuario) {
        return "formRegistro";
    }

    @PostMapping("/signup")
    public String guardarRegistro(Usuario usuario, RedirectAttributes attributes) {

        String pwdPlano = usuario.getPassword();
        String pwdEncriptado = passwordEncoder.encode(pwdPlano);
        usuario.setPassword(pwdEncriptado);
        usuario.setEstatus(1);
        usuario.setFechaRegistro(new Date());
        Perfil perfil = new Perfil();
        perfil.setId(2);
        usuario.agregar(perfil);
        serviceUsuarios.guardar(usuario);
        attributes.addFlashAttribute("msg", "Usuario agregado con exito");
        return "redirect:/";
    }
    
    
    
   
}

