import java.io.File;
import java.io.IOException;

public class Teste {

    private Candidato[] alunos;
    private int qtdAlunos;

    public Teste() {
        alunos = new Candidato[1000];
        qtdAlunos = 0;
    }
    //recebe Candidato[] alunos e qtdAlunos do Vestibular, salvos após a leitura.
    public Teste(Candidato[] alunos, int qtdAlunos){
        this.alunos=alunos;
        this.qtdAlunos=qtdAlunos;
    }

    private void quicksortNota(int esq, int dir) {
        int i = esq, j = dir;
        double pivo = alunos[(dir + esq) / 2].getMedia();
        Candidato temp;
        while (i <= j) {
            while (alunos[i].getMedia() < pivo)
                i++;
            while (alunos[j].getMedia() > pivo)
                j--;
            if (i <= j) {
                temp = alunos[i];
                alunos[i] = alunos[j];
                alunos[j] = temp;
                i++;
                j--;
            }
        }
        if (esq < j)
            quicksortNota(esq, j);
        if (i < dir)
            quicksortNota(i, dir);
    }

    
    //acionado em Vestibular (l.26)
    public void ordenarCandidatos() {
        
        //usa o quickSort para ordenar em ordem crescente de média.
        /*As médias são calculadas na propria classe do candidato */
        
        quicksortNota(0, qtdAlunos - 1);
    }


    public static void main(String[] args) throws IOException 
    {

        

        //dá início ao processo de vestibular
        Vestibular UniversidadeStark = new Vestibular();
        System.out.println("Vestibular iniciado");
        System.out.println("Iniciando leitura dos arquivos...");
        UniversidadeStark.lerArquivo();
        System.out.println("Leitura dos arquivos concluída.");
        //após a leitura, os dados da entrada serão salvos nas devidas estruturas ou classes.

        //ordenando os candidatos, de acordo com a média...
        //aciona o método de ordenação QuickSort
        System.out.println("Iniciando ordenação dos candidatos...");
        UniversidadeStark.ordenar();
        System.out.println("Candidatos ordenados.");

        //classificando candidatos nos cursos desejados
        System.out.println("Iniciando cálculo da Classificação...");;
        UniversidadeStark.calcularClassificacao();
        System.out.println("Classificação concluída.");

        
        
        //escrevendo no arquivo de saída
        System.out.println("Iniciando Escrita no arquivo de saída...");
        UniversidadeStark.escreverSaida();
        System.out.println("Escrita finalizada. Cheque o arquivo de saída.");
        System.out.println("Vestibular finalizado!");
    }
}

