package com.ricksaud.order_management.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.math.BigDecimal;




@Entity
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private BigDecimal preco;
    private int quantidadeEstoque;


    protected Produto(){}

    public Produto(String nome, BigDecimal preco, int quantidadeEstoque) {

        if(preco == null || preco.compareTo(BigDecimal.ZERO) < 0){
            throw new IllegalArgumentException("Preco invalido.");
        }

        if(nome == null || nome.isBlank()){
            throw new IllegalArgumentException("Nome invalido");
        }

        if(quantidadeEstoque < 0){
            throw new IllegalArgumentException("O estoque nao pode ser negativo!");
        }


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

        if (quantidade <= 0) {
            throw new IllegalArgumentException("A quantidade deve ser maior que zero.");
        }

        if (quantidade > quantidadeEstoque) {
            throw new IllegalArgumentException("Estoque insuficiente.");
        }



        quantidadeEstoque -= quantidade;
    }

    public void alterarPreco(BigDecimal novoPreco){

        if(novoPreco == null || novoPreco.compareTo(BigDecimal.ZERO) < 0 ){
            throw new IllegalArgumentException("Preço inválido!");


        }
        this.preco = novoPreco;

    }

    @Override
    public String toString() {
        return "Produto{" +
                "preco=" + preco +
                ", nome='" + nome + '\'' +
                ", quantidadeEstoque=" + quantidadeEstoque +
                '}';
    }
}
