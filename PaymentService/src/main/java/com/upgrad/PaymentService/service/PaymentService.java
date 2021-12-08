package com.upgrad.PaymentService.service;

import com.upgrad.PaymentService.Dto.PaymentDto;
import com.upgrad.PaymentService.Entity.TransactionDetailsEntity;

public interface PaymentService {

    int addPaymentDetails(PaymentDto paymentDto);
    TransactionDetailsEntity getPaymentDetails(Integer transactionId);
}
