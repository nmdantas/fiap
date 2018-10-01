package br.com.fiap.educalab.models;

import android.graphics.drawable.Drawable;
import android.widget.ImageView;

public class Objetivos {

    private String nomeObjetivo;
    private Drawable bg_objetivo;


    public Objetivos(String nomeObjetivo) {

        this.nomeObjetivo = nomeObjetivo;
        this.bg_objetivo = bg_objetivo;
    }

    public void setNomeObjetivo(String nomeObjetivo){
        this.nomeObjetivo = nomeObjetivo;
    }

    public String getNomeObjetivo() {
        return nomeObjetivo;
    }

    public Drawable getBg_objetivo() { return bg_objetivo; }

   // public void setBg_objetivo(Drawable bg_objetivo) {
      //  this.bg_objetivo = bg_objetivo;
   // }
}
