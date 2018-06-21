package br.com.fiap.dialog;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

import javax.persistence.NoResultException;
import javax.swing.JOptionPane;

import org.springframework.dao.DataIntegrityViolationException;

import br.com.fiap.controller.AlunoController;
import br.com.fiap.controller.AvaliacaoController;
import br.com.fiap.controller.CursoController;
import br.com.fiap.entidades.Aluno;
import br.com.fiap.entidades.Avaliacao;
import br.com.fiap.entidades.Curso;

public class AlunoDialog {
	
	public static void menuAluno(AlunoController alunoController, CursoController cursoController, AvaliacaoController avaliacaoController) {
		String opAluno = "";
		
		try {
			Aluno aluno = alunoController.buscarPorMatricula(Integer.parseInt(JOptionPane.showInputDialog("Informe sua matricula: ")));
			
			while (!opAluno.equals(null) && !opAluno.equals("0")) {
				opAluno = JOptionPane.showInputDialog("||----------MENU ALUNO----------||\n\n"
						+ "\t 1 - Listar Cursos\n"
						+ "\t 2 - Minhas Notas\n"
						+ "\t 0 - Voltar\n");

				switch (opAluno) {
				case "1":
					meusCursos(aluno, alunoController, cursoController);
					break;
				case "2":
					minhasNotas(aluno, cursoController, avaliacaoController);
					break;
				default:
					break;
				}
			}
				
		}catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Aluno n�o encontrado", "Error", JOptionPane.ERROR_MESSAGE);
		}
		
		
	}
	
	public static void menuAlunoAdmin(AlunoController alunoController, AvaliacaoController avaliacaoController, CursoController cursoController) {
		String opAluno = "";
		
		while (!opAluno.equals(null) && !opAluno.equals("0")) {
			opAluno = JOptionPane.showInputDialog("||----------MENU ALUNO ADMIN----------||\n\n"
					+ "\t 1 - Cadastrar Aluno\n"
					+ "\t 2 - Listar Aluno\n" 
					+ "\t 3 - Excluir Aluno\n" 
					+ "\t 4 - Dar Nota\n" 
					+ "\t 0 - Voltar\n");

			switch (opAluno) {
			case "1":
				cadastrarAluno(alunoController, cursoController);
				break;
			case "2": 
				listarAlunos(alunoController);
				break;
			case "3":
				excluirAluno(alunoController);
				break;
			case "4": 
				darNota(alunoController, avaliacaoController, cursoController);
				break;
			default:
				break;
			}
		}
	}
	
	private static void cadastrarAluno(AlunoController alunoController, CursoController cursoController) {
		String nome, endereco;
		int matricula = 0;
		Curso curso;
		List<Curso> cursos = cursoController.lista();
		
		if (!cursos.isEmpty()) {
			Aluno aluno = new Aluno();
			try {
				do {
					
					nome = JOptionPane.showInputDialog("Nome do Aluno: ");
					matricula = Integer.parseInt(JOptionPane.showInputDialog("N�mero da matricula: "));
					endereco = JOptionPane.showInputDialog("Endere�o do Aluno: ");
					
					
					aluno.setNome(nome);
					aluno.setMatricula(matricula);
					aluno.setEndereco(endereco);
					
					curso = cursoController.buscar(Integer.parseInt(JOptionPane.showInputDialog(cursos + "\nSelecione o curso inicial do aluno:")));
					curso.getAlunos().add(aluno);
					aluno.getCursos().add(curso);
					
				} while(nome.equals("") || endereco.equals("") || matricula == 0);
				
				alunoController.adicionar(aluno);
				JOptionPane.showMessageDialog(null, "Aluno adicionado com sucesso", "Sucesso",
						JOptionPane.INFORMATION_MESSAGE);
				
				
			} catch (NumberFormatException e) {
				JOptionPane.showMessageDialog(null, "Matricula invalida", "Error", JOptionPane.ERROR_MESSAGE);
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				e.printStackTrace();
			}
		} else {
			
		}
		
		
		
	}
	
	private static void listarAlunos(AlunoController alunoController) {
		JOptionPane.showMessageDialog(null, alunoController.lista());
	}
	
	private static void excluirAluno(AlunoController alunoController) {
		String id;

		do {
			id = JOptionPane.showInputDialog("Id do aluno para excluir:");
		} while (id.equals(""));

		Aluno aluno = alunoController.buscar(Integer.parseInt(id));

		if (aluno != null) {
			try {
				alunoController.remover(aluno);
				JOptionPane.showMessageDialog(null, "Aluno removido com sucesso", "Sucesso",
						JOptionPane.INFORMATION_MESSAGE);
			} catch (Exception e) {
				e.printStackTrace();
				JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			}
		} else {
			JOptionPane.showMessageDialog(null, "Aluno n�o encontrado", "Error", JOptionPane.ERROR_MESSAGE);
		}

	}
	
	private static void darNota(AlunoController alunoController, AvaliacaoController avaliacaoController, CursoController cursoController) {
		int nota = -1;
		Curso curso = null;
		Aluno aluno;
		
		Avaliacao avaliacao = new Avaliacao();
		
	
			try {
				do {
					aluno = alunoController.buscarPorMatricula(Integer.parseInt(JOptionPane.showInputDialog("Informe a matricula do aluno: ")));
					
					if (!aluno.getCursos().isEmpty()) {
						curso = cursoController.buscar(Integer.parseInt(JOptionPane.showInputDialog(aluno.getCursos() + "\nSelecione o curso para dar a nota:")));
						nota = Integer.parseInt(JOptionPane.showInputDialog("Forne�a a nota de 0 � 10: "));
					} else {
						JOptionPane.showMessageDialog(null, "O aluno n�o est� cadastrado em nenhum curso", "Error", JOptionPane.ERROR_MESSAGE);
					}
					
					
					
				} while (nota == -1);
				
				avaliacao.setNota(nota);
				avaliacao.setCurso(curso);
				
				avaliacaoController.adicionar(avaliacao);
				JOptionPane.showMessageDialog(null, "Nota adicionada com sucesso", "Sucesso",
						JOptionPane.INFORMATION_MESSAGE);
			} catch (NumberFormatException e) {
				JOptionPane.showMessageDialog(null, "Nota invalida", "Error", JOptionPane.ERROR_MESSAGE);
			} catch (NoResultException e) {
				JOptionPane.showMessageDialog(null, "Informa��o n�o encontrada", "Error", JOptionPane.ERROR_MESSAGE);
			} catch (DataIntegrityViolationException  e) {
				JOptionPane.showMessageDialog(null, "J� existe uma nota para essa avalia��o", "Error", JOptionPane.ERROR_MESSAGE);
			} catch (Exception e) {
				
				JOptionPane.showMessageDialog(null, "Curso n�o encontrado", "Error", JOptionPane.ERROR_MESSAGE);
			}
		
	}

	private static void meusCursos(Aluno aluno, AlunoController alunoController, CursoController cursoController) {
		
		JOptionPane.showMessageDialog(null, "Nome: "+aluno.getNome() + "\n" + aluno.getCursos());
			
	}

	private static void minhasNotas(Aluno aluno, CursoController cursoController, AvaliacaoController avaliacaoController) {
		Curso curso;
		
		try {
			curso = cursoController.buscar(Integer.parseInt(JOptionPane.showInputDialog(aluno.getCursos() + "\nSelecione qual curso voc� deseja saber a nota:")));
			JOptionPane.showMessageDialog(null, "Sua nota �: " + avaliacaoController.buscarNota(curso.getId()).getNota(), "Notas", JOptionPane.INFORMATION_MESSAGE);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Curso n�o encontrado", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}
}
