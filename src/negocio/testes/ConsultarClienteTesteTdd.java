package negocio.testes;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Test;

import negocio.Cliente;
import negocio.GerenciadoraClientes;

public class ConsultarClienteTesteTdd {
    
    @Test
    public void deveRetornarClienteQuandoIdExistir() {
        GerenciadoraClientes gerClientes = new GerenciadoraClientes();
        Cliente cliente = new Cliente(1, "João", 30, "joao@email.com", 1, true);
        
        gerClientes.adicionaCliente(cliente);
        
        Cliente clienteEncontrado = gerClientes.pesquisaCliente(1);
        
        assertNotNull(clienteEncontrado);
        assertEquals(1, clienteEncontrado.getId());
        assertEquals("João", clienteEncontrado.getNome());
    }

    @Test
    public void deveRetornarNullQuandoClienteNaoExistir() {
        GerenciadoraClientes gerClientes = new GerenciadoraClientes();
        
        Cliente clienteEncontrado = gerClientes.pesquisaCliente(99);
        
        assertNull(clienteEncontrado);
    }

    @Test(expected = IllegalArgumentException.class)
    public void deveLancarExcecaoParaIdInvalido() {
        GerenciadoraClientes gerClientes = new GerenciadoraClientes();
        
        gerClientes.pesquisaCliente(-1);
    }



}
