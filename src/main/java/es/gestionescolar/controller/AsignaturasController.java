package es.gestionescolar.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import es.gestionescolar.model.Asignatura;
import es.gestionescolar.model.Curso;
import es.gestionescolar.model.Nota;
import es.gestionescolar.model.Profesor;
import es.gestionescolar.service.IAsignaturasService;
import es.gestionescolar.service.ICursosService;
import es.gestionescolar.service.IProfesoresService;

@Controller
@RequestMapping(value = "/asignaturas")
public class AsignaturasController {

	@Autowired
	private IAsignaturasService serviceAsignaturas;
	
	@Autowired
	private ICursosService serviceCursos;
	
	@Autowired
	private IProfesoresService serviceProfesores;
	
	@GetMapping("/index")
    public String mostrarIndex(Model model, Pageable page) {
        Page<Asignatura> lista = serviceAsignaturas.buscarTodas(page);
        model.addAttribute("asignaturas", lista);
        return "asignaturas/listAsignatura";
    }
	
	@GetMapping("/create")
    public String crear(Asignatura asignatura) {
        return "asignaturas/formAsignatura";
    }
	
	 @PostMapping("/save")
	    public String guardar(Asignatura asignatura, BindingResult result, RedirectAttributes attributes) {
	        if (result.hasErrors()) {
	            System.out.println("Existieron errores");
	            return "asignaturas/formAsignatura";
	        }

	        // Guadamos el objeto categoria en la bd
	        serviceAsignaturas.guardar(asignatura);
	        attributes.addFlashAttribute("msg", "Los datos de la asignatura fueron guardados!");
	        return "redirect:/asignaturas/index";
	    }
	 
	 @GetMapping("/edit/{id}")
	    public String editar(@PathVariable("id") int idAsignatura, Model model) {
	        Asignatura asignatura = serviceAsignaturas.buscarPorId(idAsignatura);
	        model.addAttribute("asignatura", asignatura);
	        return "asignaturas/formAsignatura";
	    }

	    @GetMapping("/delete/{id}")
	    public String eliminar(@PathVariable("id") int idAsignatura, RedirectAttributes attributes) {
	        serviceAsignaturas.eliminar(idAsignatura);
	        attributes.addFlashAttribute("msg", "La asignatura fue eliminada!.");
	        return "redirect:/asignaturas/index";
	    }
	    
	    @GetMapping("/view/{id}")
	    public String verDetalle(@PathVariable("id") int idAsignatura, Model model) {
	        Asignatura asignatura = serviceAsignaturas.buscarPorId(idAsignatura);
	        model.addAttribute("asignatura", asignatura);
	        return "asignaturas/detalle";
	    }
	    
	    @GetMapping("/curso/{idCurso}")
	    public String filtrarAsignaturasPorCurso(@PathVariable("idCurso") Integer idCurso, Model model, Pageable page) {
	    	
			Page<Asignatura> lista = serviceAsignaturas.buscarAsignaturasPorCurso(idCurso, page);
			Curso curso = serviceCursos.buscarPorId(idCurso);
			String nombreCurso = curso.getNombre();
			model.addAttribute("nombreCurso", nombreCurso);
	        model.addAttribute("asignaturas", lista);
	        
	        return "asignaturas/listAsignaturaCurso"; 
	    }
	    
	    @PostMapping("/asignarAsignatura/{cursoId}")
	    public String asignarAsignaturaACurso(
	            @PathVariable("cursoId") Integer cursoId,
	            @RequestParam("asignaturaId") Integer asignaturaId,
	            RedirectAttributes attributes
	    ) {
	        serviceAsignaturas.asignarAsignaturaACurso(cursoId, asignaturaId);
	        attributes.addFlashAttribute("msg", "La asignatura fue asignada al curso.");
	        return "redirect:/asignaturas/curso/" + cursoId;
	    }

	    
	    @GetMapping("/asignar/{cursoId}")
	    public String asignarAsignaturaACursoForm(@PathVariable("cursoId") int idCurso, Model model) {
	        List<Asignatura> asignaturas = serviceAsignaturas.buscarAsignaturasSinAsignar(idCurso);
	        model.addAttribute("asignaturas", asignaturas);
	        model.addAttribute("cursoId", idCurso);
	        return "asignaturas/formAsignaturaCurso";
	    }
	    
	    @GetMapping("/eliminar/{cursoId}/{asignaturaId}")
	    public String eliminarAsignaturaDeCurso(
	        @PathVariable("cursoId") Integer cursoId,
	        @PathVariable("asignaturaId") Integer asignaturaId,
	        RedirectAttributes attributes
	    ) {
	        serviceAsignaturas.eliminarAsignaturaDeCurso(cursoId, asignaturaId);
	        attributes.addFlashAttribute("msg", "La asignatura fue eliminada del curso.");
	        return "redirect:/asignaturas/curso/" + cursoId;
	    }
	    
	    @GetMapping("/profesor/{profesorId}")
	    public String filtrarAsignaturasPorProfesor(@PathVariable("profesorId") Integer profesorId, Model model, Pageable page) {
	    	
			Page<Asignatura> lista = serviceAsignaturas.buscarAsignaturasPorProfesor(profesorId, page);
			Profesor profesor = serviceProfesores.buscarPorId(profesorId);
			String nombreProfesor = profesor.getNombre();
			model.addAttribute("nombreProfesor", nombreProfesor);
	        model.addAttribute("asignaturas", lista);
	        
	        return "asignaturas/listAsignaturaProfesor"; 
	    }
	    
	    @GetMapping("/asignarP/{profesorId}")
	    public String asignarAsignaturaAProfesorForm(@PathVariable("profesorId") int profesorId, Model model) {
	        List<Asignatura> asignaturas = serviceAsignaturas.buscarAsignaturasSinAsignarP(profesorId);
	        model.addAttribute("asignaturas", asignaturas);
	        model.addAttribute("profesorId", profesorId);
	        return "asignaturas/formAsignaturaProfesor";
	    }
	    
	    @PostMapping("/asignarAsignaturaP/{profesorId}")
	    public String asignarAsignaturaAProfesor(
	            @PathVariable("profesorId") Integer profesorId,
	            @RequestParam("asignaturaId") Integer asignaturaId,
	            RedirectAttributes attributes
	    ) {
	        serviceAsignaturas.asignarAsignaturaAProfesor(asignaturaId, profesorId);
	        attributes.addFlashAttribute("msg", "La asignatura fue asignada al profesor.");
	        return "redirect:/asignaturas/profesor/" + profesorId;
	    }
	    
	    @GetMapping("/eliminarP/{profesorId}/{asignaturaId}")
	    public String eliminarAsignaturaDeProfesor(
	        @PathVariable("profesorId") Integer profesorId,
	        @PathVariable("asignaturaId") Integer asignaturaId,
	        RedirectAttributes attributes
	    ) {
	        serviceAsignaturas.eliminarAsignaturaDeProfesor(asignaturaId, profesorId);
	        attributes.addFlashAttribute("msg", "La asignatura fue eliminada del profesor.");
	        return "redirect:/asignaturas/profesor/" + profesorId;
	    }
	    
}
