class Candidato {
    // recebe do main o nome do aluno
    private String nome;
    // sua nota em redação
    private double notaRed;
    // matemática
    private double notaMat;
    // e linguagens
    private double notaLing;
    // o curso de desejo
    private int codCursoOp1;
    // segunda opção
    private int codCursoOp2;
    // e sua média nas notas
    private double media;

    public Candidato() {
        this.nome = " ";
        this.notaRed = 0;
        this.notaMat = 0;
        this.notaLing = 0;
        this.codCursoOp1 = 0;
        this.codCursoOp2 = 0;
        this.media = 0;
    }

    public Candidato(String nome, double notaRed, double notaMat, double notaLing, int codCursoOp1, int codCursoOp2) {
        this.nome = nome;
        this.notaRed = notaRed;
        this.notaMat = notaMat;
        this.notaLing = notaLing;
        this.codCursoOp1 = codCursoOp1;
        this.codCursoOp2 = codCursoOp2;
        // calculo/definição da média
        this.media = (notaRed + notaMat + notaLing) / 3;
    }

    public String getNome() {
        return nome;
    }

    public double getMedia() {
        return media;
    }

    public double getNotaRed() {
        return notaRed;
    }

    public double getNotaLing() {
        return notaLing;
    }

    public double getNotaMat() {
        return notaMat;
    }

    public int getCodCursoOp1() {
        return codCursoOp1;
    }

    public int getCodCursoOp2() {
        return codCursoOp2;
    }

    @Override
    public String toString() {
        return "Candidato: " + nome + ", Redação: " + notaRed + ", Matemática: " + notaMat + ", Linguagens: " + notaLing
                + ", Opção 1: " + codCursoOp1 + ", Opção 2: " + codCursoOp2 + ", Média: " + media;
    }
    
}
