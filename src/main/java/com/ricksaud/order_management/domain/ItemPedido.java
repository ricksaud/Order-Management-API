package com.ricksaud.order_management.domain;

import java.math.BigDecimal;

public class ItemPedido {

    private Produto produto;
    private int quantidade;
    private BigDecimal precoUnitario;


    public ItemPedido(Produto produto, int quantidade){

        if(produto == null){
            throw new IllegalArgumentException("O produto não pode ser nulo.");
        }

        if(quantidade <= 0 ){
            throw new IllegalArgumentException("A quantidade deve ser maior que zero!");
        }

        this.produto = produto;
        this.quantidade = quantidade;
        this.precoUnitario = produto.getPreco();

    }

    public Produto getProduto() {
        return produto;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public BigDecimal getPrecoUnitario() {
        return precoUnitario;
    }



    public BigDecimal calcularSubtotal(){
        return precoUnitario.multiply(BigDecimal.valueOf(quantidade));
    }
}
