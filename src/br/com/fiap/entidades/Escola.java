package br.com.fiap.entidades;


import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "escola")
public class Escola {
	
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	@Column(name="nome")
	private String nome;
	
	@Column(name="endereco", length=45)
	private String endereco;
	
	
	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY,
			mappedBy="escola")
	private List<Curso> cursos = new ArrayList<>();
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getEndereco() {
		return endereco;
	}
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public List<Curso> getCursos() {
		return cursos;
	}
	public void setCursos(List<Curso> cursos) {
		this.cursos = cursos;
	}
	
	@Override
	public String toString() {
		return "\n"+this.getId()+ " - " +this.getNome() + " - " + this.getEndereco() + "\n";
	}
}



