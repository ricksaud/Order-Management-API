package com.ricksaud.order_management.repository;

import com.ricksaud.order_management.domain.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
}
