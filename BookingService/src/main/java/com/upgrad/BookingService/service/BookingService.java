package com.upgrad.BookingService.service;

import com.upgrad.BookingService.dto.PaymentDto;
import com.upgrad.BookingService.entity.BookingInfoEntity;
import com.upgrad.BookingService.exceptions.invalidBookingIdException;
import com.upgrad.BookingService.exceptions.invalidPaymentModeException;

public interface BookingService {
    BookingInfoEntity addBooking(BookingInfoEntity bookingInfoEntity);
    BookingInfoEntity addPaymentData(int bookingId,PaymentDto paymentDto) throws invalidBookingIdException, invalidPaymentModeException;
    BookingInfoEntity getBookingData(Integer bookingID);

}
