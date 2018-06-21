package br.com.fiap.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import br.com.fiap.entidades.Escola;

@Repository
public class JpaEscolaDao  {
	
	@PersistenceContext
    private EntityManager manager;

	
	@SuppressWarnings("unchecked")
	public List<Escola> lista(){
		return manager.createQuery("SELECT e FROM Escola e").getResultList();
	}
	
	
	public void remove(Escola escola) {
		Escola escolaARemover = buscaPorId(escola.getId());
		manager.remove(escolaARemover);
	}

	
	public Escola buscaPorId(int id) {
		return manager.find(Escola.class, id);
	}

	
	public void adiciona(Escola e) {
		manager.persist(e);
		
	}

	
	public void altera(Escola e) {
		manager.merge(e);
		
	}

}
