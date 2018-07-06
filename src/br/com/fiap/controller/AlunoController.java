package br.com.fiap.controller;

import java.util.List;

import javax.persistence.NoResultException;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.fiap.dao.JpaAlunoDao;
import br.com.fiap.entidades.Aluno;

@Transactional
@Component
public class AlunoController {
	
	@Autowired
	private JpaAlunoDao dao;
	
	public void adicionar(Aluno aluno) throws Exception {
		this.dao.adiciona(aluno);
	}
	
	public void remover(Aluno aluno) throws Exception {
		this.dao.remove(aluno);
	}
	
	public List<Aluno> lista() {
		return this.dao.lista();
	}
	
	public Aluno buscar(int id) {
		return this.dao.buscaPorId(id);
	}
	
	public Aluno buscarPorMatricula(int matricula) throws NoResultException {
		return this.dao.buscaPorMatricula(matricula);
	}
	
	public void adicionaCurso(int idAluno, int idCurso) {
		this.dao.adicionaCurso(idAluno, idCurso);
	}
	


}
