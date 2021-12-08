package com.upgrad.PaymentService.Dao;

import com.upgrad.PaymentService.Entity.TransactionDetailsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentDao extends JpaRepository<TransactionDetailsEntity,Integer> {
}
