package negocio;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe de neg�cio para realizar opera��es sobre as contas do banco.
 * 
 */
public class GerenciadoraContas {

	private List<ContaCorrente> contasDoBanco = new ArrayList<>();

	public GerenciadoraContas(){

	}

	public GerenciadoraContas(List<ContaCorrente> contasDoBanco) {
		this.contasDoBanco = contasDoBanco;
	}

	/**
	 * Retorna uma lista com todas as contas do banco.
	 * @return lista com todas as contas do banco
	 */
	public List<ContaCorrente> getContasDoBanco() {
		return contasDoBanco;
	}

	/**
	 * Pesquisa por uma conta a partir do seu ID.
	 * @param idConta id da conta a ser pesquisada
	 * @return a conta pesquisada ou null, caso n�o seja encontrada
	 */
	public ContaCorrente pesquisaConta (int idConta) {

		for (ContaCorrente contaCorrente : contasDoBanco) {
			if(contaCorrente.getId() == idConta)
				return contaCorrente;
		}
		return null;
	}
	
	/**
	 * Adiciona uma nova conta � lista de contas do banco.
	 * @param novaConta nova conta a ser adicionada
	 */
	public void adicionaConta (ContaCorrente contaCorrente) {
		this.contasDoBanco.add(contaCorrente);
	}

	/**
	 * Remove conta da lista de contas do banco.
	 * @param idConta ID da conta a ser removida 
	 * @return true se a conta foi removida. False, caso contr�rio.
	 */
	public boolean removeConta (int idConta) {
		
		boolean contaRemovida = false;
		
		for (int i = 0; i < contasDoBanco.size(); i++) {
			ContaCorrente conta = contasDoBanco.get(i);
			if(conta.getId() == idConta){
				contasDoBanco.remove(i);
				break;
			}
		}
		
		return contaRemovida;
	}

	/**
	 * Informa se uma determinada conta est� ativa ou n�o.
	 * @param idConta ID da conta cujo status ser� verificado
	 * @return true se a conta est� ativa. False, caso contr�rio. 
	 */
	public boolean contaAtiva (int idConta) {
		
		boolean contaAtiva = false;
		
		for (int i = 0; i < contasDoBanco.size(); i++) {
			ContaCorrente conta = contasDoBanco.get(i);
			if(conta.getId() == idConta)
				if(conta.isAtiva()){
					contaAtiva = true;
					break;
				}
		}
		
		return contaAtiva;
	}
	
	/**
	 * Transfere um determinado valor de uma conta Origem para uma conta Destino.
	 * Caso n�o haja saldo suficiente, o valor n�o ser� transferido.
	 * 
	 * @param idContaOrigem conta que ter� o valor deduzido
	 * @param valor valor a ser transferido
	 * @param idContaDestino conta que ter� o valor acrescido
	 * @return true, se a transfer�ncia foi realizada com sucesso.
	 */
	public boolean transfereValor(int idContaOrigem, double valor, int idContaDestino) {
		// 1. Validar que el valor a transferir sea positivo
		if (valor <= 0) {
			return false;
		}

		ContaCorrente contaOrigem = pesquisaConta(idContaOrigem);
		ContaCorrente contaDestino = pesquisaConta(idContaDestino);

		// 2. Validar que ambas cuentas existan
		if (contaOrigem == null || contaDestino == null) {
			return false;
		}

		// 3. Delegar la validación del saldo y la transferencia a la cuenta de origen
		return contaOrigem.transfere(contaDestino, valor);
	}
	
}
