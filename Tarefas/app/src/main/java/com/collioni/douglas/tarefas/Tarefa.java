package com.collioni.douglas.tarefas;

/**
 * Created by Douglas.Collioni on 08/04/2015.
 */
public class Tarefa {
    private String descricao;

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Tarefa(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public String toString() {
        return descricao;
    }
}
