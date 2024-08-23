package EstatisticaFramework;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe Roll para geração dos valores da tabela Roll
 * @author João Vittor
 */
public class Roll {
    /**
     * Coluna do DataSet que é utilizada para calcular a tabela Roll.
     */
    private List<Double> coluna;
    /**
     * Matriz de frequência dos valores da coluna do DataSet que é utilizada para calcular a tabela Roll.
     */
    private List<List<Double>> frequencia;

    /**
     * Linha do cabeçalho da tabela Roll.
     */
    private final String[] header = {"N", "Classes", "fi", "Fi", "fr (%)", "Fr (%)", "X̄"};

    /**
     * Coluna de classes da tabela Roll.
     */
    private List<String> classes;
    /**
     * Coluna de fi da tabela Roll.
     */
    private List<Integer> fi;
    /**
     * Coluna Fi da tabela Roll.
     */
    private List<Integer> Fi;
    /**
     * Coluna fr da tabela Roll.
     */
    private List<Double> fr;
    /**
     * Coluna Fr da tabela Roll.
     */
    private List<Double> Fr;
    /**
     * Coluna de médias da tabela Roll.
     */
    private List<Double> medias;

    /**
     * Soma dos valores da coluna fi.
     */
    private int fiFinal;
    /**
     * Soma dos valores da coluna fr.
     */
    private double frFinal;

    /**
     * Construtor da classe Roll.
     * Recebe a coluna e a frequência da coluna do DataSet que é utilizada para calcular a tabela Roll.
     * A coluna e a frequencia devem representar a mesma coluna do DataSet.
     * @param coluna coluna do DataSet para calcular os valores do Roll
     * @param frequencia matriz de frequencia dos valores da coluna do DataSet
     */
    public Roll(List<Double> coluna, List<List<Double>> frequencia) {
        this.coluna = coluna;
        this.frequencia = frequencia;

        classes = new ArrayList<>();
        fi = new ArrayList<>();
        Fi = new ArrayList<>();
        fr = new ArrayList<>();
        Fr = new ArrayList<>();
        medias = new ArrayList<>();

        fiFinal = 0;
        frFinal = 0;

        calcularRoll();
    }

    /**
     * Método para o calculo dos valores do Roll.
     * Esse método é utilizado pelo construtor.
     * O cálculo foi tirado de dentro do construtor e colocado em um método próprio para que o código fique mais organizado.
     */
    private void calcularRoll() {
        int cont = 0;
        
        for (List<Double> lista : frequencia) {
            String num1 = "";
            String num2 = "";
            if ((lista.get(0) % 1 == 0)) {
                num1 = String.valueOf(lista.get(0).intValue());
                num2 = String.valueOf(lista.get(0).intValue() + 1);
            } else {
                num1 = String.valueOf(lista.get(0));
                num2 = String.valueOf(lista.get(0) + 1);
            }

            classes.add(num1 + " ⊢ " + num2);
            medias.add((lista.get(0) + (lista.get(0) + 1)) / 2);

            int quantidade = lista.get(1).intValue();
            int tamanho = coluna.size();

            fi.add(quantidade);
            fiFinal += quantidade;

            BigDecimal bd = BigDecimal.valueOf((quantidade * 100.0) / tamanho);
            bd.setScale(2, RoundingMode.HALF_UP);
            double porcentagem = bd.doubleValue();

            fr.add(porcentagem);
            frFinal += porcentagem;

            if (cont == 0) {
                Fi.add(quantidade);
                Fr.add(porcentagem);
            } else {
                Fi.add(quantidade + Fi.get(cont - 1));
                Fr.add(porcentagem + Fr.get(cont - 1));
            }

            cont++;
        }

        classes.set((classes.size() - 1), classes.get((classes.size() - 1)).replace("⊢", "⊢⊣"));
    }

    /**
     * Método para retornar a coluna do DataSet que está sendo utilizada para o cálculo dos valores do Roll.
     * @return coluna do DataSet
     */
    public List<Double> getColuna() {
        return coluna;
    }
    
    /**
     * Método para retornar a frequência dos valores da coluna do DataSet que está sendo utilizada para o cálculo dos valores do Roll.
     * @return frequência dos valores da coluna do DataSet
     */
    public List<List<Double>> getFrequencia() {
        return frequencia;
    }

    /**
     * Método para retornar a linha do cabeçalho da tabela Roll.
     * @return cabeçalho do Roll
     */
    public String[] getHeader() {
        return header;
    }

    /**
     * Método para retornar a coluna de classes do Roll.
     * @return classes do Roll
     */
    public List<String> getClasses() {
        return classes;
    }

    /**
     * Método para retornar a coluna de fi do Roll.
     * @return valores fi do Roll
     */
    public List<Integer> getfi() {
        return fi;
    }

    /**
     * Método para retornar a coluna de Fi do Roll.
     * @return valores Fi do Roll
     */
    public List<Integer> getFi() {
        return Fi;
    }

    /**
     * Método para retornar a coluna de fr do Roll.
     * @return valores fr do Roll
     */
    public List<Double> getfr() {
        return fr;
    }

    /**
     * Método para retornar a coluna de Fr do Roll.
     * @return valores Fr do Roll
     */
    public List<Double> getFr() {
        return Fr;
    }

    /**
     * Método para retornar a coluna de médias do Roll.
     * @return médias do Roll
     */
    public List<Double> getMedias() {
        return medias;
    }

    /**
     * Método para retornar a soma dos valores fi do Roll.
     * @return soma dos valores fi do Roll
     */
    public int getfiFinal() {
        return fiFinal;
    }
/**
     * Método para retornar a soma dos valores fr do Roll.
     * @return soma dos valores fr do Roll
     */
    public double getfrFinal() {
        return frFinal;
    }
    
}
