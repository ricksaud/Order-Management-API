package com.ricksaud.order_management;
import java.math.BigDecimal;
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


    @Test
    public void deveManterPedidoEEstoqueQuandoEstoqueForInsuficiente(){
        Produto cachorro = new Produto("Cachorro", new BigDecimal("50.00"), 3);
        ItemPedido item1 = new ItemPedido(cachorro, 4);

        Pedido pedido = new Pedido();
        pedido.adicionarItem(item1);

        assertThrows(IllegalStateException.class, () -> pedido.confirmar());

        assertEquals(StatusPedido.PENDENTE, pedido.getStatus());
        assertEquals(3, cachorro.getQuantidadeEstoque());

    }


    @Test
    public void deveRejeitarConfirmacaoQuandoSomaDosItensExcederEstoque(){
        Produto cachorro = new Produto("Cachorro", new BigDecimal("50.00"), 5);
        ItemPedido item1 = new ItemPedido(cachorro, 3);
        ItemPedido item2 = new ItemPedido(cachorro, 3);

        Pedido pedido = new Pedido();
        pedido.adicionarItem(item1);
        pedido.adicionarItem(item2);

        assertThrows(IllegalStateException.class, () -> pedido.confirmar() );

        assertEquals(StatusPedido.PENDENTE, pedido.getStatus());
        assertEquals(5, cachorro.getQuantidadeEstoque());

    }

    @Test
    public void deveManterTodosOsEstoquesQuandoUmProdutoForInsuficiente() {

       Produto teclado = new Produto("Teclado Mecanico", new BigDecimal("120.00"), 10);
       Produto mouse = new Produto("Mouse quantico", new BigDecimal("180.00"), 1);


       ItemPedido item1 = new ItemPedido(teclado, 2);
       ItemPedido item2 = new ItemPedido(mouse, 3);


       Pedido pedido = new Pedido();

       pedido.adicionarItem(item1);
       pedido.adicionarItem(item2);

assertThrows(IllegalStateException.class, () -> pedido.confirmar());

assertEquals(StatusPedido.PENDENTE, pedido.getStatus());
assertEquals(10, teclado.getQuantidadeEstoque());
assertEquals(1, mouse.getQuantidadeEstoque());

    }

    @Test
    public void deveImpedirSegundaConfirmacaoSemAlterarEstoque(){
        Produto teclado = new Produto("Teclado Mecanico", new BigDecimal("120.00"), 10);

        ItemPedido item1 = new ItemPedido(teclado, 2);


        Pedido pedido = new Pedido();
        pedido.adicionarItem(item1);

        pedido.confirmar();

        assertEquals(StatusPedido.CONFIRMADO, pedido.getStatus());
        assertEquals(8, teclado.getQuantidadeEstoque());

        assertThrows(IllegalStateException.class, () -> pedido.confirmar());

        assertEquals(StatusPedido.CONFIRMADO, pedido.getStatus());
        assertEquals(8, teclado.getQuantidadeEstoque());

    }

    @Test
    public void deveRejeitarConfirmacaoDePedidoVazio() {
         Pedido pedido = new Pedido();

         assertThrows(IllegalStateException.class, () -> pedido.confirmar());

         assertEquals(StatusPedido.PENDENTE, pedido.getStatus());
    }



    @Test
    public void deveCancelarPedidoPendenteSemAlterarEstoque(){
        Produto teclado = new Produto("Teclado Mecanico", new BigDecimal("120.00"), 10);

        ItemPedido item1 = new ItemPedido(teclado, 2);

        Pedido pedido = new Pedido();
        pedido.adicionarItem(item1);

        pedido.cancelar();

        assertEquals(StatusPedido.CANCELADO, pedido.getStatus());
        assertEquals(10, teclado.getQuantidadeEstoque());
    }

    @Test
    public void deveRejeitarCancelamentoDePedidoConfirmado(){
        Produto teclado = new Produto("Teclado Mecanico", new BigDecimal("120.00"), 10);

        ItemPedido item1 = new ItemPedido(teclado, 2);

        Pedido pedido = new Pedido();
        pedido.adicionarItem(item1);

        pedido.confirmar();

        assertThrows(IllegalStateException.class, () -> pedido.cancelar());

        assertEquals(StatusPedido.CONFIRMADO, pedido.getStatus());
        assertEquals(8, teclado.getQuantidadeEstoque());


    }

}
