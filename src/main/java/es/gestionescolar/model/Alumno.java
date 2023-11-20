package es.gestionescolar.model;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="alumnos")
public class Alumno {
	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;
	    private String nombre;
	    private String apellidos;
	    @DateTimeFormat(pattern = "dd-MM-yy")
	    @Column(name = "fecha_nacimiento")
	    private Date fechaNacimiento;
	    
	    @ManyToOne
	    @JoinColumn(name = "curso_id")
	    private Curso curso;
	    
	    @OneToMany(mappedBy = "alumno")
	    private List<Nota> notas;

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

		public Curso getCurso() {
			return curso;
		}

		public void setCurso(Curso curso) {
			this.curso = curso;
		}

		public List<Nota> getNotas() {
			return notas;
		}

		public void setNotas(List<Nota> notas) {
			this.notas = notas;
		}

		@Override
		public String toString() {
			return "Alumno [id=" + id + ", nombre=" + nombre + ", apellidos=" + apellidos + ", fechaNacimiento="
					+ fechaNacimiento +"]";
		}
    
    
}
