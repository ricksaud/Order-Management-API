package com.ricksaud.order_management;

import org.hibernate.cache.spi.support.AbstractReadWriteAccess;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.math.BigDecimal;

@SpringBootApplication
public class OrderManagementApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(OrderManagementApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		Produto produto = new Produto(
				"Pijama verde musgo",
				new BigDecimal("50"),
				10
		);

		ItemPedido item = new ItemPedido(produto, 3);
		Pedido pedido = new Pedido();

		System.out.println("Status inicial: " + pedido.getStatus());

// Tentar confirmar um pedido vazio
		try {
			pedido.confirmar();
			System.out.println("FALHA: confirmou pedido vazio.");
		} catch (IllegalStateException e) {
			System.out.println("Erro esperado: " + e.getMessage());
		}

		System.out.println("Status após tentativa: " + pedido.getStatus());

// Adicionar item e confirmar
		pedido.adicionarItem(item);
		pedido.confirmar();

		System.out.println("Status após confirmar: " + pedido.getStatus());
		System.out.println("Total: " + pedido.calcularTotal());

// Tentar adicionar item após confirmar
		try {
			pedido.adicionarItem(item);
			System.out.println("FALHA: adicionou item após confirmar.");
		} catch (IllegalStateException e) {
			System.out.println("Erro esperado: " + e.getMessage());
		}

		System.out.println("Total após tentativa: " + pedido.calcularTotal());

// Tentar confirmar novamente
		try {
			pedido.confirmar();
			System.out.println("FALHA: confirmou novamente.");
		} catch (IllegalStateException e) {
			System.out.println("Erro esperado: " + e.getMessage());
		}

// Tentar cancelar um pedido confirmado
		try {
			pedido.cancelar();
			System.out.println("FALHA: cancelou pedido confirmado.");
		} catch (IllegalStateException e) {
			System.out.println("Erro esperado: " + e.getMessage());
		}

		System.out.println("Status preservado: " + pedido.getStatus());

// Criar outro pedido e cancelar
		Pedido outroPedido = new Pedido();
		outroPedido.cancelar();

		System.out.println("Outro pedido: " + outroPedido.getStatus());

// Tentar adicionar item ao pedido cancelado
		try {
			outroPedido.adicionarItem(item);
			System.out.println("FALHA: adicionou item ao pedido cancelado.");
		} catch (IllegalStateException e) {
			System.out.println("Erro esperado: " + e.getMessage());
		}

// Tentar confirmar um pedido cancelado
		try {
			outroPedido.confirmar();
			System.out.println("FALHA: confirmou pedido cancelado.");
		} catch (IllegalStateException e) {
			System.out.println("Erro esperado: " + e.getMessage());
		}

		System.out.println("Status preservado: " + outroPedido.getStatus());
		System.out.println("Total do cancelado: " + outroPedido.calcularTotal());




	}
}
