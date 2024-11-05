package org.example.gasticountback.repository;

import org.example.gasticountback.entity.Balance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IBalanceRepository extends JpaRepository<Balance, Integer> {
}