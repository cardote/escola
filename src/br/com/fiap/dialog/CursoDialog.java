package br.com.fiap.dialog;

import javax.swing.JOptionPane;

import br.com.fiap.controller.CursoController;
import br.com.fiap.controller.EscolaController;
import br.com.fiap.entidades.Curso;
import br.com.fiap.entidades.Escola;

public class CursoDialog {

	public static void menuCurso(CursoController cursoController, EscolaController escolaController) {
		String opCurso = "";
		while (!opCurso.equals(null) && !opCurso.equals("0")) {
			opCurso = JOptionPane.showInputDialog("||----------MENU CURSO----------||\n\n"
					+ "\t 1 - Cadastrar Curso\n"
					+ "\t 2 - Listar Curso\n" 
					+ "\t 3 - Excluir Curso\n" 
					+ "\t 0 - Voltar\n");

			switch (opCurso) {
			case "1":
				cadastrarCurso(cursoController, escolaController);
				break;
			case "2": 
				listarCursos(cursoController);
				break;
			case "3":
				excluirCurso(cursoController);
				break;
			default:
				break;
			}
		}

	}

	private static void cadastrarCurso(CursoController cursoController, EscolaController escolaController) {
		String nome;
		Escola escola;

		Curso curso = new Curso();

		try {
			do {
				escola = escolaController.buscar(Integer
						.parseInt(JOptionPane.showInputDialog(escolaController.lista() + "\nEscolha o ID da Escola:")));
				nome = JOptionPane.showInputDialog("Nome do Curso:");

			} while (nome.equals(""));

			curso.setNome(nome);
			curso.setEscola(escola);

			cursoController.adicionar(curso);
			JOptionPane.showMessageDialog(null, "Curso adicionado com sucesso", "Sucesso",
					JOptionPane.INFORMATION_MESSAGE);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Escola não encontrada", "Error", JOptionPane.ERROR_MESSAGE);
		} 

	}
	
	private static void listarCursos(CursoController cursoController) {
		JOptionPane.showMessageDialog(null, cursoController.lista());
	}
	
	private static void excluirCurso(CursoController cursoController) {
		String id;

		do {
			id = JOptionPane.showInputDialog("Id do curso para excluir:");
		} while (id.equals(""));

		Curso curso = cursoController.buscar(Integer.parseInt(id));

		if (curso != null) {
			try {
				cursoController.remover(curso);
				JOptionPane.showMessageDialog(null, "Curso removido com sucesso", "Sucesso",
						JOptionPane.INFORMATION_MESSAGE);
			} catch (Exception e) {
				e.printStackTrace();
				JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			}
		} else {
			JOptionPane.showMessageDialog(null, "Curso não encontrado", "Error", JOptionPane.ERROR_MESSAGE);
		}

	}

}
