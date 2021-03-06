package br.com.fiap.entidades;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "curso")
public class Curso {
	
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	@Column(name="nome")
	private String nome;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="idescola")
	private Escola escola;
	
	@ManyToMany(fetch = FetchType.EAGER, mappedBy = "cursos")
	private List<Aluno> alunos = new ArrayList<>();
	
	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER,
			mappedBy="curso")
	private Set<Avaliacao> avaliacoes = new HashSet<>();

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

	public Escola getEscola() {
		return escola;
	}

	public void setEscola(Escola escola) {
		this.escola = escola;
	}
	
	
	
	public List<Aluno> getAlunos() {
		return alunos;
	}

	public void setAlunos(List<Aluno> alunos) {
		this.alunos = alunos;
	}

	public Set<Avaliacao> getAvaliacoes() {
		return avaliacoes;
	}

	public void setAvaliacoes(Set<Avaliacao> avaliacoes) {
		this.avaliacoes = avaliacoes;
	}

	@Override
	public String toString() {
		return "\n"+this.getId() + " - " + this.getNome()+"\n";
	}
	
	
	
}
