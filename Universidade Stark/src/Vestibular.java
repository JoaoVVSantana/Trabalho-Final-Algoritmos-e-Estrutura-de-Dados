import java.io.FileWriter;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Vestibular {

    // Valores serão definidos após a leitura do arquivo...
    private static int qtdCursos;
    private static ListaCursos cursos = new ListaCursos();
    private static int qtdCandidatos;
    private static Candidato[] candidatos;

    public Vestibular() {
        this.candidatos = new Candidato[100];
        // Construção feita na leitura do arquivo

    }

    public static void ordenar() {
        // Após ser acionado pelo main, os candidatos (salvos no vetor) e quantidade de
        // candidatos são objetificados
        Teste ordenar = new Teste(candidatos, qtdCandidatos);
        // método da classe teste.
        ordenar.ordenarCandidatos();
        // candidatos ordenados em ordem crescente de média

        // Como os candidatos estão agora ordenados, analisar o critério de desempate
        // que é a nota de redação, para que isso também fique ordenado.

        // itera sobre o vetor candidatos, começando da maior média (fim do vetor)
        for (int i = qtdCandidatos - 1; i != 0; i--) {
            // se a média do candidato for igual ao do que está atrás dele na fila
            if (candidatos[i].getMedia() == candidatos[i - 1].getMedia()
                    // e a nota de Redação (do anterior) for maior
                    && candidatos[i].getNotaRed() < candidatos[i - 1].getNotaRed()) {
                // são trocados de posição
                Candidato temp = candidatos[i - 1];
                candidatos[i - 1] = candidatos[i];
                candidatos[i] = temp;
                temp = null;

            }
        }
        // vetor candidatos ordenado em ordem crescente, tratando médias iguais com a
        // maior nota de Red.

        for (int i = qtdCandidatos - 1; i != 0; i--) {
            System.out.print(candidatos[i].getNome() + " - ");
        }
    }

    public void lerArquivo() {
        try {
            // Leitura do arquivo
            File entradaArq = new File("entrada.txt");
            Scanner scannerEntrada = new Scanner(entradaArq);

            // Criação de um vetor "valores" que armazena a quantidade de cursos e de
            // candidatos
            String[] valores = scannerEntrada.nextLine().split(";");

            // qtdCursos vai armazenar a quantidade de cursos
            this.qtdCursos = Integer.parseInt(valores[0]);
            System.out.println("Quantidade de cursos: " + qtdCursos);

            // qtdCandidatos armazena o número de candidatos
            this.qtdCandidatos = Integer.parseInt(valores[1]);
            System.out.println("Quantidade de candidatos: " + qtdCandidatos);

            // Com a quantidade de cursos analisada e salva, agora devemos analisar o nome
            // dos cursos, qual o código deles e quantas vagas estão disponíveis

            // itera sobre os cursos
            for (int i = 0; i < qtdCursos; i++) {

                // valores recebe o conteúdo da próxima linha do arquivo
                valores = scannerEntrada.nextLine().split(";");

                // armazena o código do curso na variável (na posição 0)
                int codigo = Integer.parseInt(valores[0]);
                System.out.println("Codigo: " + codigo);

                // o nome do curso (na posição 1)
                String nome = valores[1];
                System.out.println("Curso: " + nome);

                // e a quantidade de vagas para esse curso (na posição 2)
                int vagas = Integer.parseInt(valores[2]);
                System.out.println("Vagas: " + vagas);

                // após serem salvas, as informações sobre codigo, nome e vagas serão salvas
                // em um objeto tipo Curso
                Curso curso = new Curso(codigo, nome, vagas);

                // esse objeto é adicionado a lista de cursos (classe ListaCursos)
                cursos.inserirFim(curso);

                System.out.println("-----------------");
            }
            // Depois do for, todos os cursos foram salvos na lista de cursos.
            System.out.println("Cursos disponíveis: " + cursos.mostrar());

            System.out.println("-------------------------");

            // Agora, deverá ser feita uma análise dos candidatos dos cursos

            // itera sobre todos os candidatos
            for (int i = 0; i < qtdCandidatos; i++) {

                // valores recebe a linha do arquivo contando um candidato
                valores = scannerEntrada.nextLine().split(";");

                // armazena o nome do candidato (na posição 0)
                String nome = valores[0];
                System.out.println("Nome do aluno: " + nome);

                // As notas desse candidato (nas posições 1, 2 e 3)
                double notaRed = Double.parseDouble(valores[1]);
                double notaMat = Double.parseDouble(valores[2]);
                double notaLing = Double.parseDouble(valores[3]);
                System.out.println("Nota em Redação: " + notaRed);
                System.out.println("Nota em Matemática: " + notaMat);
                System.out.println("Nota em Linguagens: " + notaLing);

                // os cursos selecionados pelo aluno (nas posições 4 e 5)
                int codCursoOp1 = Integer.parseInt(valores[4]);
                int codCursoOp2 = Integer.parseInt(valores[5]);
                System.out.println("Curso 1 selecionado: " + codCursoOp1);
                System.out.println("Curso 2 selecionado: " + codCursoOp2);

                // Um objeto do tipo candidato é criado
                // As informações de nome, notas e o códigos são salvos
                Candidato candidato = new Candidato(nome, notaRed, notaMat, notaLing, codCursoOp1, codCursoOp2);
                // Candidato adicionado ao vetor de candidatos
                this.candidatos[i] = candidato;

                System.out.println("-----------------");
            }
            // Depois do for, todos os candidatos foram salvos no vetor candidatos.

            scannerEntrada.close();

            // volta ao main
        } catch (IOException e) {

            System.out.println("ERRO NO ARQUIVO");
        }
    }

    public void escreverSaida() throws IOException {

        
        FileWriter escreverSaida = new FileWriter("saida.txt");

        int i = 1;

        while (cursos.pesquisar(i) != null) {
            Curso curso = cursos.pesquisar(i);
            // Escreve o nome do curso da iteração e a nota de corte dele
            escreverSaida.write(curso.getNome() + " " + String.format("%.2f", curso.getNotaCorte()) + "\n");
            escreverSaida.write("Selecionados\n");
            // escreve o nome dos selecionados daquele curso
            for (Candidato candidato : curso.getSelecionados()) {
                escreverSaida.write(candidato.getNome() + " " + String.format("%.2f", candidato.getMedia()) + "\n");
            }
            // escreve o nome dos que ficaram na fila de espera
            escreverSaida.write("Fila de Espera\n");
            for (Candidato candidato : curso.getFilaEspera()) {
                escreverSaida.write(candidato.getNome() + " " + String.format("%.2f", candidato.getMedia()) + "\n");
            }

            escreverSaida.write("\n");
            i++;

        }

        escreverSaida.close();

    }

    public void calcularClassificacao() 
    {
    //Com os candidatos ordenados em média crescente, começamos pela maior média
    for(int i=qtdCandidatos-1;i>=0;i--)
    {
        System.out.println("<<<<<<CANDIDATO>>>>>>>>");
        Candidato candidatoAtual=candidatos[i];
        System.out.println(candidatoAtual.getNome());
        
        int op1=candidatoAtual.getCodCursoOp1();
        int op2=candidatoAtual.getCodCursoOp2();
        Curso curso1=cursos.pesquisar(op1);
        System.out.println(curso1.getNome());
        Curso curso2=cursos.pesquisar(op2);
        System.out.println(curso2.getNome());

        //1° Op tem vaga
        if(curso1.getSelecionados().size()<curso1.getVagas())
        {
            curso1.inserirSelecionados(candidatoAtual);
            System.out.println("Candidato "+candidatoAtual.getNome()+" inserido em "+curso1.getNome());
            
        }
        //1° Op NÃO tem vaga
        else if(curso1.getSelecionados().size()>=curso1.getVagas())
        {
            //2° Op tem vaga
            if(curso2.getSelecionados().size()<curso2.getVagas())
            {
                curso2.inserirSelecionados(candidatoAtual);
                System.out.println("Candidato "+candidatoAtual.getNome()+" inserido em "+curso2.getNome());
                

                System.out.println("na segunda opção, então...");
                curso1.inserirFilaEspera(candidatoAtual);
                System.out.println("Candidato "+candidatoAtual.getNome()+" inserido na fila de espera de "
                +curso1.getNome());
            }

            //2° Op NÃO tem vaga
            else if(curso2.getSelecionados().size()>=curso2.getVagas())
            {
                curso1.inserirFilaEspera(candidatoAtual);
                curso2.inserirFilaEspera(candidatoAtual);
                System.out.println("Candidato "+candidatoAtual.getNome()+" inserido na fila de espera de "
                +curso1.getNome()+" e de "+curso2.getNome()+ " por não ter vagas");
            }


        }


    }

    //Com os candidatos em seus devidos cursos, calculamos a nota de corte
    for(int i=1;i<=qtdCursos;i++)
    {
        Curso cursoAtual=cursos.pesquisar(i);
        ArrayList<Candidato> selecionados=cursoAtual.getSelecionados();
        double menorMedia=999999999;

        //iteramos a fila de selecionados do curso
        for(Candidato candidato : selecionados)
        {
            double mediaCandidato=candidato.getMedia();
            //se a média do candidato for a menor entre as médias, salva na variável
            if(mediaCandidato<menorMedia)
            {
                
                if(mediaCandidato<menorMedia)
                {
                    menorMedia=mediaCandidato;
                }
            }
        }
        //a menor média salva é inserida na nota de corte do curso
        cursoAtual.setNotaCorte(menorMedia);
    }

    }

}

