package EstatisticaFramework;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.io.*;
import java.net.*;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

/**
 * Classe do Framework de Estatisticas
 * @author João Vittor
 */
public class EstatisticaFramework {

    /**
     * Construtor da classe EstatisticaFramework
     */
    public EstatisticaFramework() {

    }

    /**
     * Método para calcular as médias dos valores de cada coluna de um DataSet.
     * @param ds DataSet que será utilizado
     * @return lista de médias dos valores de cada coluna
     */
    public List<Double> calcularMedia(DataSet ds) {
        List<Double> valores = new ArrayList<>();
        for (int i = 0; i < ds.getLargura(); i++) {
            valores.add(calcularMedia(ds, i));
        }
        return valores;
    }

    /**
     * Método para calcular a média dos valores de uma coluna de um DataSet.
     * @param ds DataSet que será utilizado
     * @param numeroColuna número (ou índice) da coluna desejada do DataSet (começa em zero)
     * @return média dos valores da coluna
     */
    public double calcularMedia(DataSet ds, int numeroColuna) {
        List<Double> coluna = ds.getColuna(numeroColuna);
        double soma = 0;

        for (Double numero : coluna) {
            soma += numero;
        }

        return soma / coluna.size();
    }

    /**
     * Método para calcular as modas dos valores de cada coluna de um DataSet.
     * @param ds DataSet que será utilizado
     * @return lista de modas dos valores de cada coluna
     */
    public List<Double> calcularModa(DataSet ds) {
        List<Double> valores = new ArrayList<>();
        for (int i = 0; i < ds.getLargura(); i++) {
            valores.add(calcularModa(ds, i));
        }
        return valores;
    }

    /**
     * Método para calcular a moda dos valores de uma coluna de um DataSet.
     * @param ds DataSet que será utilizado
     * @param numeroColuna número (ou índice) da coluna desejada do DataSet (começa em zero)
     * @return moda dos valores da coluna
     */
    public double calcularModa(DataSet ds, int numeroColuna) {
        List<List<Double>> frequencia = getFrequencia(ds, numeroColuna);

        double moda = 0;
        double quantidade = 0;
        for (List<Double> dupla : frequencia) {
            if (dupla.get(1) > quantidade) {
                quantidade = dupla.get(1);
                moda = dupla.get(0);
            }
        }
        return moda;
    }

    /**
     * Método para calcular as medianas dos valores de cada coluna de um DataSet.
     * @param ds DataSet que será utilizado
     * @return lista de medianas dos valores de cada coluna
     */
    public List<Double> calcularMediana(DataSet ds) {
        List<Double> valores = new ArrayList<>();
        for (int i = 0; i < ds.getLargura(); i++) {
            valores.add(calcularMediana(ds, i));
        }
        return valores;
    }

    /**
     * Método para calcular a mediana dos valores de uma coluna de um DataSet.
     * @param ds DataSet que será utilizado
     * @param numeroColuna número (ou índice) da coluna desejada do DataSet (começa em zero)
     * @return mediana dos valores da coluna
     */
    public double calcularMediana(DataSet ds, int numeroColuna) {
        List<Double> coluna = ds.getColuna(numeroColuna);

        Collections.sort(coluna);
        int tamanho = coluna.size();
        if (tamanho % 2 == 0) {
            return (coluna.get(tamanho / 2 - 1) + coluna.get(tamanho / 2)) / 2.0;
        } else {
            return coluna.get(tamanho / 2);
        }
    }

    /**
     * Método para calcular as variâncias dos valores de cada coluna de um DataSet.
     * @param ds DataSet que será utilizado
     * @return lista de variâncias dos valores de cada coluna
     */
    public List<Double> calcularVariancia(DataSet ds) {
        List<Double> valores = new ArrayList<>();
        for (int i = 0; i < ds.getLargura(); i++) {
            valores.add(calcularVariancia(ds, i));
        }
        return valores;
    }

