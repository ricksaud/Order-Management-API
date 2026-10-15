package com.ricksaud.order_management;

import java.math.BigDecimal;

import org.junit.Test;
import static org.junit.Assert.assertEquals;



public class ItemPedidoTest {

    @Test
    public void deveCalcularSubtotal() {
        // Preparar
        Produto produto = new Produto(
                "Camiseta", new BigDecimal("100.00"), 10
        );
        ItemPedido item = new ItemPedido(produto, 2);

        // Executar
        BigDecimal subtotal = item.calcularSubtotal();

        // Verificar
        assertEquals(new BigDecimal("300.00"), subtotal);
    }


    @Test
    public void deveManterPrecoUnitarioQuandoPrecoDoProdutoMudar(){
        Produto ventilador = new Produto("Ventilador", new BigDecimal("100.00"),10);
        ItemPedido item1 = new ItemPedido(ventilador, 2);
        ventilador.alterarPreco(new BigDecimal("120.00"));

        assertEquals(new BigDecimal("120.00"), item1.getPrecoUnitario());


    }

    @Test
    public void deveConfirmarPedidoeDescontarEstoque(){
        Produto pulseira = new Produto("Pulseira", new BigDecimal("35.00"), 10);
        ItemPedido item1 = new ItemPedido(pulseira, 3);
        Pedido pedido = new Pedido();
        pedido.adicionarItem(item1);

        pedido.confirmar();

        assertEquals(StatusPedido.CONFIRMADO, pedido.getStatus());
        assertEquals(7, pulseira.getQuantidadeEstoque());
    }

}
