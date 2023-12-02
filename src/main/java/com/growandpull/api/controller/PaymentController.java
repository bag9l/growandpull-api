package com.growandpull.api.controller;

import com.growandpull.api.dto.payment.CompletedOrder;
import com.growandpull.api.dto.payment.PaymentOrder;
import com.growandpull.api.dto.payment.PaymentRequest;
import com.growandpull.api.service.PayPalService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/paypal")
public class PaymentController {
    private final PayPalService payPalService;

    @PostMapping(value = "/init")
    public PaymentOrder createPayment(
            @RequestBody PaymentRequest paymentRequest) {
        return payPalService.createPayment(paymentRequest);
    }

    @PostMapping(value = "/capture")
    public CompletedOrder completePayment(@RequestParam("token") String token) {
        return payPalService.completePayment(token);
    }
}
