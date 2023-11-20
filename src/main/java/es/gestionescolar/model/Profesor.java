package es.gestionescolar.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="profesores")
public class Profesor {
	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;
	    private String nombre;
	    private String apellidos;
	    @DateTimeFormat(pattern = "dd-MM-yy")
	    @Column(name = "fecha_nacimiento")
	    private Date fechaNacimiento;
	    
	    @ManyToMany
	    @JoinTable(name = "Curso_Profesores",
	        joinColumns = @JoinColumn(name = "profesor_id"),
	        inverseJoinColumns = @JoinColumn(name = "curso_id"))
	    private List<Curso> cursos;
	    
	    @ManyToMany(mappedBy = "profesores")
	    private List<Asignatura> asignaturas;

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

		public String getApellidos() {
			return apellidos;
		}

		public void setApellidos(String apellidos) {
			this.apellidos = apellidos;
		}

		public Date getFechaNacimiento() {
			return fechaNacimiento;
		}

		public void setFechaNacimiento(Date fechaNacimiento) {
			this.fechaNacimiento = fechaNacimiento;
		}

		public List<Curso> getCursos() {
			return cursos;
		}

		public void setCursos(List<Curso> cursos) {
			this.cursos = cursos;
		}

		public List<Asignatura> getAsignaturas() {
			return asignaturas;
		}

		public void setAsignaturas(List<Asignatura> asignaturas) {
			this.asignaturas = asignaturas;
		}

		@Override
		public String toString() {
			return "Profesor [id=" + id + ", nombre=" + nombre + ", apellidos=" + apellidos + ", fechaNacimiento="
					+ fechaNacimiento + ", cursos=" + cursos + ", asignaturas=" + asignaturas + "]";
		}

		

		
    
    
}
