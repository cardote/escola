package br.com.fiap.app;

import javax.swing.JOptionPane;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import br.com.fiap.controller.AlunoController;
import br.com.fiap.controller.AvaliacaoController;
import br.com.fiap.controller.CursoController;
import br.com.fiap.controller.EscolaController;
import br.com.fiap.dialog.AlunoDialog;
import br.com.fiap.dialog.CursoDialog;
import br.com.fiap.dialog.EscolaDialog;

public class App {

	private static EscolaController escolaController;
	private static CursoController cursoController;
	private static AlunoController alunoController;
	private static AvaliacaoController avaliacaoController;
	private static ApplicationContext context;

	public static void main(String[] args) {

		context = new ClassPathXmlApplicationContext("spring-context.xml");
		escolaController = context.getBean(EscolaController.class);
		cursoController = context.getBean(CursoController.class);
		alunoController = context.getBean(AlunoController.class);
		avaliacaoController = context.getBean(AvaliacaoController.class);
		menu();
		/*
		 * List<Curso> cursos;
		 * 
		 * cursos = cursoController.lista();
		 * 
		 * for (int i = 0; i < cursos.size(); i++) {
		 * 
		 * // if(cursos.get(i).getAvaliacoes(). != aluno.getId()) {
		 * System.out.println(cursos.get(i).getAvaliacoes()); for (Avaliacao avaliacao :
		 * cursos.get(i).getAvaliacoes()) { System.out.println(avaliacao.getId() + " - "
		 * + avaliacao.getNota()); } // msg += cursos.get(i).getId() + " - " +
		 * cursos.get(i).getNome() + "\n"; // } }
		 */

	}

	private static void menu() {
		String opMenu = "";

		while (!opMenu.equals(null) && !opMenu.equals("0")) {
			opMenu = JOptionPane.showInputDialog("||----------MENU----------||\n\n" + "\t 1 - Menu Aluno\n"
					+ "\t 2 - Menu Administrador\n" + "\t 0 - Sair\n");

			switch (opMenu) {
			case "1":
				AlunoDialog.menuAluno(alunoController, cursoController, avaliacaoController);
				break;
			case "2":
				menuAdmin();
				break;
			default:
				System.exit(0);
				break;
			}

		}
	}

	private static void menuAdmin() {
		String opMenu = "";

		while (!opMenu.equals(null) && !opMenu.equals("0")) {
			opMenu = JOptionPane.showInputDialog("||----------MENU ADMINISTRADOR----------||\n\n" + "\t 1 - Escola\n"
					+ "\t 2 - Curso\n" + "\t 3 - Aluno\n" + "\t 0 - Voltar\n");

			switch (opMenu) {
			case "1":
				EscolaDialog.menuEscola(escolaController);
				break;
			case "2":
				CursoDialog.menuCurso(cursoController, escolaController);
				break;
			case "3":
				AlunoDialog.menuAlunoAdmin(alunoController, avaliacaoController, cursoController);
				break;
			default:
				break;
			}

		}
	}

}
