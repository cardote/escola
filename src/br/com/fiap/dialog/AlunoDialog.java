package br.com.fiap.dialog;

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

	public static void menuAluno(AlunoController alunoController, CursoController cursoController,
			AvaliacaoController avaliacaoController) {
		String opAluno = "";

		try {
			Aluno aluno = alunoController
					.buscarPorMatricula(Integer.parseInt(JOptionPane.showInputDialog("Informe sua matricula: ")));

			while (!opAluno.equals(null) && !opAluno.equals("0")) {
				opAluno = JOptionPane.showInputDialog("||----------MENU ALUNO----------||\n\n"
						+ "\t 1 - Listar Cursos\n" + "\t 2 - Minhas Notas\n" + "\t 0 - Voltar\n");

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

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Aluno não encontrado", "Error", JOptionPane.ERROR_MESSAGE);
		}

	}

	public static void menuAlunoAdmin(AlunoController alunoController, AvaliacaoController avaliacaoController,
			CursoController cursoController) {
		String opAluno = "";

		while (!opAluno.equals(null) && !opAluno.equals("0")) {
			opAluno = JOptionPane.showInputDialog("||----------MENU ALUNO ADMIN----------||\n\n"
					+ "\t 1 - Cadastrar Aluno\n" + "\t 2 - Listar Aluno\n" + "\t 3 - Excluir Aluno\n"
					+ "\t 4 - Dar Nota\n" + "\t 5 - Matricular em um curso\n" + "\t 0 - Voltar\n");

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
			case "5":
				adicionarCurso(alunoController, cursoController);
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
					matricula = Integer.parseInt(JOptionPane.showInputDialog("Número da matricula: "));
					endereco = JOptionPane.showInputDialog("Endereço do Aluno: ");

					aluno.setNome(nome);
					aluno.setMatricula(matricula);
					aluno.setEndereco(endereco);

					String msg = "\nSelecione o curso inicial do aluno: \n\n";
					for (Curso c : cursos) {
						msg += c.getId() + " - " + c.getNome() + "\n\n";

					}

					curso = cursoController.buscar(Integer.parseInt(JOptionPane.showInputDialog(msg)));
					curso.getAlunos().add(aluno);
					aluno.getCursos().add(curso);

				} while (nome.equals("") || endereco.equals("") || matricula == 0);

				alunoController.adicionar(aluno);
				JOptionPane.showMessageDialog(null, "Aluno adicionado com sucesso", "Sucesso",
						JOptionPane.INFORMATION_MESSAGE);

			} catch (NumberFormatException e) {
				JOptionPane.showMessageDialog(null, "Matricula invalida", "Error", JOptionPane.ERROR_MESSAGE);
			} catch (DataIntegrityViolationException e) {

				JOptionPane.showMessageDialog(null, "Já existe um aluno com esse numero de matricula", "Error",
						JOptionPane.ERROR_MESSAGE);
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);

			}
		} else {

		}

	}

	private static void listarAlunos(AlunoController alunoController) {
		String msg = "\n Alunos Cadastrados \n ";
		for (Aluno aluno : alunoController.lista()) {
			msg += "\n\n" + "Matricula: " + aluno.getMatricula() + "\nNome: " + aluno.getNome()
					+ "\n\n--------------------";

		}
		JOptionPane.showMessageDialog(null, msg);

	}

	private static void excluirAluno(AlunoController alunoController) {
		String matricula;

		do {
			matricula = JOptionPane.showInputDialog("matricula do aluno para excluir:");
		} while (matricula.equals(""));

		Aluno aluno = alunoController.buscarPorMatricula(Integer.parseInt(matricula));

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
			JOptionPane.showMessageDialog(null, "Aluno não encontrado", "Error", JOptionPane.ERROR_MESSAGE);
		}

	}

	private static void darNota(AlunoController alunoController, AvaliacaoController avaliacaoController,
			CursoController cursoController) {
		int nota = -1;
		Curso curso = null;
		Aluno aluno;
		List<Curso> cursos;
		boolean temCurso = false;

		Avaliacao avaliacao = new Avaliacao();

		try {
			do {
				aluno = alunoController.buscarPorMatricula(
						Integer.parseInt(JOptionPane.showInputDialog("Informe a matricula do aluno: ")));
				cursos = aluno.getCursos();

				if (!cursos.isEmpty()) {
					String msg = "\n Selecione o id do curso para dar a nota:\n\n";
					for (int i = 0; i < cursos.size(); i++) {
						for (Avaliacao ava : cursos.get(i).getAvaliacoes()) {
							if (ava.getAluno().getId() != aluno.getId()
									&& cursos.get(i).getId() != ava.getCurso().getId()) {
								msg += cursos.get(i).getId() + " - " + cursos.get(i).getNome() + "\n";
								temCurso = true;
							}
						}
					}

					if (temCurso) {
						curso = cursoController.buscar(Integer.parseInt(JOptionPane.showInputDialog(msg)));
						nota = Integer.parseInt(JOptionPane.showInputDialog("Forneça a nota de 0 à 10: "));
					} else {
						JOptionPane.showMessageDialog(null, "O aluno já possue nota em todos os cursos", "Error",
								JOptionPane.ERROR_MESSAGE);
					}

				} else {
					JOptionPane.showMessageDialog(null, "O aluno não está cadastrado em nenhum curso", "Error",
							JOptionPane.ERROR_MESSAGE);
				}

			} while (nota == -1);

			avaliacao.setNota(nota);
			avaliacao.setCurso(curso);
			avaliacao.setAluno(aluno);

			avaliacaoController.adicionar(avaliacao);
			JOptionPane.showMessageDialog(null, "Nota adicionada com sucesso", "Sucesso",
					JOptionPane.INFORMATION_MESSAGE);
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(null, "Nota invalida", "Error", JOptionPane.ERROR_MESSAGE);
		} catch (NoResultException e) {
			JOptionPane.showMessageDialog(null, "Informação não encontrada", "Error", JOptionPane.ERROR_MESSAGE);
		} catch (DataIntegrityViolationException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Já existe uma nota para essa avaliação", "Error",
					JOptionPane.ERROR_MESSAGE);
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Curso não encontrado", "Error", JOptionPane.ERROR_MESSAGE);
		}

	}

	private static void meusCursos(Aluno aluno, AlunoController alunoController, CursoController cursoController) {

		String msg = "\n Meus Cursos \n ";
		for (Curso curso : aluno.getCursos()) {
			msg += "\n\n" + "Id: " + curso.getId() + "\nNome: " + curso.getNome() + "\n\n--------------------";

		}
		JOptionPane.showMessageDialog(null, msg, aluno.getNome(), JOptionPane.INFORMATION_MESSAGE);

	}

	private static void minhasNotas(Aluno aluno, CursoController cursoController,
			AvaliacaoController avaliacaoController) {
		Curso curso;

		try {
			String msg = "\n Selecione o id do curso que você deseja saber a nota:\n\n ";
			for (Curso c : aluno.getCursos()) {
				msg += c.getId() + " - " + c.getNome() + "\n\n";

			}
			curso = cursoController.buscar(Integer.parseInt(JOptionPane.showInputDialog(msg)));
			JOptionPane.showMessageDialog(null,
					"Sua nota é: " + avaliacaoController.buscarNota(curso.getId(), aluno.getId()).getNota(), "Notas",
					JOptionPane.INFORMATION_MESSAGE);
		} catch (NoResultException e) {
			JOptionPane.showMessageDialog(null, "Você não possui nota nesse curso", "Error", JOptionPane.ERROR_MESSAGE);

		} catch (Exception e) {

			JOptionPane.showMessageDialog(null, "Curso não encontrado", "Error", JOptionPane.ERROR_MESSAGE);
		}

	}

	private static void adicionarCurso(AlunoController alunoController, CursoController cursoController) {

		Aluno aluno;
		List<Curso> cursos = cursoController.lista();
		int idCurso;

		try {
			aluno = alunoController.buscarPorMatricula(
					Integer.parseInt(JOptionPane.showInputDialog("Informe a matricula do aluno: ")));

			String msg = "\nSelecione o id do curso: \n\n";
			for (int i = 0; i < cursos.size(); i++) {

				msg += cursos.get(i).getId() + " - " + cursos.get(i).getNome() + "\n\n";

			}

			idCurso = Integer.parseInt(JOptionPane.showInputDialog(msg));
			alunoController.adicionaCurso(aluno.getId(), idCurso);
			JOptionPane.showMessageDialog(null, "Aluno matriculado com sucesso", "Sucesso",
					JOptionPane.INFORMATION_MESSAGE);

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "O aluno já está matriculado nesse curso ou esse curso não existe", "Error",
					JOptionPane.ERROR_MESSAGE);
		}
	}

}