    /**
     * Método para calcular a variância dos valores de uma coluna de um DataSet.
     * @param ds DataSet que será utilizado
     * @param numeroColuna número (ou índice) da coluna desejada do DataSet (começa em zero)
     * @return variância dos valores da coluna
     */
    public double calcularVariancia(DataSet ds, int numeroColuna) {
        List<Double> coluna = ds.getColuna(numeroColuna);
        double media = calcularMedia(ds, numeroColuna);
        double soma = 0;
        
        for (double numero : coluna) {
            soma += Math.pow(numero - media, 2);
        }
        
        return soma / (coluna.size() - 1);
    }

    /**
     * Método para calcular os desvios padrão de cada coluna de um DataSet.
     * @param ds DataSet que será utilizado
     * @return lista de desvios padrão dos valores de cada coluna
     */
    public List<Double> calcularDesvioPadrao(DataSet ds) {
        List<Double> valores = new ArrayList<>();
        for (int i = 0; i < ds.getLargura(); i++) {
            valores.add(calcularDesvioPadrao(ds, i));
        }
        return valores;
    }

    /**
     * Método para calcular o desvio padrão dos valores de uma coluna de um DataSet.
     * @param ds DataSet que será utilizado
     * @param numeroColuna número (ou índice) da coluna desejada do DataSet (começa em zero)
     * @return desvio padrão dos valores da coluna
     */
    public double calcularDesvioPadrao(DataSet ds, int numeroColuna) {
        double variancia = calcularVariancia(ds, numeroColuna);
        return Math.sqrt(variancia);
    }

    /**
     * Método para calcular os desvios médios dos valores de cada coluna de um DataSet.
     * @param ds DataSet que será utilizado
     * @return lista de desvios médios dos valores de cada coluna
     */
    public List<Double> calcularDesvioMedio(DataSet ds) {
        List<Double> valores = new ArrayList<>();
        for (int i = 0; i < ds.getLargura(); i++) {
            valores.add(calcularDesvioMedio(ds, i));
        }
        return valores;
    }

    /**
     * Método para calcular o desvio médio dos valores de uma coluna de um DataSet.
     * @param ds DataSet que será utilizado
     * @param numeroColuna número (ou índice) da coluna desejada do DataSet (começa em zero)
     * @return desvio médio dos valores da coluna
     */
    public double calcularDesvioMedio(DataSet ds, int numeroColuna) {
        List<Double> coluna = ds.getColuna(numeroColuna);
        double media = calcularMedia(ds, numeroColuna);
        double soma = 0;
        
        for (double numero : coluna) {
            soma += Math.abs(numero - media);
        }
        
        return soma / coluna.size();
    }

    /**
     * Método para retornar os maiores valores de cada coluna de um DataSet.
     * @param ds DataSet que será utilizado
     * @return lista de maiores valores de cada coluna
     */
    public List<Double> getMax(DataSet ds) {
        List<Double> valores = new ArrayList<>();
        for (int i = 0; i < ds.getLargura(); i++) {
            valores.add(getMax(ds, i));
        }
        return valores;
    }

    /**
     * Método para retornar o maior valor de uma coluna de um DataSet.
     * @param ds DataSet que será utilizado
     * @param numeroColuna número (ou índice) da coluna desejada do DataSet (começa em zero)
     * @return maior valor da coluna
     */
    public double getMax(DataSet ds, int numeroColuna) {
        List<Double> coluna = ds.getColuna(numeroColuna);
        double max = Double.MIN_VALUE;

        for (Double numero : coluna) {
            if (numero > max) {
                max = numero;
            }
        }

        return max;
    }

    /**
     * Método para retornar os menores valores de cada coluna de um DataSet
     * @param ds DataSet que será utilizado
     * @return lista de menores valores de cada coluna
     */
    public List<Double> getMin(DataSet ds) {
        List<Double> valores = new ArrayList<>();
        for (int i = 0; i < ds.getLargura(); i++) {
            valores.add(getMin(ds, i));
        }
        return valores;
    }

