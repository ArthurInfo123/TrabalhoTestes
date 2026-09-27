package negocio.testes;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import negocio.ContaCorrente;
import negocio.GerenciadoraContas;

public class TransferirValoresEntreContasTeste {
    @Test
    public void transfereDentroLimiteSaldo(){
        GerenciadoraContas newGerenciadorContas = new GerenciadoraContas();

        ContaCorrente contaCorrente1 = new ContaCorrente(1, 10, false);
        ContaCorrente contaCorrente2 = new ContaCorrente(2, 20, false);

        newGerenciadorContas.adicionaConta(contaCorrente1);
        newGerenciadorContas.adicionaConta(contaCorrente2);

        newGerenciadorContas.transfereValor(1, 5, 2);

        ContaCorrente contaCorrentePesquisado1 = newGerenciadorContas.pesquisaConta(1);

        // // Assert (Validação/Verificação)
        assertNotNull(contaCorrentePesquisado1);
        assertEquals(1, contaCorrentePesquisado1.getId());
        // Compara o saldo numérico (o terceiro parâmetro 0.001 é o delta para double)
        assertEquals(5.0, contaCorrentePesquisado1.getSaldo(), 0.001);        

        ContaCorrente contaCorrentePesquisado2 = newGerenciadorContas.pesquisaConta(2);

        // // Assert (Validação/Verificação)
        assertNotNull(contaCorrentePesquisado2);
        assertEquals(2, contaCorrentePesquisado2.getId());
        // Compara o saldo numérico (o terceiro parâmetro 0.001 é o delta para double)
        assertEquals(25, contaCorrentePesquisado2.getSaldo(), 0.001);        
    }

    @Test
    public void transfereForaLimiteSaldoConta1(){
        GerenciadoraContas newGerenciadorContas = new GerenciadoraContas();

        ContaCorrente contaCorrente1 = new ContaCorrente(1, 10, false);
        ContaCorrente contaCorrente2 = new ContaCorrente(2, 20, false);

        newGerenciadorContas.adicionaConta(contaCorrente1);
        newGerenciadorContas.adicionaConta(contaCorrente2);

        newGerenciadorContas.transfereValor(1, 11, 2);

        ContaCorrente contaCorrentePesquisado1 = newGerenciadorContas.pesquisaConta(1);

        // // Assert (Validação/Verificação)
        assertNotNull(contaCorrentePesquisado1);
        assertEquals(1, contaCorrentePesquisado1.getId());
        // Compara o saldo numérico (o terceiro parâmetro 0.001 é o delta para double)
        assertEquals(10, contaCorrentePesquisado1.getSaldo(), 0.001);        

        ContaCorrente contaCorrentePesquisado2 = newGerenciadorContas.pesquisaConta(2);

        // // Assert (Validação/Verificação)
        assertNotNull(contaCorrentePesquisado2);
        assertEquals(2, contaCorrentePesquisado2.getId());
        // Compara o saldo numérico (o terceiro parâmetro 0.001 é o delta para double)
        assertEquals(20, contaCorrentePesquisado2.getSaldo(), 0.001);        
    }

    @Test
    public void transfereForaLimiteNegativo(){
        GerenciadoraContas newGerenciadorContas = new GerenciadoraContas();

        ContaCorrente contaCorrente1 = new ContaCorrente(1, 10, false);
        ContaCorrente contaCorrente2 = new ContaCorrente(2, 20, false);

        newGerenciadorContas.adicionaConta(contaCorrente1);
        newGerenciadorContas.adicionaConta(contaCorrente2);

        newGerenciadorContas.transfereValor(1, -4, 2);

        ContaCorrente contaCorrentePesquisado1 = newGerenciadorContas.pesquisaConta(1);

        // // Assert (Validação/Verificação)
        assertNotNull(contaCorrentePesquisado1);
        assertEquals(1, contaCorrentePesquisado1.getId());
        // Compara o saldo numérico (o terceiro parâmetro 0.001 é o delta para double)
        assertEquals(10, contaCorrentePesquisado1.getSaldo(), 0.001);        

        ContaCorrente contaCorrentePesquisado2 = newGerenciadorContas.pesquisaConta(2);

        // // Assert (Validação/Verificação)
        assertNotNull(contaCorrentePesquisado2);
        assertEquals(2, contaCorrentePesquisado2.getId());
        // Compara o saldo numérico (o terceiro parâmetro 0.001 é o delta para double)
        assertEquals(20, contaCorrentePesquisado2.getSaldo(), 0.001);        
    }

    @Test
    public void transfereZero(){
        GerenciadoraContas newGerenciadorContas = new GerenciadoraContas();

        ContaCorrente contaCorrente1 = new ContaCorrente(1, 10, false);
        ContaCorrente contaCorrente2 = new ContaCorrente(2, 20, false);

        newGerenciadorContas.adicionaConta(contaCorrente1);
        newGerenciadorContas.adicionaConta(contaCorrente2);

        newGerenciadorContas.transfereValor(1, 0, 2);

        ContaCorrente contaCorrentePesquisado1 = newGerenciadorContas.pesquisaConta(1);

        // // Assert (Validação/Verificação)
        assertNotNull(contaCorrentePesquisado1);
        assertEquals(1, contaCorrentePesquisado1.getId());
        // Compara o saldo numérico (o terceiro parâmetro 0.001 é o delta para double)
        assertEquals(10, contaCorrentePesquisado1.getSaldo(), 0.001);        

        ContaCorrente contaCorrentePesquisado2 = newGerenciadorContas.pesquisaConta(2);

        // // Assert (Validação/Verificação)
        assertNotNull(contaCorrentePesquisado2);
        assertEquals(2, contaCorrentePesquisado2.getId());
        // Compara o saldo numérico (o terceiro parâmetro 0.001 é o delta para double)
        assertEquals(20, contaCorrentePesquisado2.getSaldo(), 0.001);        
    }

}
