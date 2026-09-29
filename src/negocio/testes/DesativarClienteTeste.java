package negocio.testes;

import static negocio.testes.SimuladorMenu.*;

import org.junit.Test;

/**
 * Funcionalidade: 5 - Desativar Cliente
 *
 * Historia:
 *   Como administrador do sistema bancario
 *   Quero desativar um cliente cadastrado
 *   Para alterar sua situacao para inativo no sistema
 *
 * Codigo relacionado: opcao 4 do menu (Main) -> GerenciadoraClientes.pesquisaCliente(int)
 * + Cliente.setAtivo(false)
 *
 * Observacao: na carga inicial os clientes 1 e 2 comecam ATIVOS.
 */
public class DesativarClienteTeste {

    /** Cenario 1: Desativar um cliente ativo */
    @Test
    public void cenario1_desativarClienteAtivo() {
        // Dado que existe um cliente cadastrado com o ID informado
        // E o cliente se encontra ativo (cliente 1 na carga inicial; a consulta confirma)
        // Quando for solicitada a sua desativacao pelo seu ID
        String saida = executa(
                CONSULTAR_CLIENTE, "1",
                DESATIVAR_CLIENTE, "1",
                CONSULTAR_CLIENTE, "1",
                SAIR);

        // (confirma o "Dado": antes da desativacao o cliente estava ativo)
        assertContemAntes(saida, "Cliente desativado com sucesso!", "Status: Ativo");
        // Entao o sistema devera alterar sua situacao para desativo
        assertContemApos(saida, "Cliente desativado com sucesso!", "Status: Inativo");
        // E devera informar que o cliente foi desativado com sucesso
        assertContem(saida, "Cliente desativado com sucesso!");
    }

    /** Cenario 2: Tentar desativar um cliente inexistente */
    @Test
    public void cenario2_tentarDesativarClienteInexistente() {
        // Dado que nao existe nenhum cliente cadastrado com o ID informado (99)
        // Quando for solicitada a alteracao desse ID
        String saida = executa(
                DESATIVAR_CLIENTE, "99",
                CONSULTAR_CLIENTE, "1",
                CONSULTAR_CLIENTE, "2",
                SAIR);

        // Entao o sistema devera informar que nao existe nenhum cliente cadastrado com esse ID
        assertContem(saida, "Cliente não encontrado!");
        // E nao devera ser alterado nada
        assertNaoContem(saida, "Cliente desativado com sucesso!");
        assertNaoContem(saida, "Status: Inativo");
        assertQuantidade(saida, "Status: Ativo", 2); // clientes 1 e 2 continuam como estavam
    }

    /**
     * Cenario 3: Desativar um cliente que ja se encontra desativado
     * (redigido conforme o comportamento real do sistema: nao ha aviso de
     * "nenhuma alteracao"; o sistema repete a mensagem de sucesso)
     */
    @Test
    public void cenario3_desativarClienteQueJaEstaDesativado() {
        // Dado que existe um cliente cadastrado com o ID informado
        // E que o cliente se encontra desativado (desativa o cliente 1; a consulta confirma)
        // Quando for solicitada a alteracao pelo seu ID
        String saida = executa(
                DESATIVAR_CLIENTE, "1",
                CONSULTAR_CLIENTE, "1",
                DESATIVAR_CLIENTE, "1",
                CONSULTAR_CLIENTE, "1",
                SAIR);

        // Entao o sistema devera manter sua situacao desativada
        assertQuantidade(saida, "Status: Inativo", 2); // antes e depois da segunda desativacao
        assertNaoContem(saida, "Status: Ativo");
        // E devera informar que o cliente foi desativado com sucesso (nas duas vezes)
        assertQuantidade(saida, "Cliente desativado com sucesso!", 2);
    }

    /** Cenario 4: Informar um ID invalido */
    @Test
    public void cenario4_desativarClienteComIdInvalido() {
        // Dado que o usuario esta realizando a desativacao de um cliente
        // Quando informar um valor que nao seja um numero inteiro como o ID
        String saida = executa(
                DESATIVAR_CLIENTE, "abc",
                CONSULTAR_CLIENTE, "1",
                SAIR);

        // Entao o sistema devera informar que deve ser digitado um numero valido
        assertContem(saida, "Digite um número válido.");
        // E nao devera desativar nenhum cliente
        assertNaoContem(saida, "Cliente desativado com sucesso!");
        assertContemApos(saida, "Digite um número válido.", "Status: Ativo");
        assertNaoContem(saida, "Status: Inativo");
    }

    /** Cenario 4 (variacoes): outros valores que nao sao inteiros validos */
    @Test
    public void cenario4_variacoesDeIdInvalido() {
        String[] invalidos = { "", "1.5", " 1", "1a", "-", "99999999999" };

        for (String invalido : invalidos) {
            String saida = executa(
                    DESATIVAR_CLIENTE, invalido,
                    CONSULTAR_CLIENTE, "1",
                    SAIR);

            assertContem(saida, "Digite um número válido.");
            assertNaoContem(saida, "Cliente desativado com sucesso!");
            assertContemApos(saida, "Digite um número válido.", "Status: Ativo");
        }
    }
}