    /**
     * Método para retornar o menor valor de uma coluna de um DataSet.
     * @param ds DataSet que será utilizado
     * @param numeroColuna número (ou índice) da coluna desejada do DataSet (começa em zero)
     * @return menor valor da coluna
     */
    public double getMin(DataSet ds, int numeroColuna) {
        List<Double> coluna = ds.getColuna(numeroColuna);
        double min = Double.MAX_VALUE;

        for (Double numero : coluna) {
            if (numero < min) {
                min = numero;
            }
        }

        return min;
    }

    /**
     * Método para calcular as tabelas Roll dos valores de cada coluna de um DataSet.
     * @param ds DataSet que será utilizado
     * @return lista de Rolls dos valores de cada coluna
     */
    public List<Roll> gerarRoll(DataSet ds) {
        List<Roll> rolls = new ArrayList<>();
        for (int i = 0; i < ds.getLargura(); i++) {
            rolls.add(gerarRoll(ds, i));
        }
        return rolls;
    }

    /**
     * Método para calcular a tabela Roll dos valores de uma coluna de um DataSet.
     * @param ds DataSet que será utilizado
     * @param numeroColuna número (ou índice) da coluna desejada do DataSet (começa em zero)
     * @return Roll dos valores de uma coluna
     */
    public Roll gerarRoll(DataSet ds, int numeroColuna) {
        List<Double> coluna = ds.getColuna(numeroColuna);
        List<List<Double>> frequencia = getFrequencia(ds, numeroColuna);

        return new Roll(coluna, frequencia);
    }

    /**
     * Método para gerar gráficos de histograma, através da API quickchart, para cada coluna de um DataSet.
     * @param ds DataSet que será utilizado
     * @param arquivo caminho e nome do arquivo que será gerado (não precisa incluir extensão do arquivo)
     * @return verdadeiro se todos os gráficos foram gerados, falso caso tenho ocorrido algum erro
     */
    public boolean gerarGraficoHistograma(DataSet ds, String arquivo) {
        boolean resultado = true;
        for (int i = 0; i < ds.getLargura(); i++) {
            if (!gerarGraficoHistograma(ds, i, i + arquivo)) {
                resultado = false;
            }
        }
        return resultado;
    }

    /**
     * Método para gerar gráfico de histograma, através da API quickchart, para uma coluna de um DataSet.
     * @param ds DataSet que será utilizado
     * @param numeroColuna número (ou índice) da coluna desejada do DataSet (começa em zero)
     * @param arquivo caminho e nome do arquivo que será gerado (não precisa incluir extensão do arquivo)
     * @return verdadeiro se o gráfico foi gerado, falso caso tenha ocorrido algum erro
     */
    public boolean gerarGraficoHistograma(DataSet ds, int numeroColuna, String arquivo) {
        return gerarGrafico(ds, numeroColuna, arquivo, "bar");
    }

    /**
     * Método para gerar gráficos de setores, através da API quickchart, para cada coluna de um DataSet.
     * @param ds DataSet que será utilizado
     * @param arquivo caminho e nome do arquivo que será gerado (não precisa incluir extensão do arquivo)
     * @return verdadeiro se o gráfico foi gerado, falso caso tenha ocorrido algum erro
     */
    public boolean gerarGraficoSetores(DataSet ds, String arquivo) {
        boolean resultado = true;
        for (int i = 0; i < ds.getLargura(); i++) {
            if (!gerarGraficoSetores(ds, i, i + arquivo)) {
                resultado = false;
            }
        }
        return resultado;
    }

