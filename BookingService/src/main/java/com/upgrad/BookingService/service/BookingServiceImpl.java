package com.upgrad.BookingService.service;

import com.upgrad.BookingService.dao.BookingDao;
import com.upgrad.BookingService.dto.PaymentDto;
import com.upgrad.BookingService.entity.BookingInfoEntity;
import com.upgrad.BookingService.exceptions.invalidBookingIdException;
import com.upgrad.BookingService.exceptions.invalidPaymentModeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.stereotype.Service;

import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
public class BookingServiceImpl implements BookingService {
    @Autowired
    BookingDao bookingDao;

    @Autowired
    RestTemplate restTemplate;

    @Value("${api-gateway-url}")
    String apiGateway;

    @Autowired
    public void setBookingDao(BookingDao bookingDao) {
        this.bookingDao = bookingDao;
    }

    @Override
    public BookingInfoEntity addBooking(BookingInfoEntity bookingInfoEntity) {

        int totalDays;
        int noOfRooms = bookingInfoEntity.getNumOfRooms();
        int roomPrice;

        long difference = bookingInfoEntity.getToDate().getTime() - bookingInfoEntity.getFromDate().getTime();
        totalDays = (int) (difference / (1000 * 60 * 60 * 24));
        roomPrice = 1000 * noOfRooms * (totalDays);

        ArrayList<String> roomNumbers = getRandomNumbers(bookingInfoEntity.getNumOfRooms());
        String roomNumber = String.join(",", roomNumbers);

        bookingInfoEntity.setRoomNumbers(roomNumber);
        bookingInfoEntity.setRoomPrice(roomPrice);
        //Setting current Date as booked date
        bookingInfoEntity.setBookedOn(new Date());

        bookingDao.save(bookingInfoEntity);
        return bookingInfoEntity;

    }


    @Override
    public BookingInfoEntity getBookingData(Integer bookingID) {
        Optional<BookingInfoEntity> reqDetails = bookingDao.findById(bookingID);
        if (reqDetails.isPresent()) {
            return reqDetails.get();
        }
        return null;
    }


    @Override
    public BookingInfoEntity addPaymentData(int bookingId, PaymentDto paymentDto) throws invalidBookingIdException, invalidPaymentModeException {
        String PaymentServiceURL = apiGateway + "/payment/transaction";
        if ("UPI".equals(paymentDto.getPaymentMode()) || "CARD".equals(paymentDto.getPaymentMode())) {
            if (!bookingDao.existsById(bookingId)) {
                throw new invalidBookingIdException();
            }
            // Getting Booking Data based on BookingID
            BookingInfoEntity bookingData = getBookingData(bookingId);
            // Getting transaction Id from Payment Service
            Integer transactionId = restTemplate.postForObject(PaymentServiceURL,
                    paymentDto, Integer.class);
            //Updating the transaction ID received from Payment service in booking data
            bookingData.setTransactionId(transactionId);
            bookingDao.save(bookingData);
            String message = "Booking confirmed for user with aadhaar number: "
                    + bookingData.getAadharNumber()
                    + "    |    "
                    + "Here are the booking details:    " + bookingData.toString();

            //Printing message to console
            System.out.println(message);

            return bookingData;

        } else {
            throw new invalidPaymentModeException();
        }
    }

    //Method to generate Random room numbers
    public static ArrayList<String> getRandomNumbers(int count) {
        Random rand = new Random();
        int upperBound = 100;
        ArrayList<String> numberList = new ArrayList<String>();

        for (int i = 0; i < count; i++) {
            numberList.add(String.valueOf(rand.nextInt(upperBound)));
        }

        return numberList;
    }
}
