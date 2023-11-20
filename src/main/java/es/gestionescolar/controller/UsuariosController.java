package es.gestionescolar.controller;




import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import es.gestionescolar.model.Usuario;
import es.gestionescolar.service.IUsuariosService;

@Controller
@RequestMapping("/usuarios")
public class UsuariosController {

    @Autowired
    private IUsuariosService serviceUsuarios;

    


    @GetMapping("/index")
    public String mostrarIndexPaginado(Model model, Pageable page) {
        Page<Usuario> lista = serviceUsuarios.buscarTodas(page);
        model.addAttribute("usuarios", lista);
        return "usuarios/listUsuarios";
    }

    @GetMapping("/delete/{id}")
    public String eliminar(@PathVariable("id") int idUsuario, Model model, RedirectAttributes attributes) {
        System.out.println("Borrando usuario con id: " + idUsuario);
        serviceUsuarios.eliminar(idUsuario);
        attributes.addFlashAttribute("msg", "Usuario eliminado");
        return "redirect:/usuarios/index";
    }
    
    @GetMapping("/block/{id}")
    public String blockUser(@PathVariable("id") int idUsuario, RedirectAttributes attributes) {
        serviceUsuarios.blockUser(idUsuario);
        attributes.addFlashAttribute("msg", "Usuario bloqueado");
        return "redirect:/usuarios/index";
    }
    
    @GetMapping("/unblock/{id}")
    public String unblockUser(@PathVariable("id") int idUsuario, RedirectAttributes attributes) {
        serviceUsuarios.unblockUser(idUsuario);
        attributes.addFlashAttribute("msg", "Usuario desbloqueado");
        return "redirect:/usuarios/index";
    }
    
    @GetMapping("/switchRol/{id}")
    public String switchRol(@PathVariable("id") int idUsuario, RedirectAttributes attributes) {
        serviceUsuarios.switchRol(idUsuario);
        attributes.addFlashAttribute("msg", "Se ha cambiado el rol del usuario");
        return "redirect:/usuarios/index";
    }
}
