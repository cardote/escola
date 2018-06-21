package br.com.fiap.controller;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.fiap.dao.JpaEscolaDao;
import br.com.fiap.entidades.Escola;

@Transactional
@Component
public class EscolaController {
	
	@Autowired
    private JpaEscolaDao dao;
	
	public void adicionar(Escola escola) throws Exception {
		this.dao.adiciona(escola);
	}
	
	public List<Escola> lista() {
		return this.dao.lista();
	}
	
	public void remover(Escola escola) throws Exception {
		this.dao.remove(escola);
	}
	
	public Escola buscar(int id) {
		return this.dao.buscaPorId(id);
	}

}
