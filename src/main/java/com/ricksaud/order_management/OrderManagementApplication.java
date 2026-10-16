package com.ricksaud.order_management;
import com.ricksaud.order_management.domain.Produto;
import com.ricksaud.order_management.repository.ProdutoRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.math.BigDecimal;
import java.util.Optional;


@SpringBootApplication
public class OrderManagementApplication implements CommandLineRunner {


	private final ProdutoRepository produtoRepository;

    public OrderManagementApplication(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }


    public static void main(String[] args) {
		SpringApplication.run(OrderManagementApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {



//

	}
}
