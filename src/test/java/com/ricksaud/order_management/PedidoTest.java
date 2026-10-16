package com.ricksaud.order_management;

import com.ricksaud.order_management.domain.ItemPedido;
import com.ricksaud.order_management.domain.Pedido;
import com.ricksaud.order_management.domain.Produto;
import com.ricksaud.order_management.domain.StatusPedido;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class PedidoTest {

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

    @Test
    public void deveRejeitarAdicaoDeItemEmPedidoConfirmado(){
        Produto teclado = new Produto("Teclado Mecanico", new BigDecimal("120.00"), 10);

        ItemPedido item1 = new ItemPedido(teclado, 2);
        ItemPedido item2 = new ItemPedido(teclado, 5);


        Pedido pedido = new Pedido();
        pedido.adicionarItem(item1);

        pedido.confirmar();

        assertThrows(IllegalStateException.class, () -> pedido.adicionarItem(item2));


        assertEquals(new BigDecimal("240.00"), pedido.calcularTotal());
        assertEquals(StatusPedido.CONFIRMADO, pedido.getStatus());
        assertEquals(8, teclado.getQuantidadeEstoque());



    }

    @Test
    public void deveRejeitarAdicaoDeItemEmPedidoCancelado(){
        Produto teclado = new Produto("Teclado Mecanico", new BigDecimal("120.00"), 10);
        ItemPedido item1 = new ItemPedido(teclado, 2);
        ItemPedido item2 = new ItemPedido(teclado, 5);

        Pedido pedido = new Pedido();
        pedido.adicionarItem(item1);

        pedido.cancelar();

        assertThrows(IllegalStateException.class, () -> pedido.adicionarItem(item2));


        assertEquals(StatusPedido.CANCELADO, pedido.getStatus());
        assertEquals(new BigDecimal("240.00"), pedido.calcularTotal());
        assertEquals(10, teclado.getQuantidadeEstoque());

    }


    @Test
    public void deveRejeitarConfirmacaoDePedidoCancelado(){

        Produto teclado = new Produto("Teclado Mecanico", new BigDecimal("120.00"), 10);
        ItemPedido item1 = new ItemPedido(teclado, 2);

        Pedido pedido = new Pedido();
        pedido.adicionarItem(item1);

        pedido.cancelar();

        assertThrows(IllegalStateException.class, () -> pedido.confirmar());

        assertEquals(StatusPedido.CANCELADO, pedido.getStatus());
        assertEquals(10, teclado.getQuantidadeEstoque());

    }



    @Test
    public void rejeitarItemNuloEmPedidoPendente(){
        Produto chaves = new Produto("chaves", new BigDecimal("10.00"), 10);
        ItemPedido item1 = new ItemPedido(chaves, 2);

        Pedido pedido = new Pedido();

        pedido.adicionarItem(item1);

        assertThrows(IllegalArgumentException.class, () -> pedido.adicionarItem(null));

        assertEquals(new BigDecimal("20.00"), pedido.calcularTotal());
        assertEquals(StatusPedido.PENDENTE, pedido.getStatus());
        assertEquals(10, chaves.getQuantidadeEstoque());




    }

    @Test
    public void deveConfirmarPedidoComDoisItensDoMesmoProduto(){
        Produto chaves = new Produto("chaves", new BigDecimal("10.00"), 10);
        ItemPedido item1 = new ItemPedido(chaves, 2);
        ItemPedido item2 = new ItemPedido(chaves, 3);

        Pedido pedido = new Pedido();

        pedido.adicionarItem(item1);
        pedido.adicionarItem(item2);

        pedido.confirmar();

        assertEquals(StatusPedido.CONFIRMADO, pedido.getStatus());
        assertEquals(5, chaves.getQuantidadeEstoque());
    }
}
