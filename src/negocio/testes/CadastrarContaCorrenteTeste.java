package negocio.testes;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import negocio.ContaCorrente;
import negocio.GerenciadoraContas;

public class CadastrarContaCorrenteTeste {
    @Test 
    public void cadastraContaCorrent(){
        GerenciadoraContas newGerenciadosContas = new GerenciadoraContas();
        ContaCorrente contaCorrente = new ContaCorrente(4, 20, false);

        newGerenciadosContas.adicionaConta(contaCorrente);
        
        ContaCorrente contaCorrentePesquisado = newGerenciadosContas.pesquisaConta(4);

        // // Assert (Validação/Verificação)
        assertNotNull(contaCorrente);
        assertEquals(4, contaCorrentePesquisado.getId());
        // Compara o saldo numérico (o terceiro parâmetro 0.001 é o delta para double)
        assertEquals(20.0, contaCorrentePesquisado.getSaldo(), 0.001);        
        // Valida que a conta não está ativa (false)
        assertFalse(contaCorrentePesquisado.isAtiva());

    }
}
