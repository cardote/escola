package br.com.fiap.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "avaliacao")
public class Avaliacao {
	
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	@Column(name="nota")
	private int nota;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="idcurso")
	private Curso curso;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getNota() {
		return nota;
	}
	public void setNota(int nota) {
		this.nota = nota;
	}
	public Curso getCurso() {
		return curso;
	}
	public void setCurso(Curso curso) {
		this.curso = curso;
	}
	
	
	

}
