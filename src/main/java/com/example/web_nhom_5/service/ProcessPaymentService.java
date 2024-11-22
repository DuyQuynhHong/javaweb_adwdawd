package com.example.web_nhom_5.service;

import com.example.web_nhom_5.dto.response.ProcessPaymentResponse;

public interface ProcessPaymentService {
    ProcessPaymentResponse processPayment(long bookingId, long amount);
}