    /**
     * Método para gerar gráfico de setores, através da API quickchart, para uma coluna de um DataSet.
     * @param ds DataSet que será utilizado
     * @param numeroColuna número (ou índice) da coluna desejada do DataSet (começa em zero)
     * @param arquivo caminho e nome do arquivo que será gerado (não precisa incluir extensão do arquivo)
     * @return verdadeiro se o gráfico foi gerado, falso caso tenha ocorrido algum erro
     */
    public boolean gerarGraficoSetores(DataSet ds, int numeroColuna, String arquivo) {
        return gerarGrafico(ds, numeroColuna, arquivo, "pie");
    }

    /**
     * Método para gerar gráficos de pareto, através da API quickchart, para cada coluna de um DataSet.
     * @param ds DataSet que será utilizado
     * @param arquivo caminho e nome do arquivo que será gerado (não precisa incluir extensão do arquivo)
     * @return verdadeiro se o gráfico foi gerado, falso caso tenha ocorrido algum erro
     */
    public boolean gerarGraficoPareto(DataSet ds, String arquivo) {
        boolean resultado = true;
        for (int i = 0; i < ds.getLargura(); i++) {
            if (!gerarGraficoPareto(ds, i, i + arquivo)) {
                resultado = false;
            }
        }
        return resultado;
    }

    /**
     * Método para gerar gráfico de pareto, através da API quickchart, para uma coluna de um DataSet.
     * @param ds DataSet que será utilizado
     * @param numeroColuna número (ou índice) da coluna desejada do DataSet (começa em zero)
     * @param arquivo caminho e nome do arquivo que será gerado (não precisa incluir extensão do arquivo)
     * @return verdadeiro se o gráfico foi gerado, falso caso tenha ocorrido algum erro
     */
    public boolean gerarGraficoPareto(DataSet ds, int numeroColuna, String arquivo) {
        return gerarGrafico(ds, numeroColuna, arquivo, "pareto");
    }

