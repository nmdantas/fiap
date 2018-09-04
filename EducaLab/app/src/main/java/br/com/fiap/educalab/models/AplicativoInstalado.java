package br.com.fiap.educalab.models;

import android.graphics.drawable.Drawable;

public class AplicativoInstalado {

    private String nome;
    private String packageName;
    private Drawable icon;
    private boolean blocked;


    public AplicativoInstalado() {
        super();
    }

    public AplicativoInstalado(String nome, String packageName, Drawable icon) {
        this();
        this.icon = icon;
        this.nome = nome;
        this.packageName = packageName;
        //this.blocked = blocked;
    }

    public Drawable getIcon(){
        return icon;
    }

    public void setBlocked(boolean blocked) {
        this.blocked = blocked;
    }

    public boolean getBlocked(){
        return blocked;
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
