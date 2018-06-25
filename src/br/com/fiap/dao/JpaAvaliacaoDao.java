package br.com.fiap.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import br.com.fiap.entidades.Avaliacao;

@Repository
public class JpaAvaliacaoDao {
	
	@PersistenceContext
    private EntityManager manager;
	
	@SuppressWarnings("unchecked")
	public List<Avaliacao> lista(){
		return manager.createQuery("SELECT a FROM Avaliacao a").getResultList();
		
	}
	
	
	public void remove(Avaliacao avaliacao) {
		Avaliacao avaliacaoARemover = buscaPorId(avaliacao.getId());
		manager.remove(avaliacaoARemover);
	}

	
	public Avaliacao buscaPorId(int id) {
		return manager.find(Avaliacao.class, id);
	}
	
	public Avaliacao buscaPorCursoAluno(int idcurso, int idaluno) {
		Query query =  manager.createQuery("SELECT a FROM Avaliacao a WHERE a.curso.id = :idcurso and a.aluno.id = :idaluno");
		query.setParameter("idcurso", idcurso);
		query.setParameter("idaluno", idaluno);
		return (Avaliacao) query.getSingleResult();
	}

	
	public void adiciona(Avaliacao a) {
		manager.persist(a);
		
	}

	
	public void altera(Avaliacao a) {
		manager.merge(a);
		
	}


}
