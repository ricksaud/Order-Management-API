package com.ricksaud.order_management;

import java.math.BigDecimal;
import java.util.*;

public class Pedido {

    private List<ItemPedido> itens = new ArrayList<>();
    private StatusPedido status = StatusPedido.PENDENTE;


    public void adicionarItem(ItemPedido item){

        if(status != StatusPedido.PENDENTE){
            throw new IllegalStateException(
                    "Somente pedidos pendentes podem receber itens."
            );
        }


        Objects.requireNonNull(item, "O item nao pode ser nulo");
        itens.add(item);
    }

    public void confirmar(){
        if(status!=StatusPedido.PENDENTE){
            throw new IllegalStateException(
                    "Somente pedidos pendentes podem ser confirmados."
            );
        }
        if (itens.isEmpty()) {
            throw new IllegalStateException(
                    "Não é possível confirmar um pedido sem itens."
            );
        }
        status = StatusPedido.CONFIRMADO;
    }

    public void cancelar() {
        if (status != StatusPedido.PENDENTE) {
            throw new IllegalStateException(
                    "Somente pedidos pendentes podem ser cancelados."
            );
        }
        status = StatusPedido.CANCELADO;
    }

    public BigDecimal calcularTotal(){
        BigDecimal total = BigDecimal.ZERO;
        for(ItemPedido item: itens){
            total = total.add(item.calcularSubtotal());
        }
        return total;
    }


    private Map<Produto, Integer> agruparQuantidadesPorProduto(){
        Map<Produto, Integer> quantidades = new HashMap<>();

        for(ItemPedido item : itens){
            Produto produto = item.getProduto();
            int quantidade = item.getQuantidade();

            int totalAtual = quantidades.getOrDefault(produto, 0);
            quantidades.put(produto, totalAtual + quantidade);

        }
        return  quantidades;




    }

    public StatusPedido getStatus() {
        return status;
    }
}
