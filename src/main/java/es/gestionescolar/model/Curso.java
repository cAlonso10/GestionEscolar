package es.gestionescolar.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="curso")
public class Curso {
	  
	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;
	    private String nombre;
	    
	    // Relaciones
	    @ManyToMany(mappedBy = "cursos")
	    private List<Profesor> profesores;
	    
	    @ManyToMany(mappedBy = "cursos")
	    private List<Asignatura> asignaturas;
	    
	    @OneToMany(mappedBy = "curso")
	    private List<Alumno> alumnos;

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public String getNombre() {
			return nombre;
		}

		public void setNombre(String nombre) {
			this.nombre = nombre;
		}

		public List<Profesor> getProfesores() {
			return profesores;
		}

		public void setProfesores(List<Profesor> profesores) {
			this.profesores = profesores;
		}

		public List<Asignatura> getAsignaturas() {
			return asignaturas;
		}

		public void setAsignaturas(List<Asignatura> asignaturas) {
			this.asignaturas = asignaturas;
		}

		
		
		public List<Alumno> getAlumnos() {
			return alumnos;
		}

		public void setAlumnos(List<Alumno> alumnos) {
			this.alumnos = alumnos;
		}

		@Override
		public String toString() {
			return "Curso [id=" + id + ", nombre=" + nombre +"]";
		}

		
   
	    
}
