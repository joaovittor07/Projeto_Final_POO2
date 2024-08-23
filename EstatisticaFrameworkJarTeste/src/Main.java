import EstatisticaFramework.*;

public class Main {
    public static void main(String[] args) throws Exception {

        System.out.println("INICIO");

        // ------------------------------------------------------------------------------

        // Teste CSV

        DataSet ds = new DataSet("dados.csv");

        //List<List<String>> dados = ds.getDados();

        //System.out.println();
        //for (List<String> linhas : dados) {
        //    for (String obj : linhas) {
        //        System.out.println(obj.toString());
        //    }
        //    System.out.println("\tPROX");
        //}
        //System.out.println();

        // ------------------------------------------------------------------------------

        // Framework

        EstatisticaFramework framework = new EstatisticaFramework();

        double media = framework.calcularMedia(ds, 0);

        System.out.println("Média: " + media);

        double mediana = framework.calcularMediana(ds, 0);

        System.out.println("Mediana: " + mediana);

        double moda = framework.calcularModa(ds, 0);

        System.out.println("Moda: " + moda);

        double max = framework.getMax(ds, 0);

        System.out.println("Max: " + max);

        double min = framework.getMin(ds, 0);

        System.out.println("Min: " + min);

        double variancia = framework.calcularVariancia(ds, 0);

        System.out.println("Variância: " + variancia);

        double desvioPadrao = framework.calcularDesvioPadrao(ds, 0);

        System.out.println("Desvio padrão: " + desvioPadrao);

        double desvioMedio = framework.calcularDesvioMedio(ds, 0);

        System.out.println("Desvio médio: " + desvioMedio);

        System.out.println();

        // ------------------------------------------------------------------------------

        // Teste HTML

        framework.gerarRelatorio(ds, "relatorio");

        // ------------------------------------------------------------------------------

        System.out.println("FIM");

    }
}
