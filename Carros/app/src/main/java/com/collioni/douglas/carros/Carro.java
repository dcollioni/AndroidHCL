package com.collioni.douglas.carros;

/**
 * Created by Douglas.Collioni on 07/04/2015.
 */
public class Carro {
    private String nome;
    private int cv;
    private int fotoId;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getCv() {
        return cv;
    }

    public void setCv(int cv) {
        this.cv = cv;
    }

    public int getFotoId() {
        return fotoId;
    }

    public void setFotoId(int fotoId) {
        this.fotoId = fotoId;
    }

    public Carro(String nome, int cv, int fotoId) {
        this.nome = nome;
        this.cv = cv;
        this.fotoId = fotoId;
    }

    @Override
    public String toString() {
        return nome;
    }
}
