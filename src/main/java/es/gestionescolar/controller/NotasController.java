package es.gestionescolar.controller;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomNumberEditor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import es.gestionescolar.model.Alumno;
import es.gestionescolar.model.Asignatura;
import es.gestionescolar.model.Curso;
import es.gestionescolar.model.Nota;
import es.gestionescolar.service.IAlumnosService;
import es.gestionescolar.service.IAsignaturasService;
import es.gestionescolar.service.ICursosService;
import es.gestionescolar.service.INotasService;


@Controller
@RequestMapping(value = "/notas")
public class NotasController {

	@Autowired
	private INotasService serviceNotas;
	
	
	@Autowired
	private IAlumnosService serviceAlumnos;
	
	
	@GetMapping("/index")
    public String mostrarIndex(Model model, Pageable page) {
        Page<Nota> lista = serviceNotas.buscarTodas(page);
        model.addAttribute("nota", lista);
        return "notas/listNotas";
    }
	
	@GetMapping("/alumno/{idAlumno}")
    public String filtrarNotasPorAlumno(@PathVariable("idAlumno") Long idAlumno, Model model, Pageable page) {
		Page<Nota> lista = serviceNotas.buscarNotasPorAlumno(idAlumno, page);
        model.addAttribute("nota", lista);
        model.addAttribute("idAlumno", idAlumno);
        return "notas/listNotas"; 
    }
	
	@GetMapping("/create")
	public String crear(@RequestParam("id") Integer alumnoId, Model model, RedirectAttributes attributes) {
	    Alumno alumno = serviceAlumnos.buscarPorId(alumnoId);
	    Curso cursoDelAlumno = alumno.getCurso();
	    if (cursoDelAlumno == null) {
	    	attributes.addFlashAttribute("msg", "Debes agregar al alumno a algun curso");
	        return "redirect:/notas/alumno/" + alumnoId;
	    }

	    List<Asignatura> asignaturasDelCurso = cursoDelAlumno.getAsignaturas();

	    Nota nuevaNota = new Nota();
	    nuevaNota.setAlumno(alumno);
	    nuevaNota.setCurso(cursoDelAlumno);

	    model.addAttribute("nota", nuevaNota);
	    model.addAttribute("asignaturas", asignaturasDelCurso);

	    return "notas/formNotas";
	}
	
	@PostMapping("/save")
	public String guardar(Nota nota, BindingResult result, RedirectAttributes attributes) {
	    if (result.hasErrors()) {
	        System.out.println("Existieron errores");
	        return "notas/formNotas";
	    }
	    Long idAlumno = nota.getAlumno().getId();
	    serviceNotas.guardar(nota);
	    //Long idAlumno = nota.getAlumno().getId();
	    attributes.addFlashAttribute("msg", "Los datos de la nota fueron guardados!");
	    return "redirect:/notas/alumno/" + idAlumno;
	}

	 
	@GetMapping("/edit/{id}")
	public String editar(@PathVariable("id") int idNota, Model model) {
	    Nota nota = serviceNotas.buscarPorId(idNota);
	    
	
	    Curso cursoDelAlumno = nota.getAlumno().getCurso();


	    List<Asignatura> asignaturasDelCurso = cursoDelAlumno.getAsignaturas();

	    model.addAttribute("nota", nota);
	    model.addAttribute("asignaturas", asignaturasDelCurso);

	    return "notas/formNotas";
	}

	    @GetMapping("/delete/{id}")
	    public String eliminar(@PathVariable("id") int idNota, RedirectAttributes attributes) {
	    	Nota nota = serviceNotas.buscarPorId(idNota);
	        Long idAlumno = nota.getAlumno().getId();
	    	serviceNotas.eliminar(idNota);
	        attributes.addFlashAttribute("msg", "La nota fue eliminada!.");
	        return "redirect:/notas/alumno/" + idAlumno;
	    }
	    
	    @GetMapping("/borrarRegistrosNotas")
	    public String borrarRegistrosNotas(RedirectAttributes attributes) {
	    	serviceNotas.borrarRegistrosNotas();
	    	attributes.addFlashAttribute("msg", "Información sobre las notas ha sido eliminada");
	        return "redirect:/";
	    }
}
