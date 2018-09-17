package reflexao;

public class Pessoa {

    private String nome;

    private double altura;

    private double peso;

    private String nomemae;

    public Pessoa() {

    }

    public Pessoa(final String nome, final double altura, final double peso, final String nomemae) {

        this.nome = nome;
        this.altura = altura;
        this.peso = peso;
        this.nomemae = nomemae;
    }

    public String getNome() {

        return this.nome;
    }

    public void setNome(final String nome) {

        this.nome = nome;
    }

    public Double getAltura() {

        return this.altura;
    }

    public void setAltura(final Double altura) {

        this.altura = altura;
    }

    public Double getPeso() {

        return this.peso;
    }

    public void setPeso(final Double peso) {

        this.peso = peso;
    }

    public String getNomemae() {

        return this.nomemae;
    }

    public void setNomemae(final String nomemae) {

        this.nomemae = nomemae;
    }

    public void bocejar() {

    }

    public void emagrecer(final String nome, final double pesoantigo) {

        this.nome = nome;
        this.peso = this.peso - pesoantigo;
    }

    @Override
    public String toString() {

        return "Pessoa [nome = " + this.nome + ", altura = " + this.altura + ", peso = " + this.peso + ", nomemae = " + this.nomemae + "]";
    }

}
