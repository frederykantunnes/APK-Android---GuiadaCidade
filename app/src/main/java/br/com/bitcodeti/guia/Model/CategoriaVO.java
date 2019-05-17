package br.com.bitcodeti.guia.Model;

/**
 * Created by Frederyk Antunnes on 18/09/2017.
 */

public class CategoriaVO {

    private int id;
    private String nome;
    private String descricao;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
