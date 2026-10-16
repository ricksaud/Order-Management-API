package com.ricksaud.order_management;
import java.math.BigDecimal;

import com.ricksaud.order_management.domain.ItemPedido;
import com.ricksaud.order_management.domain.Produto;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;



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
        assertEquals(new BigDecimal("200.00"), subtotal);
    }


    @Test
    public void deveManterPrecoUnitarioQuandoPrecoDoProdutoMudar(){
        Produto ventilador = new Produto("Ventilador", new BigDecimal("100.00"),10);
        ItemPedido item1 = new ItemPedido(ventilador, 2);
        ventilador.alterarPreco(new BigDecimal("120.00"));

        assertEquals(new BigDecimal("100.00"), item1.getPrecoUnitario());


    }



}
