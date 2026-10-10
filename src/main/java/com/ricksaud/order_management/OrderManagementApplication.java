package com.ricksaud.order_management;

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

	Produto produto1 = new Produto(1L, "   Teclado Mecanico   ", new BigDecimal("349.90"),15);


		System.out.println(produto1.getNome() + " " + produto1.getQuantidadeEstoque());



		// Teste 2
		System.out.println("Estoque Inicial:" + produto1.getQuantidadeEstoque());
		produto1.adicionarEstoque(  5);
		System.out.println("Após adicionar:" + produto1.getQuantidadeEstoque());
		produto1.removerEstoque(8);
		System.out.println("Após remover: " + produto1.getQuantidadeEstoque());


		try {

			produto1.removerEstoque(20);

        } catch(IllegalArgumentException e){
			System.out.println("Erro capturado: " + e.getMessage());

		}
		System.out.println("Estoque após a tentativa:  " + produto1.getQuantidadeEstoque());





	}
}
