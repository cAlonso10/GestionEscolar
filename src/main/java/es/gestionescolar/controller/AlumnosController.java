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

import es.gestionescolar.model.Alumno;
import es.gestionescolar.model.Asignatura;
import es.gestionescolar.model.Curso;
import es.gestionescolar.service.IAlumnosService;
import es.gestionescolar.service.ICursosService;
import es.gestionescolar.service.INotasService;

@Controller
@RequestMapping(value = "/alumnos")
public class AlumnosController {

	@Autowired
	private IAlumnosService serviceAlumnos;
	
	@Autowired
	private INotasService serviceNotas;
	
	@Autowired
	private ICursosService serviceCursos;
	
	@GetMapping("/index")
    public String mostrarIndex(Model model, Pageable page) {
        Page<Alumno> lista = serviceAlumnos.buscarTodas(page);
        model.addAttribute("alumno", lista);
        return "alumnos/listAlumnos";
    }
	
	@GetMapping("/create")
    public String crear(Alumno alumno) {
        return "alumnos/formAlumnos";
    }
	
	 @PostMapping("/save")
	    public String guardar(Alumno alumno, BindingResult result, RedirectAttributes attributes) {
	        if (result.hasErrors()) {
	            System.out.println("Existieron errores");
	            return "alumnos/formAlumnos";
	        }

	        // Guadamos el objeto categoria en la bd
	        serviceAlumnos.guardar(alumno);
	        attributes.addFlashAttribute("msg", "Los datos del alumno fueron guardados!");
	        return "redirect:/alumnos/index";
	    }
	 
	 @GetMapping("/edit/{id}")
	    public String editar(@PathVariable("id") int idAlumno, Model model) {
	        Alumno alumno = serviceAlumnos.buscarPorId(idAlumno);
	        model.addAttribute("alumno", alumno);
	        return "alumnos/formAlumnos";
	    }

	    @GetMapping("/delete/{id}")
	    public String eliminar(@PathVariable("id") int idAlumno, RedirectAttributes attributes) {
	    	serviceAlumnos.eliminar(idAlumno);
	        attributes.addFlashAttribute("msg", "El alumno fue eliminado!.");
	        return "redirect:/alumnos/index";
	    }
	    
	    @GetMapping("/view/{id}")
	    public String verDetalle(@PathVariable("id") int idAlumno, Model model) {
	        Alumno alumno = serviceAlumnos.buscarPorId(idAlumno);
	        model.addAttribute("alumno", alumno);
	        return "alumnos/detalle";
	    }
	    
	    @GetMapping("/curso/{idCurso}")
	    public String filtrarAlumnosPorCurso(@PathVariable("idCurso") Integer idCurso, Model model, Pageable page) {
	    	
			Page<Alumno> lista = serviceAlumnos.buscarAlumnosPorCurso(idCurso, page);
			Curso curso = serviceCursos.buscarPorId(idCurso);
			String nombreCurso = curso.getNombre();
			model.addAttribute("nombreCurso", nombreCurso);
	        model.addAttribute("alumnos", lista);
	        
	        return "alumnos/listAlumnosCurso"; 
	    }
	    
	    @PostMapping("/asignarAlumno/{cursoId}")
	    public String asignarAlumnoACurso(
	            @PathVariable("cursoId") Integer cursoId,
	            @RequestParam("alumnoId") Integer alumnoId,
	            RedirectAttributes attributes
	    ) {
	        // Llama al servicio para asignar la asignatura al curso
	        serviceAlumnos.asignarAlumnoACurso(cursoId, alumnoId);
	        attributes.addFlashAttribute("msg", "El alumno fue asignado al curso.");
	        return "redirect:/alumnos/curso/" + cursoId;
	    }

	    
	    @GetMapping("/asignar/{cursoId}")
	    public String asignarAlumnoACursoForm(@PathVariable("cursoId") int idCurso, Model model) {
	        List<Alumno> alumnos = serviceAlumnos.buscarAlumnosSinAsignar(idCurso);
	        model.addAttribute("alumnos", alumnos);
	        model.addAttribute("cursoId", idCurso);
	        return "alumnos/formAlumnosCurso";
	    }
	    
	    @GetMapping("/eliminar/{cursoId}/{alumnoId}")
	    public String eliminarAlumnoDeCurso(
	        @PathVariable("cursoId") Integer cursoId,
	        @PathVariable("alumnoId") Integer alumnoId,
	        RedirectAttributes attributes
	    ) {
	    	serviceAlumnos.eliminarAlumnoDeCurso(cursoId, alumnoId);
	        attributes.addFlashAttribute("msg", "El alumno fue eliminado del curso.");
	        return "redirect:/alumnos/curso/" + cursoId;
	    }
	    
	    @GetMapping("/borrarRegistrosAlumnos")
	    public String borrarRegistrosAlumnos(RedirectAttributes attributes) {
	    	serviceNotas.borrarRegistrosNotas();
	    	serviceAlumnos.borrarRegistrosAlumnos();
	    	attributes.addFlashAttribute("msg", "Información sobre las notas y los alumnos eliminada");
	        return "redirect:/";
	    }
}

