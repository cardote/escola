package br.com.fiap.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import br.com.fiap.entidades.Aluno;

@Repository
public class JpaAlunoDao {
	
	@PersistenceContext
    private EntityManager manager;
	
	
	@SuppressWarnings("unchecked")
	public List<Aluno> lista(){
		return manager.createQuery("SELECT a FROM Aluno a").getResultList();
	}
	
	
	public void remove(Aluno aluno) {
		Aluno alunoARemover = buscaPorId(aluno.getId());
		manager.remove(alunoARemover);
	}

	
	public Aluno buscaPorId(int id) {
		return manager.find(Aluno.class, id);
	}
	
	public Aluno buscaPorMatricula(int matricula) {
		Query query = manager.createQuery("Select a FROM Aluno a WHERE a.matricula = :matricula");
		query.setParameter("matricula", matricula);
		return (Aluno) query.getSingleResult();
	}
	
	public void adicionaCurso(int idAluno, int idCurso) {
		Query query = manager.createNativeQuery("INSERT INTO aluno_curso (aluno_id, curso_id) VALUES (:aluno_id, :curso_id);");
		query.setParameter("aluno_id", idAluno);
		query.setParameter("curso_id", idCurso);
		query.executeUpdate();
	}
	
	

	
	public void adiciona(Aluno a) {
		manager.persist(a);
	
		
	}
	
	
	public void altera(Aluno a) {
		manager.merge(a);
		
	}

}
