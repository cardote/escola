package br.com.fiap.entidades;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "aluno")
public class Aluno {
	
	@Id
    @GeneratedValue
	private int id;
	
	@Column(name = "matricula")
	private int matricula;
	
	@Column(name = "nome")
	private String nome;
	
	@Column(name = "endereco")
	private String endereco;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "aluno_curso", catalog = "dbescola", joinColumns = {
			@JoinColumn(name = "aluno_id", nullable = false, updatable = false) }, inverseJoinColumns = {
					@JoinColumn(name = "curso_id", nullable = false, updatable = false) })
	private List<Curso> cursos = new ArrayList<>();
	
	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY,
			mappedBy="aluno")
	private List<Avaliacao> avaliacoes = new ArrayList<>();
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getMatricula() {
		return matricula;
	}
	public void setMatricula(int matricula) {
		this.matricula = matricula;
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
		return "\n"+this.getId()+" - " + this.getNome() + " - " + this.getMatricula() + " - " + this.getEndereco()+"\n";
	}
	
	

}
