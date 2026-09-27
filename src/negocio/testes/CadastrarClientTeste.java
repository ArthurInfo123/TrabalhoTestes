package negocio.testes;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.*;

import negocio.Cliente;
import negocio.GerenciadoraClientes;

public class CadastrarClientTeste {

    @Test
    public void cadastrarCliente(){
        GerenciadoraClientes newGerenciadoraClientes = new GerenciadoraClientes();
        Cliente newCliente = new Cliente(3, "Arthur", 22, "atrevisan3@ucs.br", 1, true);

        newGerenciadoraClientes.adicionaCliente(newCliente);
        
        Cliente clienteEncontrado = newGerenciadoraClientes.pesquisaCliente(3);

        // Assert (Validação/Verificação)
        assertNotNull(clienteEncontrado);
        assertEquals(3, clienteEncontrado.getId());
        assertEquals("Arthur", clienteEncontrado.getNome());
    }
}