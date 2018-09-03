package br.com.fiap.educalab.models;

import android.graphics.drawable.Drawable;
import android.widget.Switch;

public class AplicativoInstalado {

    private String nome;
    private String packageName;
    private Drawable icon;
    private boolean chk;


    public AplicativoInstalado() {
        super();
    }

    public AplicativoInstalado(String nome, String packageName, Drawable icon) {
        this();
        this.icon = icon;
        this.nome = nome;
        this.packageName = packageName;
        //this.chk = chk;
    }

    public Drawable getIcon(){
        return icon;
    }

    public void setChk(boolean chk) {
        this.chk = chk;
    }

    public boolean getChk(){
        return chk;
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
