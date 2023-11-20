package es.gestionescolar.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import es.gestionescolar.model.Nota;

public interface INotasService {
	void guardar(Nota nota);
	Nota buscarPorId(Integer idNota);	
	void eliminar(Integer idNota);
	Page<Nota> buscarTodas(Pageable page);
	Page<Nota> buscarNotasPorAlumno(Long idAlumno, Pageable page);
	public void borrarRegistrosNotas();
}
