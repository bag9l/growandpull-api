package com.growandpull.api.service;

import com.growandpull.api.dto.payment.CompletedOrder;
import com.growandpull.api.dto.payment.PaymentOrder;
import com.growandpull.api.dto.payment.PaymentRequest;

public interface PayPalService {
    PaymentOrder createPayment(PaymentRequest paymentRequest);
    CompletedOrder completePayment(String token);
}
