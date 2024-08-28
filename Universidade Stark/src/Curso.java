import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

class Curso {
    
    private int codigo;
  
    private String nome;
   
    private int vagas;
    
    private double notaCorte;
    
    private ArrayList<Candidato> selecionados;
   
    private Queue<Candidato> filaEspera;

    public Curso() {
        this.codigo = -1;
        this.nome = " ";
        this.vagas = 0;
        this.selecionados = new ArrayList<Candidato>();
        this.filaEspera = new LinkedList<>();
    }

    // recebe as informações necessárias do arquivo
    public Curso(int codigo, String nome, int vagas) {
        this.codigo = codigo;
        this.nome = nome;
        this.vagas = vagas;

        this.selecionados = new ArrayList<Candidato>();
        this.filaEspera = new LinkedList<>();

    }

    public void inserirFilaEspera(Candidato candidato) {
        filaEspera.add(candidato);
    }

    public boolean inserirSelecionados(Candidato candidato) {

        if (selecionados.size() < vagas) {
            selecionados.add(candidato);
            return true;
        } else
            return false;
    }

    public int getCodigo() {
        return codigo;
    }

    public String getNome() {
        return nome;
    }

    public int getVagas() {
        return vagas;
    }

    public double getNotaCorte() {
        return notaCorte;
    }

    public void setNotaCorte(double notaCorte) {
        this.notaCorte = notaCorte;
    }

    public ArrayList<Candidato> getSelecionados() {
        return selecionados;
    }

    public Queue<Candidato> getFilaEspera() {
        return filaEspera;
    }
}