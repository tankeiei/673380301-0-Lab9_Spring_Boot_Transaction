package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.DepositTransaction;

@Repository
public interface DepositRepository extends JpaRepository<DepositTransaction, Long> {
}
