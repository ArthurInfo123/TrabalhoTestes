package negocio;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

	  private GerenciadoraClientes gerClientes;
	  private GerenciadoraContas gerContas;
	
	public static void main(String[] args) {
		Main sistema = new Main();
		sistema.inicializaSistemaBancario(); //criando algumas contas e clientes ficticios
		sistema.iniciarLoopDoMenu();
	}

		private void iniciarLoopDoMenu() {
			Scanner sc = new Scanner(System.in);
			boolean continua = true;

			while (continua) {
				printMenu();
				String entradaOpcao = sc.nextLine();
				int opcao = 0;
				try{
					opcao = Integer.parseInt(entradaOpcao);
				}catch(NumberFormatException e){

				}
				switch (opcao) {
				case 1:
					System.out.print("Digite o ID do cliente: ");
					String entradaId = sc.nextLine(); // Lê como texto primeiro

					try {
						// Tenta converter o texto digitado para um número (ID)
						int idCliente = Integer.parseInt(entradaId);
						Cliente cliente = this.gerClientes.pesquisaCliente(idCliente);

						// Se achou o cliente
						if(cliente != null) {
							System.out.println(cliente.toString());
						}
						// Se digitou um número, mas o cliente não existe
						else {
							System.out.println("Cliente não encontrado.");
						}
					} catch (NumberFormatException e) {
						// Se digitou qualquer caractere que não seja um número inteiro
						System.out.println("Digite um número válido.");
					}

					pulalinha();
					break;

				case 2:
					System.out.print("Digite o ID da conta: ");
					// Fazendo a mesma proteção para a conta
					try {
						int idConta = Integer.parseInt(sc.nextLine());
						ContaCorrente conta = this.gerContas.pesquisaConta(idConta);
						if(conta != null)
							System.out.println(conta.toString());
						else
							System.out.println("Conta não encontrada!");
					} catch (NumberFormatException e) {
						System.out.println("Digite um número válido.");
					}
					pulalinha();
					break;

				case 3:
					System.out.print("Digite o ID do cliente: ");
					try {
						int idCliente2 = Integer.parseInt(sc.nextLine());
						Cliente cliente2 = this.gerClientes.pesquisaCliente(idCliente2);
						if(cliente2 != null){
							cliente2.setAtivo(true);
							System.out.println("Cliente ativado com sucesso!");
						} else {
							System.out.println("Cliente não encontrado!");
						}
					} catch (NumberFormatException e) {
						System.out.println("Digite um número válido.");
					}
					pulalinha();
					break;

				case 4:
					System.out.print("Digite o ID do cliente: ");
					try {
						int idCliente3 = Integer.parseInt(sc.nextLine());
						Cliente cliente3 = this.gerClientes.pesquisaCliente(idCliente3);
						if(cliente3 != null){
							cliente3.setAtivo(false);
							System.out.println("Cliente desativado com sucesso!");
						} else {
							System.out.println("Cliente não encontrado!");
						}
					} catch (NumberFormatException e) {
						System.out.println("Digite um número válido.");
					}
					pulalinha();
					break;

				case 5:
					continua = false;
					System.out.println("################# Sistema encerrado #################");
					break;

				default:
					System.out.println("Opção inválida! Por favor, escolha um número do menu.");
					pulalinha();
					break;
			}
		}
       sc.close();
}

	private void pulalinha() {
		System.out.println("\n");
	}

	/**
	 * Imprime menu de opcoes do nosso sistema banc�rio
	 */
	private void printMenu() {
		
		System.out.println("O que voce deseja fazer? \n");
		System.out.println("1) Consultar por um cliente");
		System.out.println("2) Consultar por uma conta corrente");
		System.out.println("3) Ativar um cliente");
		System.out.println("4) Desativar um cliente");
		System.out.println("5) Sair");
		System.out.println();
		
	}

	/**
	 * Metodo que cria e insere algumas contas e clientes no sistema do banco,
	 * apenas para realizacao de testes manuais atraves do metodo main acima.
	 */
	private void inicializaSistemaBancario() {
		// criando lista vazia de contas e clientes
		List<ContaCorrente> contasDoBanco = new ArrayList<>();
		List<Cliente> clientesDoBanco = new ArrayList<>();
		
		// criando e inserindo duas contas na lista de contas correntes do banco
		ContaCorrente conta01 = new ContaCorrente(1, 10, true);
		ContaCorrente conta02 = new ContaCorrente(2, 20, true);
		contasDoBanco.add(conta01);
		contasDoBanco.add(conta02);
		
		// criando dois clientes e associando as contas criadas acima a eles
		Cliente cliente01 = new Cliente(1, "Maria Silva", 31, "mariasilva@gmail.com", conta01.getId(), true);
		Cliente cliente02 = new Cliente(2, "Felipe Augusto", 34, "felipeaugusto@gmail.com", conta02.getId(), true);
		// inserindo os clientes criados na lista de clientes do banco
		clientesDoBanco.add(cliente01);
		clientesDoBanco.add(cliente02);
		
		gerClientes = new GerenciadoraClientes(clientesDoBanco);
		gerContas = new GerenciadoraContas(contasDoBanco);
		
	}

}

