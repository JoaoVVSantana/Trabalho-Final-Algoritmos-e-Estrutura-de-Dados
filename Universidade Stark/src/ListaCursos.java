public class ListaCursos {

    private CelulaCurso primeiro;
    private CelulaCurso ultimo;
   

    public ListaCursos(){
        this.primeiro= new CelulaCurso(null);
        this.ultimo=primeiro;
    }
    

    public void inserirFim(Curso x){

        
        
        ultimo.prox =new CelulaCurso(x);
        ultimo = ultimo.prox;
       
        
        
    }

    public String mostrar()
    {
        String cursos=new String();
        for(CelulaCurso i=primeiro.prox;i!=null;i=i.prox){
            cursos+=i.elemento.getNome()+" ";
        }
        return cursos;
    }

    
    public Curso pesquisar(int codigo) {
    
    //itera sobre os cursos salvos, utilizando uma célula
    for (CelulaCurso i = primeiro.prox; i != null; i = i.prox) 
    {
        //salva o elemento da célula atual num objeto Curso
        Curso curso = i.elemento;

        //se a célula atual contém o curso procurado...
        if (curso.getCodigo() == codigo) 
        {
            return curso; //retorna um objeto curso
        }
    }
    return null; //retorna null se o curso não for encontrado
}
}
