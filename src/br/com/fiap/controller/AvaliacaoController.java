package br.com.fiap.controller;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.fiap.dao.JpaAvaliacaoDao;
import br.com.fiap.entidades.Avaliacao;

@Transactional
@Component
public class AvaliacaoController {

	@Autowired
	private JpaAvaliacaoDao dao;
	
	public void adicionar(Avaliacao avaliacao) {
		this.dao.adiciona(avaliacao);
	}
	
	public void remover(Avaliacao avaliacao){
		this.dao.remove(avaliacao);
	}
	
	public List<Avaliacao> lista() {
		return this.dao.lista();
	}
	
	public Avaliacao buscar(int id) {
		return this.dao.buscaPorId(id);
	}
	
	public Avaliacao buscarNota(int idcurso) {
		return this.dao.buscaPorCurso(idcurso);
	}
}
