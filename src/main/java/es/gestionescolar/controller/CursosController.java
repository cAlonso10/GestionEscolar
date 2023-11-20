package es.gestionescolar.controller;

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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import es.gestionescolar.model.Curso;
import es.gestionescolar.model.Nota;
import es.gestionescolar.service.ICursosService;

@Controller
@RequestMapping(value = "/cursos")
public class CursosController {

	@Autowired
	private ICursosService serviceCursos;
	
	@GetMapping("/index")
    public String mostrarIndex(Model model, Pageable page) {
        Page<Curso> lista = serviceCursos.buscarTodas(page);
        model.addAttribute("curso", lista);
        return "cursos/listCursos";
    }
	
	@GetMapping("/create")
    public String crear(Curso curso) {
        return "cursos/formCursos";
    }
	
	 @PostMapping("/save")
	    public String guardar(Curso curso, BindingResult result, RedirectAttributes attributes) {
	        if (result.hasErrors()) {
	            System.out.println("Existieron errores");
	            return "cursos/formCursos";
	        }

	        // Guadamos el objeto categoria en la bd
	        serviceCursos.guardar(curso);
	        attributes.addFlashAttribute("msg", "Los datos de la asignatura fueron guardados!");
	        return "redirect:/cursos/index";
	    }
	 
	 @GetMapping("/edit/{id}")
	    public String editar(@PathVariable("id") int idCurso, Model model) {
	        Curso curso = serviceCursos.buscarPorId(idCurso);
	        model.addAttribute("curso", curso);
	        return "cursos/formCursos";
	    }

	    @GetMapping("/delete/{id}")
	    public String eliminar(@PathVariable("id") int idCurso, RedirectAttributes attributes) {
	    	serviceCursos.eliminar(idCurso);
	        attributes.addFlashAttribute("msg", "El curso fue eliminado!.");
	        return "redirect:/cursos/index";
	    }
	    
	    @GetMapping("/view/{id}")
	    public String verDetalle(@PathVariable("id") int idCurso, Model model) {
	        Curso curso = serviceCursos.buscarPorId(idCurso);
	        model.addAttribute("curso", curso);
	        return "cursos/detalle";
	    }
	    
	    @GetMapping("/desasignarAlumnos")
	    public String desasignarAlumnos(RedirectAttributes attributes) {
	    	serviceCursos.desasignarAlumnosDeTodosLosCursos();
	    	attributes.addFlashAttribute("msg", "Se han desasignado todos los alumnos de todos los cursos");
	        return "redirect:/"; 
	    }
	   
	    @GetMapping("/desasignarProfesores")
	    public String desasignarProfesores(RedirectAttributes attributes) {
	    	serviceCursos.desasignarProfesoresDeTodosLosCursos();
	    	attributes.addFlashAttribute("msg", "Se han desasignado todos los profesores de todos los cursos");
	        return "redirect:/"; 
	    }
	    
}
