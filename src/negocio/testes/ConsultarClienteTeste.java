package negocio.testes;

import static negocio.testes.SimuladorMenu.*;

import org.junit.Test;

/**
 * Funcionalidade: 1 - Consultar Cliente
 *
 * Historia:
 *   Como usuario do sistema bancario
 *   Quero consultar um cliente pelo seu ID
 *   Para visualizar seus dados cadastrais.
 *
 * Codigo relacionado: opcao 1 do menu (Main) -> GerenciadoraClientes.pesquisaCliente(int)
 */
public class ConsultarClienteTeste {

    /** Cenario 1: Consultar cliente cadastrado */
    @Test
    public void cenario1_consultarClienteCadastrado() {
        // Dado que existe um cliente cadastrado com o ID informado (cliente 1, carga inicial)
        // Quando for solicitada a consulta utilizando o seu ID
        String saida = executa(CONSULTAR_CLIENTE, "1", SAIR);

        // Entao o sistema deve localiza-lo
        assertNaoContem(saida, "Cliente não encontrado.");
        // E deverá apresentar seus dados
        assertContem(saida, "Id: 1");
        assertContem(saida, "Nome: Maria Silva");
        assertContem(saida, "Email: mariasilva@gmail.com");
        assertContem(saida, "Idade: 31");
        assertContem(saida, "Status: Ativo");
    }

    /** Cenario 2: Consultar cliente nao cadastrado */
    @Test
    public void cenario2_consultarClienteNaoCadastrado() {
        // Dado que nao existe cliente cadastrado com o ID informado (99)
        // Quando for solicitado a consulta utilizando este ID
        String saida = executa(CONSULTAR_CLIENTE, "99", SAIR);

        // Entao o sistema nao devera localizar nenhum cliente
        assertNaoContem(saida, "Nome:");
        // E devera informar que nao existe cliente com esse ID
        assertContem(saida, "Cliente não encontrado.");
    }

    /** Cenario 3: Consultar cliente com um ID invalido */
    @Test
    public void cenario3_consultarClienteComIdInvalido() {
        // Dado que o usuario esta realizando uma consulta de cliente
        // Quando informar um valor que nao seja um numero inteiro para o ID
        String saida = executa(CONSULTAR_CLIENTE, "abc", SAIR);

        // Entao o sistema devera informar que deve ser digitado um numero valido
        assertContem(saida, "Digite um número válido.");
        // E a consulta nao devera ser realizada
        assertNaoContem(saida, "Nome:");
        assertNaoContem(saida, "Cliente não encontrado.");
    }

    /** Cenario 3 (variacoes): outros valores que nao sao inteiros validos */
    @Test
    public void cenario3_variacoesDeIdInvalido() {
        String[] invalidos = { "", "1.5", " 1", "1a", "-", "99999999999" };

        for (String invalido : invalidos) {
            String saida = executa(CONSULTAR_CLIENTE, invalido, SAIR);

            assertContem(saida, "Digite um número válido.");
            assertNaoContem(saida, "Nome:");
            assertNaoContem(saida, "Cliente não encontrado.");
        }
    }
}
