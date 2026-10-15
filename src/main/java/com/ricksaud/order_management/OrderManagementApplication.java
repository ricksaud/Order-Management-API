package com.ricksaud.order_management;

import org.hibernate.cache.spi.support.AbstractReadWriteAccess;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
public class OrderManagementApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(OrderManagementApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

//
//	Produto camiseta = new Produto(
//			"Camiseta",
//			new BigDecimal("100.00"),
//			10
//	);
//
//	Produto calca = new Produto("Calca",
//			new BigDecimal("150.00"), 5);
//
//	ItemPedido item1 = new ItemPedido(camiseta, 2);
//	ItemPedido item2 = new ItemPedido(calca, 1);
//
//
//	Pedido pedido = new Pedido();
//
//		System.out.println("Total vazio: " + pedido.calcularTotal());
//
//		pedido.adicionarItem(item1);
//		System.out.println("Após camisetas: " + pedido.calcularTotal());
//
//		pedido.adicionarItem(item2);
//		System.out.println("Após calça: " + pedido.calcularTotal());
//
//		System.out.println("Status: " + pedido.getStatus());
//
//
//		pedido.confirmar();
//		System.out.println("Status: " + pedido.getStatus());
//
//		try{
//			pedido.adicionarItem(item1);
//		}catch (IllegalStateException e){
//			System.out.println("Erro esperado: " + e.getMessage());
//		}
//
//		System.out.println("Total: " + pedido.calcularTotal());
//
//
//		Pedido pedido2 = new Pedido();
//		pedido2.cancelar();
//		System.out.println("Status do pedido: " + pedido2.getStatus());
//
//		try{
//			pedido2.adicionarItem(item1);
//
//		} catch (IllegalStateException e) {
//			System.out.println("Erro esperado: " + e.getMessage());
//        }
//
//
//		Produto celular = new Produto("Motorola x5", new BigDecimal("2000.00"), 5);
//		ItemPedido itemCelular1 = new ItemPedido(celular, 3);
//		ItemPedido itemCelular2 = new ItemPedido(celular, 4);
//
//		int quantidadeNecessaria = itemCelular1.getQuantidade() + itemCelular2.getQuantidade();
//		System.out.println("Quantidade necessaria: " + quantidadeNecessaria);
//		System.out.println("Estoque disponivel: " + celular.getQuantidadeEstoque());
//
//
//		Map<Produto, Integer> quantidades = new HashMap<>();
//		quantidades.put(celular, itemCelular1.getQuantidade());
//
//		int quantidadeAtual = quantidades.get(celular);
//		quantidades.put(celular, quantidadeAtual + itemCelular2.getQuantidade());
//		System.out.println(quantidades.get(celular));

//		Produto celular = new Produto(
//				"Motorola x5", new BigDecimal("2000.00"), 10
//		);
//
//		ItemPedido item1 = new ItemPedido(celular, 3);
//		ItemPedido item2 = new ItemPedido(celular, 4);
//
//		Pedido pedido = new Pedido();
//		pedido.adicionarItem(item1);
//		pedido.adicionarItem(item2);
//
//		System.out.println("Status antes: " + pedido.getStatus());
//
//		try {
//			pedido.confirmar();
//		} catch (IllegalStateException e) {
//			System.out.println("Erro: " + e.getMessage());
//		}
//
//		System.out.println("Status depois: " + pedido.getStatus());
//		System.out.println("Estoque depois: " + celular.getQuantidadeEstoque());


//		Produto camiseta = new Produto("Camiseta", new BigDecimal("50.00"), 10);
//		Produto celular = new Produto("Celular", new BigDecimal("5000.00"), 1);
//
//
//		ItemPedido camisetaPedido = new ItemPedido(camiseta, 2);
//		ItemPedido celularPedido = new ItemPedido(celular, 3);
//
//		Pedido pedido1 = new Pedido();
//		pedido1.adicionarItem(camisetaPedido);
//		pedido1.adicionarItem(celularPedido);
//
//		try{
//			pedido1.confirmar();
//		} catch (IllegalStateException e){
//			System.out.println("Erro esperado: " + e.getMessage());
//
//		}
//
//		System.out.println(pedido1.getStatus());
//		System.out.println(camiseta.getQuantidadeEstoque());
//		System.out.println(celular.getQuantidadeEstoque());



















	}
}
