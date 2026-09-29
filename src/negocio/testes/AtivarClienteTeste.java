package negocio.testes;

import static negocio.testes.SimuladorMenu.*;

import org.junit.Test;

/**
 * Funcionalidade: 4 - Ativar Cliente
 *
 * Historia:
 *   Como administrador do sistema bancario
 *   Quero ativar um cliente cadastrado
 *   Para permitir que o cliente fique com a situacao ativa no sistema.
 *
 * Codigo relacionado: opcao 3 do menu (Main) -> GerenciadoraClientes.pesquisaCliente(int)
 * + Cliente.setAtivo(true)
 *
 * Observacao: na carga inicial os clientes 1 e 2 comecam ATIVOS. Para obter um
 * cliente inativo, os testes o desativam antes pela opcao 4 do menu.
 */
public class AtivarClienteTeste {

    /** Cenario 1: Ativar cliente inativo */
    @Test
    public void cenario1_ativarClienteInativo() {
        // Dado que existe um cliente cadastrado com o ID informado
        // E que o cliente esta inativo (desativa o cliente 1 e confere pela consulta)
        // Quando for solicitada a ativacao do cliente pelo seu ID
        String saida = executa(
                DESATIVAR_CLIENTE, "1",
                CONSULTAR_CLIENTE, "1",
                ATIVAR_CLIENTE, "1",
                CONSULTAR_CLIENTE, "1",
                SAIR);

        // (confirma o "Dado": antes da ativacao o cliente estava inativo)
        assertContemAntes(saida, "Cliente ativado com sucesso!", "Status: Inativo");
        // Entao o sistema devera alterar sua situacao para ativo
        assertContemApos(saida, "Cliente ativado com sucesso!", "Status: Ativo");
        // E devera informar que o cliente foi ativado com sucesso
        assertContem(saida, "Cliente ativado com sucesso!");
    }

    /** Cenario 2: Tentar ativar um cliente inexistente */
    @Test
    public void cenario2_tentarAtivarClienteInexistente() {
        // Dado que nao existe um cliente cadastrado com o ID informado (99)
        // Quando for solicitada a ativacao utilizando esse ID
        String saida = executa(
                ATIVAR_CLIENTE, "99",
                CONSULTAR_CLIENTE, "1",
                CONSULTAR_CLIENTE, "2",
                SAIR);

        // Entao o sistema devera informar que nao existe cliente cadastrado com esse ID
        assertContem(saida, "Cliente não encontrado!");
        // E nao sera possivel alterar nada
        assertNaoContem(saida, "Cliente ativado com sucesso!");
        assertNaoContem(saida, "Status: Inativo");
        assertQuantidade(saida, "Status: Ativo", 2); // clientes 1 e 2 continuam como estavam
    }

    /**
     * Cenario 3: Ativar um cliente que ja esta ativo
     * (redigido conforme o comportamento real do sistema: nao ha aviso de
     * "nenhuma alteracao"; o sistema repete a mensagem de sucesso)
     */
    @Test
    public void cenario3_ativarClienteQueJaEstaAtivo() {
        // Dado que existe um cliente cadastrado com o ID informado
        // E que o cliente esta ativo (cliente 1 na carga inicial; a consulta confirma)
        // Quando for solicitada a ativacao do cliente pelo seu ID
        String saida = executa(
                CONSULTAR_CLIENTE, "1",
                ATIVAR_CLIENTE, "1",
                CONSULTAR_CLIENTE, "1",
                SAIR);

        // (confirma o "Dado": antes da ativacao o cliente ja estava ativo)
        assertContemAntes(saida, "Cliente ativado com sucesso!", "Status: Ativo");
        // Entao o sistema devera manter sua situacao ativa
        assertContemApos(saida, "Cliente ativado com sucesso!", "Status: Ativo");
        assertNaoContem(saida, "Status: Inativo");
        // E devera informar que o cliente foi ativado com sucesso
        assertQuantidade(saida, "Cliente ativado com sucesso!", 1);
    }

    /** Cenario 4: Informar um ID invalido */
    @Test
    public void cenario4_ativarClienteComIdInvalido() {
        // Dado que o usuario esta realizando a ativacao de um cliente
        // (cliente 1 desativado antes, para provar que a ativacao nao acontece)
        // Quando informar um valor que nao seja um numero inteiro como o ID
        String saida = executa(
                DESATIVAR_CLIENTE, "1",
                ATIVAR_CLIENTE, "abc",
                CONSULTAR_CLIENTE, "1",
                SAIR);

        // Entao o sistema devera informar que deve ser digitado um numero valido
        assertContem(saida, "Digite um número válido.");
        // E nao devera realizar a ativacao
        assertNaoContem(saida, "Cliente ativado com sucesso!");
        assertContemApos(saida, "Digite um número válido.", "Status: Inativo");
        assertNaoContem(saida, "Status: Ativo");
    }

    /** Cenario 4 (variacoes): outros valores que nao sao inteiros validos */
    @Test
    public void cenario4_variacoesDeIdInvalido() {
        String[] invalidos = { "", "1.5", " 1", "1a", "-", "99999999999" };

        for (String invalido : invalidos) {
            String saida = executa(
                    DESATIVAR_CLIENTE, "1",
                    ATIVAR_CLIENTE, invalido,
                    CONSULTAR_CLIENTE, "1",
                    SAIR);

            assertContem(saida, "Digite um número válido.");
            assertNaoContem(saida, "Cliente ativado com sucesso!");
            assertContemApos(saida, "Digite um número válido.", "Status: Inativo");
        }
    }
}
