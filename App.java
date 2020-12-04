package Exercicio_BD_IG;

import java.util.Scanner;

public class App {
	
	static BancoConsole bd = new BancoConsole();

	static Scanner ler = new Scanner(System.in); 

	public static void main(String[] args) {

		int opcao = 0;
		do {
			System.out.println("\n*** Seletor de Opções ***");
			System.out.println();
			System.out.println("1 - Inserir usuário");
			System.out.println("2 - Consultar usuário");
			System.out.println("3 - Consultar todos usuários");
			System.out.println("4 - Alterar usuário");
			System.out.println("5 - Excluir usuário");
			System.out.println("0 - Finalizar");
			
			try {
				System.out.print("\nOpção: ");
				opcao = ler.nextInt();
			} catch (Exception e) {
				System.out.println("\nA opção deve ser numérica");
				ler.nextLine();
				continue;
			}

			switch (opcao) {
			case 1:
				ler.nextLine();
				System.out.println("Nome do usuario: ");
				String nome = ler.nextLine();
				System.out.println("e-Mail do usuario: ");
				String email = ler.nextLine();
				System.out.println("Renda familiar: ");
				float renda = ler.nextFloat();
				System.out.println("Quantidade de pessoas na família: ");
				int qtde = ler.nextInt();
				Pessoa pessoa = new Pessoa(nome, email, renda, qtde);
				bd.inserirUsuario(pessoa);
				bd.consultarId(pessoa);
				break;
			case 2:
				System.out.println("Id para consultar: ");
				int id = ler.nextInt();
				pessoa = new Pessoa(id);
				bd.consultarUsuario(pessoa);
				break;
			case 3:
				bd.listarPessoas();
				break;
			case 4:
				System.out.println("Id para alterar: ");
				id = ler.nextInt();
				
				ler.nextLine();
				System.out.println("Nome do usuario: ");
				nome = ler.nextLine();
				System.out.println("e-Mail do usuario: ");
				email = ler.nextLine();
				System.out.println("Renda familiar: ");
				renda = ler.nextFloat();
				System.out.println("Quantidade de pessoas na família: ");
				qtde = ler.nextInt();
				pessoa = new Pessoa(id, nome, email, renda, qtde);
				bd.alterarUsuario(pessoa);
				break;
			case 5:
				System.out.println("Id para excluir: ");
				id = ler.nextInt();
				pessoa = new Pessoa(id);
				bd.excluirUsuario(pessoa);
				break;
			case 0:
				break;
			default:
				System.out.printf("\nOpção incorreta!!!\n");
			}
			if (opcao == 0) {
				System.out.println("\n--- PROGRAMA FINALIZADO  ---");
				break;
			}				
		} while (true);

	}

}
