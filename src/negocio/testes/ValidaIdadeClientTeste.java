package negocio.testes;

import org.junit.*;

import negocio.GerenciadoraClientes;
import negocio.IdadeNaoPermitidaException;

public class ValidaIdadeClientTeste {

    public void validaIdadeDentroLimite() throws IdadeNaoPermitidaException{
        GerenciadoraClientes newGerenciadoraClientes = new GerenciadoraClientes();

        newGerenciadoraClientes.validaIdade(60);
    }

    @Test(expected = IdadeNaoPermitidaException.class)
    public void validaIdadeMenor18() throws IdadeNaoPermitidaException{
        GerenciadoraClientes newGerenciadoraClientes = new GerenciadoraClientes();

        newGerenciadoraClientes.validaIdade(15);
    }

    @Test(expected = IdadeNaoPermitidaException.class)
    public void validaIdadeMaior65() throws IdadeNaoPermitidaException{
        GerenciadoraClientes newGerenciadoraClientes = new GerenciadoraClientes();

        newGerenciadoraClientes.validaIdade(66);
    }
}