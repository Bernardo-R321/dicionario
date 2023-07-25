package dicionariocomtela;

/**
 *
 * @author artur
 */
public class Palavra {

    private String palavra;
    private String significado;
    private int codigo;

    public Palavra(String palavra) {                         ///Construtor
        this(palavra, ""); //Sobrecarga -> passa pro outro construtor a responsabilidade de realizar a lógica
    }

    public Palavra(String palavra, String significado) { ///Outro construtor
        this.palavra = palavra;
        this.significado = significado;
    }
    
    public Palavra(int codigo) { ///Outro construtor
        this.codigo = codigo;
    }

    public String getPalavra() {
        return palavra;
    }

    public void setPalavra(String palavra) {
        this.palavra = palavra;
    }

    public String getSignificado() {
        return significado;
    }

    public void setSignificado(String significado) {
        this.significado = significado;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    @Override
    public boolean equals(Object objeto) {

        if (objeto instanceof Palavra) {
            return ((Palavra) objeto).codigo == this.codigo;
        }
        return false;
    }

    @Override
    public String toString() { ///Muda o que sai escrito se printa o objeto ao invés de sair classe@endereço na memória sai o que está aqui
        return this.palavra + " | " + this.significado;
    }

    /*
        java.util.ArrayList<Class> list = new ArrayList(); 
        for(Class c : list){  --> FOR EACH
            System.out.println(c);
        }
    
    
    
     */
}
