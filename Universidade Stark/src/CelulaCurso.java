public class CelulaCurso {


    public Curso elemento;
    public CelulaCurso prox;


    public CelulaCurso(Curso elemento) {
        this.elemento = elemento;
        this.prox = null;
    }
    public Curso getCurso() {
        return elemento;
    }
}