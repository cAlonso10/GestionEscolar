package es.gestionescolar.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import es.gestionescolar.model.Curso;
import es.gestionescolar.model.Profesor;
import es.gestionescolar.service.ICursosService;
import es.gestionescolar.service.IProfesoresService;

@Controller
@RequestMapping(value = "/profesores")
public class ProfesoresController {

	@Autowired
	private IProfesoresService serviceProfesores;
	
	@Autowired
	private ICursosService serviceCursos;
	
	@GetMapping("/index")
    public String mostrarIndex(Model model, Pageable page) {
        Page<Profesor> lista = serviceProfesores.buscarTodas(page);
        model.addAttribute("profesores", lista);
        return "profesores/listProfesores";
    }
	
	@GetMapping("/create")
    public String crear(Profesor Profesor) {
        return "profesores/formProfesores";
    }
	
	 @PostMapping("/save")
	    public String guardar(Profesor Profesor, BindingResult result, RedirectAttributes attributes) {
	        if (result.hasErrors()) {
	            System.out.println("Existieron errores");
	            return "profesores/formProfesores";
	        }

	        // Guadamos el objeto categoria en la bd
	        serviceProfesores.guardar(Profesor);
	        attributes.addFlashAttribute("msg", "Los datos del Profesor fueron guardados!");
	        return "redirect:/profesores/index";
	    }
	 
	 @GetMapping("/edit/{id}")
	    public String editar(@PathVariable("id") int idProfesor, Model model) {
	        Profesor Profesor = serviceProfesores.buscarPorId(idProfesor);
	        model.addAttribute("profesor", Profesor);
	        return "profesores/formProfesores";
	    }

	    @GetMapping("/delete/{id}")
	    public String eliminar(@PathVariable("id") int idProfesor, RedirectAttributes attributes) {
	        serviceProfesores.eliminar(idProfesor);
	        attributes.addFlashAttribute("msg", "El Profesor fue eliminada!.");
	        return "redirect:/profesores/index";
	    }
	    
	    @GetMapping("/view/{id}")
	    public String verDetalle(@PathVariable("id") int idProfesor, Model model) {
	        Profesor Profesor = serviceProfesores.buscarPorId(idProfesor);
	        model.addAttribute("profesor", Profesor);
	        return "profesores/detalle";
	    }
	    
	    @GetMapping("/curso/{idCurso}")
	    public String filtrarProfesoresPorCurso(@PathVariable("idCurso") Integer idCurso, Model model, Pageable page) {
	    	
			Page<Profesor> lista = serviceProfesores.buscarProfesoresPorCurso(idCurso, page);
			Curso curso = serviceCursos.buscarPorId(idCurso);
			String nombreCurso = curso.getNombre();
			model.addAttribute("nombreCurso", nombreCurso);
	        model.addAttribute("profesores", lista);
	        
	        return "profesores/listProfesoresCurso"; 
	    }
	    
	    @PostMapping("/asignarProfesor/{cursoId}")
	    public String asignarProfesorACurso(
	            @PathVariable("cursoId") Integer cursoId,
	            @RequestParam("profesorId") Integer profesorId,
	            RedirectAttributes attributes
	    ) {
	    	serviceProfesores.asignarProfesoresACurso(cursoId, profesorId);
	        attributes.addFlashAttribute("msg", "El profesor fue asignado al curso.");
	        return "redirect:/profesores/curso/" + cursoId;
	    }

	    
	    @GetMapping("/asignar/{cursoId}")
	    public String asignarProfesorACursoForm(@PathVariable("cursoId") int idCurso, Model model) {
	        List<Profesor> profesores = serviceProfesores.buscarProfesoresSinAsignar(idCurso);
	        model.addAttribute("profesores", profesores);
	        model.addAttribute("cursoId", idCurso);
	        return "profesores/formProfesoresCurso";
	    }
	    
	    @GetMapping("/eliminar/{cursoId}/{profesorId}")
	    public String eliminarProfesorDeCurso(
	        @PathVariable("cursoId") Integer cursoId,
	        @PathVariable("profesorId") Integer profesorId,
	        RedirectAttributes attributes
	    ) {
	        serviceProfesores.eliminarProfesoresDeCurso(cursoId, profesorId);
	        attributes.addFlashAttribute("msg", "El profesor fue eliminado del curso.");
	        return "redirect:/profesores/curso/" + cursoId;
	    }
	    
	    
}
