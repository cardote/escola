package br.com.fiap.dialog;

import javax.swing.JOptionPane;

import br.com.fiap.controller.EscolaController;
import br.com.fiap.entidades.Escola;

public class EscolaDialog {
	
	public static void menuEscola(EscolaController escolaController) {
		String opEscola = "";
		while (!opEscola.equals(null) && !opEscola.equals("0")) {
			opEscola = JOptionPane.showInputDialog("||----------MENU ESCOLA----------||\n\n"
					+ "\t 1 - Cadastrar Escola\n"
					+ "\t 2 - Listar Escola\n"
					+ "\t 3 - Excluir Escola\n"
					+ "\t 0 - Voltar\n");
			
			switch (opEscola) {
			case "1":
				cadastrarEscola(escolaController);
				break;
			case "2":
				listarEscolas(escolaController);
				break;
			case "3":
				excluirEscola(escolaController);
			default:
				break;
			}
		}
	}

	private static void cadastrarEscola(EscolaController escolaController) {
		String nome, endereco;

		Escola escola = new Escola();

		do {
			nome = JOptionPane.showInputDialog("Nome da Escola:");
			endereco = JOptionPane.showInputDialog("Endereço da Escola:");
		} while (nome.equals("") && endereco.equals(""));

		escola.setNome(nome);
		escola.setEndereco(endereco);

		try {
			escolaController.adicionar(escola);
			JOptionPane.showMessageDialog(null, "Escola adicionada com sucesso", "Sucesso",
					JOptionPane.INFORMATION_MESSAGE);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}

	}

	private static void listarEscolas(EscolaController escolaController) {

		JOptionPane.showMessageDialog(null, escolaController.lista());

	}

	private static void excluirEscola(EscolaController escolaController) {
		String id;

		do {
			id = JOptionPane.showInputDialog("Id da escola para excluir:");
		} while (id.equals(""));

		Escola escola = escolaController.buscar(Integer.parseInt(id));

		if (escola != null) {
			try {
				escolaController.remover(escola);
				JOptionPane.showMessageDialog(null, "Escola removida com sucesso", "Sucesso",
						JOptionPane.INFORMATION_MESSAGE);
			} catch (Exception e) {
				e.printStackTrace();
				JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			}
		} else {
			JOptionPane.showMessageDialog(null, "Escola não encontrada", "Error", JOptionPane.ERROR_MESSAGE);
		}

	}

}
