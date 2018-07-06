package br.com.fiap.controller;

import java.util.List;

import javax.persistence.NoResultException;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.fiap.dao.JpaCursoDao;
import br.com.fiap.entidades.Curso;

@Transactional
@Component
public class CursoController {

	@Autowired
	private JpaCursoDao dao;
	
	public void adicionar(Curso curso){
		this.dao.adiciona(curso);
	}
	
	public void remover(Curso curso){
		this.dao.remove(curso);
	}
	
	public List<Curso> lista() {
		return this.dao.lista();
	}
	
	public Curso buscar(int id) throws NoResultException {
		return this.dao.buscaPorId(id);
	}
	
}
