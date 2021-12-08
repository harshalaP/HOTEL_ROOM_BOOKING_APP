package com.upgrad.PaymentService.Entity;

import javax.persistence.*;

@Entity
@Table(name = "transaction")
public class TransactionDetailsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer transactionId;
    @Column
    private String paymentMode;
    @Column(nullable = false)
    private int bookingId;
    @Column
    private String upiId;
    @Column
    private String cardNumber;

    public TransactionDetailsEntity() {
    }

    public TransactionDetailsEntity(int transactionId, String paymentMode, int bookingId, String upiId, String cardNumber) {
        this.transactionId = transactionId;
        this.paymentMode = paymentMode;
        this.bookingId = bookingId;
        this.upiId = upiId;
        this.cardNumber = cardNumber;
    }

    public Integer getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Integer transactionId) {
        this.transactionId = transactionId;
    }

    public String getPaymentMode() {
        return paymentMode;
    }

    public void setPaymentMode(String paymentMode) {
        this.paymentMode = paymentMode;
    }

    public int getBookingId() {
        return bookingId;
    }

    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }

    public String getUpiId() {
        return upiId;
    }

    public void setUpiId(String upiId) {
        this.upiId = upiId;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    @Override
    public String toString() {
        return "TransactionDetailsEntity{" +
                "transactionId=" + transactionId +
                ", paymentMode='" + paymentMode + '\'' +
                ", bookingId=" + bookingId +
                ", upiId='" + upiId + '\'' +
                ", cardNumber='" + cardNumber + '\'' +
                '}';
    }
}
