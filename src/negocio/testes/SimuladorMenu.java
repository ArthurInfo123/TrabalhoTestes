package negocio.testes;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;

import negocio.Main;

public class SimuladorMenu {

    public static final String CONSULTAR_CLIENTE = "1";
    public static final String CONSULTAR_CONTA = "2";
    public static final String ATIVAR_CLIENTE = "3";
    public static final String DESATIVAR_CLIENTE = "4";
    public static final String SAIR = "5";

    public static String executa(String... teclas) {
        InputStream entradaOriginal = System.in;
        PrintStream saidaOriginal = System.out;
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();

        try {
            String entrada = String.join("\n", teclas) + "\n";
            System.setIn(new ByteArrayInputStream(entrada.getBytes(StandardCharsets.UTF_8)));
            System.setOut(new PrintStream(buffer, true, "UTF-8"));

            Main.main(new String[0]);
        } catch (UnsupportedEncodingException e) {
            throw new IllegalStateException(e);
        } finally {
            System.setIn(entradaOriginal);
            System.setOut(saidaOriginal);
        }

        return new String(buffer.toByteArray(), StandardCharsets.UTF_8);
    }

    public static int conta(String texto, String trecho) {
        int total = 0;
        int posicao = texto.indexOf(trecho);
        while (posicao >= 0) {
            total++;
            posicao = texto.indexOf(trecho, posicao + trecho.length());
        }
        return total;
    }

    public static void assertContem(String saida, String trecho) {
        assertTrue("Esperava encontrar \"" + trecho + "\" na saida, que foi:\n" + saida,
                saida.contains(trecho));
    }

    public static void assertNaoContem(String saida, String trecho) {
        assertFalse("Nao esperava encontrar \"" + trecho + "\" na saida, que foi:\n" + saida,
                saida.contains(trecho));
    }

    public static void assertQuantidade(String saida, String trecho, int esperado) {
        assertEquals("Quantidade de \"" + trecho + "\" na saida:\n" + saida,
                esperado, conta(saida, trecho));
    }

    public static void assertContemAntes(String saida, String marcador, String trecho) {
        int posicao = saida.indexOf(marcador);
        assertTrue("Marcador \"" + marcador + "\" nao encontrado na saida:\n" + saida, posicao >= 0);
        assertTrue("Esperava \"" + trecho + "\" antes de \"" + marcador + "\". Saida:\n" + saida,
                saida.substring(0, posicao).contains(trecho));
    }

    public static void assertContemApos(String saida, String marcador, String trecho) {
        int posicao = saida.indexOf(marcador);
        assertTrue("Marcador \"" + marcador + "\" nao encontrado na saida:\n" + saida, posicao >= 0);
        assertTrue("Esperava \"" + trecho + "\" depois de \"" + marcador + "\". Saida:\n" + saida,
                saida.indexOf(trecho, posicao) >= 0);
    }
}
