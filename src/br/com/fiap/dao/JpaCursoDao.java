package br.com.fiap.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import br.com.fiap.entidades.Aluno;
import br.com.fiap.entidades.Curso;

@Repository
public class JpaCursoDao {

	@PersistenceContext
	private EntityManager manager;

	@SuppressWarnings("unchecked")
	public List<Curso> lista() {
		return manager.createQuery("SELECT c FROM Curso c").getResultList();
	}

	public void remove(Curso curso) {
		Curso cursoARemover = buscaPorId(curso.getId());
		manager.remove(cursoARemover);
	}

	public Curso buscaPorId(int id) {
		return manager.find(Curso.class, id);
	}


	public void adiciona(Curso c) {
		manager.persist(c);

	}

	public void altera(Curso c) {
		manager.merge(c);

	}
	
	@SuppressWarnings("unchecked")
	public List<Curso> buscaCursoAvaliacao(Aluno aluno, Curso curso) {
		
		Query query = manager.createQuery("SELECT c FROM Curso c inner join c.avaliacoes av where av.aluno = :idaluno and av.curso = :idcurso");
		query.setParameter("idaluno", aluno);
		query.setParameter("idcurso", curso);
		return query.getResultList();
	}

}
