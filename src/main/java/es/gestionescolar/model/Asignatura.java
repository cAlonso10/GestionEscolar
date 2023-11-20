package es.gestionescolar.model;

import java.util.List;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

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
@Table(name="asignaturas")
public class Asignatura {
	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;
	    private String nombre;
	    
	    @ManyToMany
	    @JoinTable(name = "Curso_Asignaturas",
	        joinColumns = @JoinColumn(name = "asignatura_id"),
	        inverseJoinColumns = @JoinColumn(name = "curso_id"))
	    private List<Curso> cursos;
	    
	    @ManyToMany
	    @JoinTable(name = "Asignatura_Profesores",
	        joinColumns = @JoinColumn(name = "asignatura_id"),
	        inverseJoinColumns = @JoinColumn(name = "profesor_id"))
	    private List<Profesor> profesores;

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

		public List<Curso> getCursos() {
			return cursos;
		}

		public void setCursos(List<Curso> cursos) {
			this.cursos = cursos;
		}

		
		
		public List<Profesor> getProfesores() {
			return profesores;
		}

		public void setProfesores(List<Profesor> profesores) {
			this.profesores = profesores;
		}

		@Override
		public String toString() {
			return "Asignatura [id=" + id + ", nombre=" + nombre + ", cursos=" + cursos + "]";
		}
    
    
}