package testes;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.IOException;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import EstatisticaFramework.DataSet;
import EstatisticaFramework.EstatisticaFramework;
import EstatisticaFramework.Roll;

public class TesteEstatisticaFramework {
    
    private EstatisticaFramework estatisticaFramework;
    private DataSet dataSet;

    @Before
    public void setUp() {
        estatisticaFramework = new EstatisticaFramework();
        try {
            dataSet = new DataSet("teste.csv");
            /* conteúdo do arquivo teste.csv:
             * 1,2,5
             * 2,2,5
             * 3,3,5
             * 4,3,5
             * 5,4,5
             */
        } catch (IOException e) {
            e.printStackTrace();
            fail("ERRO: Não foi possível ler o arquivo do conjunto de dados!");
        }
    }

    @Test
    public void testeCalcularMedia() {
        List<Double> medias = estatisticaFramework.calcularMedia(dataSet);
        assertEquals(3.0, medias.get(0), 0.001);
        assertEquals(2.8, medias.get(1), 0.001);
        assertEquals(5.0, medias.get(2), 0.001);

        double mediaCol1 = estatisticaFramework.calcularMedia(dataSet, 0);
        assertEquals(3.0, mediaCol1, 0.001);
    }

    @Test
    public void testeCalcularModa() {
        List<Double> modas = estatisticaFramework.calcularModa(dataSet);
        assertEquals(1, modas.get(0), 0.001);
        assertEquals(2, modas.get(1), 0.001);
        assertEquals(5, modas.get(2), 0.001);

        double modaCol2 = estatisticaFramework.calcularModa(dataSet, 1);
        assertEquals(2, modaCol2, 0.001);
    }

    @Test
    public void testeCalcularMediana() {
        List<Double> medianas = estatisticaFramework.calcularMediana(dataSet);
        assertEquals(3.0, medianas.get(0), 0.001);
        assertEquals(3.0, medianas.get(1), 0.001);
        assertEquals(5.0, medianas.get(2), 0.001);

        double medianaCol3 = estatisticaFramework.calcularMediana(dataSet, 2);
        assertEquals(5.0, medianaCol3, 0.001);
    }

    @Test
    public void testeCalcularVariancia() {
        List<Double> variancias = estatisticaFramework.calcularVariancia(dataSet);
        assertEquals(2.5, variancias.get(0), 0.001);
        assertEquals(0.7, variancias.get(1), 0.001);
        assertEquals(0.0, variancias.get(2), 0.001);

        double varianciaCol1 = estatisticaFramework.calcularVariancia(dataSet, 0);
        assertEquals(2.5, varianciaCol1, 0.001);
    }

    @Test
    public void testeCalcularDesvioPadrao() {
        List<Double> desvios = estatisticaFramework.calcularDesvioPadrao(dataSet);
        assertEquals(Math.sqrt(2.5), desvios.get(0), 0.001);
        assertEquals(Math.sqrt(0.7), desvios.get(1), 0.001);
        assertEquals(0.0, desvios.get(2), 0.001);

        double desvioCol2 = estatisticaFramework.calcularDesvioPadrao(dataSet, 1);
        assertEquals(Math.sqrt(0.7), desvioCol2, 0.001);
    }

    @Test
    public void testeCalcularDesvioMedio() {
        List<Double> desviosMedios = estatisticaFramework.calcularDesvioMedio(dataSet);
        assertEquals(1.2, desviosMedios.get(0), 0.001);
        assertEquals(0.64, desviosMedios.get(1), 0.001);
        assertEquals(0.0, desviosMedios.get(2), 0.001);

        double desvioMedioCol1 = estatisticaFramework.calcularDesvioMedio(dataSet, 0);
        assertEquals(1.2, desvioMedioCol1, 0.001);
    }

    @Test
    public void testeGetMax() {
        List<Double> maximos = estatisticaFramework.getMax(dataSet);
        assertEquals(5, maximos.get(0), 0.001);
        assertEquals(4, maximos.get(1), 0.001);
        assertEquals(5, maximos.get(2), 0.001);

        double maxCol1 = estatisticaFramework.getMax(dataSet, 0);
        assertEquals(5, maxCol1, 0.001);
    }

    @Test
    public void testeGetMin() {
        List<Double> minimos = estatisticaFramework.getMin(dataSet);
        assertEquals(1, minimos.get(0), 0.001);
        assertEquals(2, minimos.get(1), 0.001);
        assertEquals(5, minimos.get(2), 0.001);

        double minCol1 = estatisticaFramework.getMin(dataSet, 0);
        assertEquals(1, minCol1, 0.001);
    }

    @Test
    public void testeGerarRoll() {
        List<Roll> rolls = estatisticaFramework.gerarRoll(dataSet);
        assertNotNull(rolls);
        assertEquals(3, rolls.size());

        Roll rollCol1 = estatisticaFramework.gerarRoll(dataSet, 0);
        assertNotNull(rollCol1);
    }

    @Test
    public void testeGerarGraficoHistograma() {
        boolean result = estatisticaFramework.gerarGraficoHistograma(dataSet, "histograma");
        assertTrue(result);

        boolean resultCol2 = estatisticaFramework.gerarGraficoHistograma(dataSet, 1, "histograma_col2");
        assertTrue(resultCol2);
    }

    @Test
    public void testeGerarGraficoSetores() {
        boolean result = estatisticaFramework.gerarGraficoSetores(dataSet, "setores");
        assertTrue(result);

        boolean resultCol2 = estatisticaFramework.gerarGraficoSetores(dataSet, 1, "setores_col2");
        assertTrue(resultCol2);
    }

    @Test
    public void testeGerarGraficoPareto() {
        boolean result = estatisticaFramework.gerarGraficoPareto(dataSet, "pareto");
        assertTrue(result);

        boolean resultCol2 = estatisticaFramework.gerarGraficoPareto(dataSet, 1, "pareto_col2");
        assertTrue(resultCol2);
    }

    @Test
    public void testeGerarRelatorio() {
        boolean result = estatisticaFramework.gerarRelatorio(dataSet, "relatorio");
        assertTrue(result);

        boolean resultCol2 = estatisticaFramework.gerarRelatorio(dataSet, 1, "relatorio_col2");
        assertTrue(resultCol2);
    }

    @Test
    public void testeGetFrequencia() {
        List<List<Double>> frequencias = estatisticaFramework.getFrequencia(dataSet, 1);
        assertNotNull(frequencias);
        assertEquals(2.0, frequencias.get(0).get(1).doubleValue(), 0.001);
    }

}