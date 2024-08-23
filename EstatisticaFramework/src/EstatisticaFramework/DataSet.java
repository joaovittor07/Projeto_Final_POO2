package EstatisticaFramework;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe DataSet para conjunto de dados.
 * @author João Vittor
 */
public class DataSet {
    /**
     * Basicamente uma matriz onde ficam os dados do DataSet.
     * Os dados são armazenados como String para aceitar tanto valores numéricos, quanto entradas como "NA".
     */
    private List<List<String>> dados;

    /**
     * Construtor da classe DataSet.
     * Recebe o caminho e nome do arquivo e transforma ele em um DataSet para ser manipulado.
     * (Aceita somente arquivos CSV por enquanto, mas outros formados podem ser adicionados no futuro).
     * @param arquivo caminho e nome do arquivo
     * @throws IOException
     */
    public DataSet(String arquivo) throws IOException {
        this.dados = new ArrayList<>();
        carregarCSV(arquivo);
    }

    /**
     * Método para leitura de um arquivo CSV.
     * Os valores são inseridos na variável dados.
     * @param arquivo caminho e nome do arquivo
     * @throws IOException
     */
    private void carregarCSV(String arquivo) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(arquivo))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                List<String> row = new ArrayList<>();
                for (String value : values) {
                    row.add(value);
                }
                dados.add(row);
            }
        }
    }

    /**
     * Método para retornar todos os dados do DataSet.
     * @return todos os dados do DataSet
     */
    public List<List<String>> getDados() {
        return dados;
    }

    /**
     * Método para retornar a altura da matriz dados.
     * @return altura da matriz dados
     */
    public int getAltura() {
        return dados.size();
    }

    /**
     * Método para calcular e retornar a largura da matriz dados.
     * Verifica qual linha é maior e retorna o tamanho dela.
     * @return largura da matriz dados
     */
    public int getLargura() {
        int maior = Integer.MIN_VALUE;
        for (List<String> lista : dados) {
            if (lista.size() > maior) {
                maior = lista.size();
            }
        }
        return maior;
    }

    /**
     * Método para retornar dados de apenas uma das colunas da matriz de dados.
     * Caso possua valores que não sejam números, estes serão ignorados (por exemplo, "NA" em posições sem valor).
     * @param numeroColuna número (ou índice) da coluna desejada (começa em zero)
     * @return valores da coluna desejada da matriz de dados
     */
    public List<Double> getColuna(int numeroColuna) {
        int cont = 0;
        List<Double> lista = new ArrayList<>();

        for (List<String> linhas : dados) {
            for (String obj : linhas) {
                if (cont == numeroColuna) {
                    if (isDouble(obj)) {
                        lista.add(Double.parseDouble(obj));
                    }
                }
                cont++;
            }
            cont = 0;
        }

        return lista;
    }

    /**
     * Verifica se uma String da matriz de dados representa um número.
     * É verificado se ela é Double para simplificar o processo.
     * @param str uma String da matriz de dados
     * @return verdadeiro se a String for um número e falso caso o contrário
     */
    private boolean isDouble(String str) {
        if (str == null || str.isEmpty()) {
            return false;
        }
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

}