    /**
     * Método genérico para gerar gráficos através da API quickchart.
     * O objetivo desse método é formatar os dados recebidos e enviar de forma correta para a API, de acordo com o tipo desejado de gráfico.
     * Esse método é utilizado pelos outros métodos específicos de geração de gráficos.
     * @param ds DataSet que será utilizado
     * @param numeroColuna número (ou índice) da coluna desejada do DataSet (começa em zero)
     * @param arquivo caminho e nome do arquivo que será gerado (não precisa incluir extensão do arquivo)
     * @param tipo tipo de gráfico que será gerado através da API quickchart
     * @return verdadeiro se o gráfico foi gerado, falso caso tenha ocorrido algum erro
     */
    private boolean gerarGrafico(DataSet ds, int numeroColuna, String arquivo, String tipo) {
    	Locale.setDefault(Locale.US);
    	// https://quickchart.io/
        // https://quickchart.io/documentation/reference/labels/#annotation-and-label-plugins

        List<Double> xLista = new ArrayList<>();
        List<Double> yLista = new ArrayList<>();

        List<List<Double>> frequencia = getFrequencia(ds, numeroColuna);
        for (List<Double> lista : frequencia) {
            xLista.add(lista.get(0));
            yLista.add(lista.get(1));
        }

        double[] xEntrada = new double[xLista.size()];
        for (int i = 0; i < xLista.size(); i++) {
            xEntrada[i] = xLista.get(i);
        }

        double[] yEntrada = new double[yLista.size()];
        for (int i = 0; i < yLista.size(); i++) {
            yEntrada[i] = yLista.get(i);
        }

        String[] x;
        if (isIntArray(xEntrada)) {
            x = intParaString(doubleParaInt(xEntrada));
        } else {
            x = doubleParaString(xEntrada);
        }
        String[] y;
        if (isIntArray(yEntrada)) {
            y = intParaString(doubleParaInt(yEntrada));
        } else {
            y = doubleParaString(yEntrada);
        }

        boolean pareto = false;
        String[] linhaValores = new String[yEntrada.length];
        String titulo = "Gráfico Histograma";
        if(tipo.equalsIgnoreCase("pareto")) {
            titulo = "Gráfico Pareto";
            pareto = true;
            tipo = "bar";
            double total = 0;

            Integer[] indices = new Integer[y.length];
            for (int i = 0; i < y.length; i++) {
                indices[i] = i;
            }
            Double[] yDouble = new Double[yEntrada.length];
            for (int i = 0; i < yEntrada.length; i++) {
                yDouble[i] = yEntrada[i];
            }
            Arrays.sort(indices, (i1, i2) -> yDouble[i2].compareTo(yDouble[i1]));
            String[] sortedX = new String[x.length];
            double[] sortedY = new double[y.length];
            for (int i = 0; i < indices.length; i++) {
                sortedX[i] = x[indices[i]];
                sortedY[i] = yEntrada[indices[i]];
            }
            System.arraycopy(sortedX, 0, x, 0, x.length);
            System.arraycopy(sortedY, 0, yEntrada, 0, y.length);

            for (double numero : yEntrada) {
                total += numero;
            }
            double valor = 0;
            for (int i = 0; i < yEntrada.length; i++) {
                BigDecimal bd = BigDecimal.valueOf((yEntrada[i] * 100.0) / total);
                bd.setScale(2, RoundingMode.HALF_UP);
                valor += bd.doubleValue();
                y[i] = String.format("%.1f", bd.doubleValue());
                linhaValores[i] = String.format("%.1f", valor);
            }
        } else if (tipo.equalsIgnoreCase("pie")) {
            titulo = "Gráfico Setores";
            double total = 0;
            for (double numero : yEntrada) {
                total += numero;
            }
            for (int i = 0; i < yEntrada.length; i++) {
                BigDecimal bd = BigDecimal.valueOf((yEntrada[i] * 100.0) / total);
                bd.setScale(2, RoundingMode.HALF_UP);
                y[i] = String.format("%.1f", bd.doubleValue());
            }
        }

        try {
            // QuickChart API URL
            String baseUrl = "https://quickchart.io/chart";

            // JSON
            StringBuilder chartData = new StringBuilder();
            chartData.append("{type:'" + tipo + "',data:{labels:[");
            for (int i = 0; i < x.length; i++) {
                chartData.append("'").append(x[i]).append("'");
                if (i < x.length - 1) {
                    chartData.append(",");
                }
            }
            
            // Se Pareto
            if (pareto) {
                chartData.append("],datasets:[{type:'bar',label:'Quantidade',data:[");
                for (int i = 0; i < y.length; i++) {
                    chartData.append(y[i]);
                    if (i < y.length - 1) {
                        chartData.append(",");
                    }
                }
                chartData.append("]},{type:'line',label:'Acumulado','fill':false,data:[");
                for (int i = 0; i < linhaValores.length; i++) {
                    chartData.append(linhaValores[i]);
                    if (i < linhaValores.length - 1) {
                        chartData.append(",");
                    }
                }
            } else {
                chartData.append("],datasets:[{label:'Quantidade',data:[");
                for (int i = 0; i < y.length; i++) {
                    chartData.append(y[i]);
                    if (i < y.length - 1) {
                        chartData.append(",");
                    }
                }
            }
            chartData.append("]}]},options: {title: {display: true,text: '" + titulo + "',},},}");

            // encoder
            String encodedChartData = URLEncoder.encode(chartData.toString(), "UTF-8");
            String chartUrl = baseUrl + "?c=" + encodedChartData;

            // Print URL
            System.out.println("URL: " + chartUrl);

            // Download imagem
            URI uri = new URI(chartUrl);
            BufferedImage imagem = ImageIO.read(uri.toURL());

            // Salvar imagem
            File saida = new File(arquivo + ".png");
            ImageIO.write(imagem, "png", saida);

            System.out.println("Imagem gerada: " + arquivo + ".png");
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Método para gerar um relatório HTML que contém todos os cálculos e gráficos de cada coluna de um DataSet.
     * @param ds DataSet que será utilizado
     * @param arquivo caminho e nome do arquivo que será gerado (não precisa incluir extensão do arquivo)
     * @return verdadeiro se o relatório foi gerado, falso caso tenha ocorrido algum erro
     */
    public boolean gerarRelatorio(DataSet ds, String arquivo) {
        List<Boolean> valores = new ArrayList<>();
        for (int i = 0; i < ds.getLargura(); i++) {
            valores.add(gerarRelatorio(ds, i, arquivo));
        }

        StringBuilder htmlContent = new StringBuilder();
        htmlContent.append("<!DOCTYPE html>\n");
        htmlContent.append("<html>\n");
        htmlContent.append("<head>\n");
        htmlContent.append("    <meta charset=\"UTF-8\">\n");
        htmlContent.append("    <meta http-equiv=\"refresh\" content=\"0; url=" + 0 + arquivo + ".html\">\n");
        htmlContent.append("    <title>Redirecionando...</title>\n");
        htmlContent.append("</head>\n");
        htmlContent.append("<body>\n");
        htmlContent.append("    <p>Se você não foi redirecionado, <a href=\"" + 0 + arquivo + ".html\">clique aqui</a>.</p>\n");
        htmlContent.append("</body>\n");
        htmlContent.append("</html>");

        boolean resultado = true;
        for (Boolean valor : valores) {
            if (valor == false) {
                resultado = false;
                break;
            }
        }

        if (resultado) {
            try (FileWriter fileWriter = new FileWriter(arquivo + ".html")) {
                fileWriter.write(htmlContent.toString());
                System.out.println("Arquivo HTML gerado: " + arquivo + ".html");
            } catch (IOException e) {
                System.err.println("Ocorreu um erro na geração do arquivo HTML: " + e.getMessage());
                resultado = false;
            }
        }

        return resultado;
    }

    /**
     * Método para gerar um relatório HTML que contém todos os cálculos e gráficos de uma coluna de um DataSet.
     * @param ds DataSet que será utilizado
     * @param numeroColuna número (ou índice) da coluna desejada do DataSet (começa em zero)
     * @param arquivo caminho e nome do arquivo que será gerado (não precisa incluir extensão do arquivo)
     * @return verdadeiro se o relatório foi gerado, falso caso tenha ocorrido algum erro
     */
    public boolean gerarRelatorio(DataSet ds, int numeroColuna, String arquivo) {
        List<List<Double>> frequencia = getFrequencia(ds, numeroColuna);

        String nomeGraficoHistograma = numeroColuna + "histograma";
        String nomeGraficoSetores = numeroColuna + "setores";
        String nomeGraficoPareto = numeroColuna + "pareto";

        gerarGraficoHistograma(ds, numeroColuna, nomeGraficoHistograma);
        gerarGraficoSetores(ds, numeroColuna, nomeGraficoSetores);
        gerarGraficoPareto(ds, numeroColuna, nomeGraficoPareto);

        Roll roll = gerarRoll(ds, numeroColuna);
        StringBuilder rollTabela = new StringBuilder();
        rollTabela.append("    <table>\n");
        rollTabela.append("        <tr>\n");
        rollTabela.append("            <th colspan=\"7\">Tabela Roll</th>\n");
        rollTabela.append("        </tr>\n");
        rollTabela.append("        <tr>\n");
        for (String string : roll.getHeader()) {
            rollTabela.append("            <th>" + string + "</th>\n");
        }
        rollTabela.append("        </tr>\n");
        for (int i = 0; i < frequencia.size(); i++) {
            rollTabela.append("        <tr>\n");
            rollTabela.append("            <td>" + (i + 1) + "</td>\n");
            rollTabela.append("            <td>" + roll.getClasses().get(i) + "</td>\n");
            rollTabela.append("            <td>" + roll.getfi().get(i) + "</td>\n");
            rollTabela.append("            <td>" + roll.getFi().get(i) + "</td>\n");
            rollTabela.append("            <td>" + String.format("%.1f", roll.getfr().get(i)) + "</td>\n");
            rollTabela.append("            <td>" + String.format("%.1f", roll.getFr().get(i)) + "</td>\n");
            rollTabela.append("            <td>" + roll.getMedias().get(i) + "</td>\n");
            rollTabela.append("        </tr>\n");
        }
        rollTabela.append("        <tr>\n");
        rollTabela.append("            <td> </td>\n");
        rollTabela.append("            <td> </td>\n");
        rollTabela.append("            <td>" + roll.getfiFinal() + "</td>\n");
        rollTabela.append("            <td> </td>\n");
        rollTabela.append("            <td>" + roll.getfrFinal() + "</td>\n");
        rollTabela.append("            <td> </td>\n");
        rollTabela.append("            <td> </td>\n");
        rollTabela.append("        </tr>\n");
        rollTabela.append("    </table>\n");

        StringBuilder htmlContent = new StringBuilder();
        htmlContent.append("<!DOCTYPE html>\n");
        htmlContent.append("<html>\n");
        htmlContent.append("<head>\n");
        htmlContent.append("    <title>Relatório</title>\n");
        htmlContent.append("</head>\n");
        htmlContent.append("<style>\n");
        htmlContent.append("table, th, td {\n");
        htmlContent.append("    border:1px solid black;\n");
        htmlContent.append("}\n");
        htmlContent.append("</style>\n");
        htmlContent.append("<body>\n");
        htmlContent.append("    <h1>Relatório</h1>\n");
        int pagina = numeroColuna + 1;
        htmlContent.append("    <h2>Página " + pagina + "</h2>\n");

        int anterior = numeroColuna - 1;
        File arquivoAnterior = new File(anterior + arquivo + ".html");
        if (arquivoAnterior.exists()) {
            htmlContent.append("    <a href=\"" + anterior + arquivo + ".html\">Página anterior</a>\n");
            htmlContent.append("    <br>\n");
            htmlContent.append("    <br>\n");
            try {
                String conteudo = new String(Files.readAllBytes(Paths.get(arquivoAnterior.getPath())));
                conteudo = conteudo.replaceFirst("    <br>\n", "    <br>\n" + "    <a href=\"" + numeroColuna + arquivo + ".html\">Próxima página</a>\n");
                Files.write(Paths.get(arquivoAnterior.getPath()), conteudo.getBytes());
            } catch (IOException e) {
                System.err.println("Ocorreu um erro na geração do arquivo HTML: " + e.getMessage());
            }
        } else {
            htmlContent.append("    <br>\n");
        }
        
        htmlContent.append("    <p>Média: " + String.format("%.2f", calcularMedia(ds, numeroColuna)) + "</p>\n");
        htmlContent.append("    <p>Mediana: " + calcularMediana(ds, numeroColuna) + "</p>\n");
        htmlContent.append("    <p>Moda: " + calcularModa(ds, numeroColuna) + "</p>\n");
        htmlContent.append("    <p>Máximo: " + getMax(ds, numeroColuna) + "</p>\n");
        htmlContent.append("    <p>Mínimo: " + getMin(ds, numeroColuna) + "</p>\n");
        htmlContent.append("    <p>Variância: " + String.format("%.2f", calcularVariancia(ds, numeroColuna)) + "</p>\n");
        htmlContent.append("    <p>Desvio padrão: " + String.format("%.2f", calcularDesvioPadrao(ds, numeroColuna)) + "</p>\n");
        htmlContent.append("    <p>Desvio médio: " + String.format("%.2f", calcularDesvioMedio(ds, numeroColuna)) + "</p>\n");
        htmlContent.append(rollTabela.toString());
        htmlContent.append("    <img src=\"" + nomeGraficoHistograma + ".png\" alt=\"Gráfico histograma\" width=\"500\" height=\"300\">\n");
        htmlContent.append("    <img src=\"" + nomeGraficoSetores + ".png\" alt=\"Gráfico setores\" width=\"500\" height=\"300\">\n");
        htmlContent.append("    <img src=\"" + nomeGraficoPareto + ".png\" alt=\"Gráfico pareto\" width=\"500\" height=\"300\">\n");

        htmlContent.append("</body>\n");
        htmlContent.append("</html>");

        try (FileWriter fileWriter = new FileWriter(numeroColuna + arquivo + ".html")) {
            fileWriter.write(htmlContent.toString());
            System.out.println("Arquivo HTML gerado: " + numeroColuna + arquivo + ".html");
            return true;
        } catch (IOException e) {
            System.err.println("Ocorreu um erro na geração do arquivo HTML: " + e.getMessage());
            return false;
        }
    }

    /**
     * Método para verificar se os números de um vetor de double são inteiros.
     * Esse método é utilizado pelo método genérico de geração de gráficos para formatação dos valores.
     * @param numeros vetor de números double
     * @return verdadeiro se todos os números do vetor são inteiros e falso caso o contrário
     */
    private boolean isIntArray(double[] numeros) {
        boolean resultado = true;
        for (double numero : numeros) {
            if ((numero % 1) != 0) {
                resultado = false;
                break;
            }
        }
        return resultado;
    }

    /**
     * Método para converter um vetor de double em um vetor de inteiros.
     * Esse método é utilizado pelo método genérico de geração de gráficos para formatação dos valores.
     * @param numeros vetor de números double
     * @return vetor de números inteiros
     */
    private int[] doubleParaInt(double[] numeros) {
        int[] resultado = new int[numeros.length];
        for (int i = 0; i < numeros.length; i++) {
            resultado[i] = (int) numeros[i];
        }
        return resultado;
    }

    /**
     * Método para converter um vetor de double em um vetor de String.
     * Esse método é utilizado pelo método genérico de geração de gráficos para formatação dos valores.
     * @param numeros vetor de números double
     * @return vetor de String
     */
    private String[] doubleParaString(double[] numeros) {
        String[] resultado = new String[numeros.length];
        for (int i = 0; i < numeros.length; i++) {
            resultado[i] = String.valueOf(numeros[i]);
        }
        return resultado;
    }

    /**
     * Método para converter um vetor de inteiros em um vetor de String.
     * Esse método é utilizado pelo método genérico de geração de gráficos para formatação dos valores.
     * @param numeros vetor de números inteiros
     * @return vetor de String
     */
    private String[] intParaString(int[] numeros) {
        String[] resultado = new String[numeros.length];
        for (int i = 0; i < numeros.length; i++) {
            resultado[i] = String.valueOf(numeros[i]);
        }
        return resultado;
    }

    /**
     * Método para calcular a frequência de valores em uma coluna de um DataSet.
     * O resultado é uma lista de listas, onde essas listas são compostas pelo valor (índice 0) e a quantidade de vezes (índice 1) que ele aparece na coluna.
     * @param ds DataSet que será utilizado
     * @param numeroColuna número (ou índice) da coluna desejada do DataSet (começa em zero)
     * @return matriz de frequência de valores
     */
    public List<List<Double>> getFrequencia(DataSet ds, int numeroColuna) {
        List<Double> coluna = ds.getColuna(numeroColuna);
        Collections.sort(coluna);
        List<List<Double>> frequencia = new ArrayList<>();

        boolean presente = false;
        for (double numero : coluna) {
            for (List<Double> dupla : frequencia) {
                if (dupla.get(0) == numero) {
                    presente = true;
                    break;
                }
            }
            if (!presente) {
                List<Double> nova = new ArrayList<>();
                nova.add(numero);
                int quantidade = 0;

                for (double numero2 : coluna) {
                    if (numero == numero2) {
                        quantidade++;
                    }
                }

                nova.add((double) quantidade);
                frequencia.add(nova);
            }
            presente = false;
        }

        return frequencia;
    }

}
