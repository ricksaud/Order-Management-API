package com.ricksaud.order_management;



import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Pedido {

    private List<ItemPedido> itens = new ArrayList<>();
    private StatusPedido status = StatusPedido.PENDENTE;

    public StatusPedido getStatus() {
        return status;
    }

    public void adicionarItem(ItemPedido item){
        if(status != StatusPedido.PENDENTE){
            throw new IllegalStateException("O estado do pedido nao esta pendente.");

        }

        if(item == null){
            throw new IllegalArgumentException("O item e nulo");

        }

        itens.add(item);

    }

    public BigDecimal calcularTotal(){
        BigDecimal total = BigDecimal.ZERO;

        for(ItemPedido item : itens){
            total = total.add(item.calcularSubtotal());
        }

        return total;

    }

    public void confirmar(){
        if(status != StatusPedido.PENDENTE){
            throw new IllegalStateException("O pedido nao esta pendente.");
        }

        if(itens.isEmpty()){
            throw new IllegalStateException("Não é possível confirmar um pedido sem itens.");
        }

        Map<Produto, Integer> quantidades = agruparQuantidadesPorProduto();

        for(Map.Entry<Produto, Integer> entrada : quantidades.entrySet()){
            Produto produto = entrada.getKey();
            int quantidadeNecessaria = entrada.getValue();

            if(quantidadeNecessaria > produto.getQuantidadeEstoque()){
                throw new IllegalStateException("Estoque insuficiente para o produto: " + produto.getNome());
            }
        }

        for(Map.Entry<Produto,Integer> entrada : quantidades.entrySet()){
            Produto produto = entrada.getKey();
            int quantidadeNecessaria = entrada.getValue();

            produto.removerEstoque(quantidadeNecessaria);
        }


        status = StatusPedido.CONFIRMADO;
    }


    public void cancelar(){
        if(status != StatusPedido.PENDENTE){
            throw new IllegalStateException("Estado nao esta pendente.");
        }

        status = StatusPedido.CANCELADO;
    }

    private Map<Produto, Integer> agruparQuantidadesPorProduto(){
        HashMap<Produto, Integer> quantidades = new HashMap<>();

        for(ItemPedido item: itens){
            Produto produto = item.getProduto();

            int quantidadeAtual = quantidades.getOrDefault(produto,0);
            int novaQuantidade = quantidadeAtual + item.getQuantidade();

            quantidades.put(produto, novaQuantidade);

        }

        return quantidades;

    }

}
