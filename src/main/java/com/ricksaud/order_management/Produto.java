package com.ricksaud.order_management;

import java.math.BigDecimal;

public class Produto {


    private Long id;
    private String nome;
    private BigDecimal preco;
    private int quantidadeEstoque;

    public Produto(Long id, String nome, BigDecimal preco, int quantidadeEstoque) {

        if(preco == null || preco.compareTo(BigDecimal.ZERO) < 0){
            throw new IllegalArgumentException("Preco invalido.");
        }

        if(nome == null || nome.isBlank()){
            throw new IllegalArgumentException("Nome invalido");
        }

        if(quantidadeEstoque < 0){
            throw new IllegalArgumentException("O estoque nao pode ser negativo!");
        }


        this.id = id;
        this.nome = nome.trim();
        this.preco = preco;
        this.quantidadeEstoque = quantidadeEstoque;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public int getQuantidadeEstoque() {
        return quantidadeEstoque;
    }

    public void adicionarEstoque(int quantidade){

        if(quantidade <= 0){
            throw new IllegalArgumentException("A quantidade precisa ser maior do que 0!");
        }

        quantidadeEstoque = Math.addExact(quantidadeEstoque, quantidade);
    }

    public void removerEstoque(int quantidade){

        if(quantidade <= 0 || quantidade > quantidadeEstoque){
            throw new IllegalArgumentException("A quantidade precisa ser maior do que 0 e menor do que a quantidade em estoque!");
        }
        quantidadeEstoque -= quantidade;
    }
}
