package br.com.fiap.educalab.models;

public class AplicativoInstalado {

    private String nome;
    private String packageName;

    public AplicativoInstalado() {
        super();
    }

    public AplicativoInstalado(String nome, String packageName) {
        this();

        this.nome = nome;
        this.packageName = packageName;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }
}
