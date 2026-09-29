package negocio.testes;

import static negocio.testes.SimuladorMenu.*;

import org.junit.Test;

/**
 * Funcionalidade: 8 - Consultar Conta Corrente
 *
 * Historia:
 *   Como usuario do sistema bancario
 *   Quero consultar uma conta corrente pelo seu ID
 *   Para visualizar os dados da conta e sua situacao no sistema
 *
 * Codigo relacionado: opcao 2 do menu (Main) -> GerenciadoraContas.pesquisaConta(int)
 */
public class ConsultarContaCorrenteTeste {

    /** Cenario 1: Consultar uma conta corrente cadastrada */
    @Test
    public void cenario1_consultarContaCorrenteCadastrada() {
        // Dado que existe uma conta corrente com o ID informado (conta 1, carga inicial)
        // Quando for solicitada a consulta utilizando o ID da conta
        String saida = executa(CONSULTAR_CONTA, "1", SAIR);

        // Entao o sistema devera localizar a conta
        assertNaoContem(saida, "Conta não encontrada!");
        // E devera apresentar suas informacoes (id, saldo e situacao)
        assertContem(saida, "Id: 1");
        assertContem(saida, "Saldo: 10.0");
        assertContem(saida, "Status: Ativa");
    }

    /** Cenario 2: Tentar consultar uma conta corrente inexistente */
    @Test
    public void cenario2_tentarConsultarContaCorrenteInexistente() {
        // Dado que nao existe conta corrente com o ID informado (99)
        // Quando for solicitada a consulta utilizando esse ID
        String saida = executa(CONSULTAR_CONTA, "99", SAIR);

        // Entao o sistema nao devera encontrar nenhuma conta
        assertNaoContem(saida, "Saldo:");
        // E devera informar que nao existe conta com o ID informado
        assertContem(saida, "Conta não encontrada!");
    }

    /** Cenario 3: Tentar consultar utilizando um ID invalido */
    @Test
    public void cenario3_consultarContaCorrenteComIdInvalido() {
        // Dado que o usuario esta tentando localizar uma conta corrente
        // Quando informar um ID que nao seja um numero inteiro
        String saida = executa(CONSULTAR_CONTA, "abc", SAIR);

        // Entao o sistema devera informar que deve ser digitado um numero valido
        assertContem(saida, "Digite um número válido.");
        // E nao devera realizar nenhuma consulta
        assertNaoContem(saida, "Saldo:");
        assertNaoContem(saida, "Conta não encontrada!");
    }

    /** Cenario 3 (variacoes): outros valores que nao sao inteiros validos */
    @Test
    public void cenario3_variacoesDeIdInvalido() {
        String[] invalidos = { "", "1.5", " 1", "1a", "-", "99999999999" };

        for (String invalido : invalidos) {
            String saida = executa(CONSULTAR_CONTA, invalido, SAIR);

            assertContem(saida, "Digite um número válido.");
            assertNaoContem(saida, "Saldo:");
            assertNaoContem(saida, "Conta não encontrada!");
        }
    }
}
