package com.upgrad.PaymentService.service;

import com.upgrad.PaymentService.Dao.PaymentDao;
import com.upgrad.PaymentService.Dto.PaymentDto;
import com.upgrad.PaymentService.Entity.TransactionDetailsEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PaymentServiceImpl implements PaymentService {
    @Autowired

    PaymentDao paymentDao;

     @Override
      public int addPaymentDetails(PaymentDto paymentDto) {
        TransactionDetailsEntity transactionDetailsEntity =new TransactionDetailsEntity();
        transactionDetailsEntity.setTransactionId(paymentDto.getTransactionId());
        transactionDetailsEntity.setPaymentMode(paymentDto.getPaymentMode());
        transactionDetailsEntity.setCardNumber(paymentDto.getCardNumber());
        transactionDetailsEntity.setUpiId(paymentDto.getUpiId());
        transactionDetailsEntity.setBookingId(paymentDto.getBookingId());
                paymentDao.save(transactionDetailsEntity);

        return transactionDetailsEntity.getTransactionId();
    }

    //METHOD TO GET PAYMENT DETAILS FOR GIVEN TRANSACTION ID
    @Override
    public TransactionDetailsEntity getPaymentDetails(Integer transactionId) {
        Optional<TransactionDetailsEntity> reqDetails = paymentDao.findById(transactionId);
        if (reqDetails.isPresent()) {
            return reqDetails.get();
        }
        return null;
    }
}
