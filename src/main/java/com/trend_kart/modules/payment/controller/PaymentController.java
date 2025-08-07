package com.trend_kart.modules.payment.controller;

import com.trend_kart.modules.payment.dto.PaymentDTO;
import com.trend_kart.modules.payment.factory.PaymentGatewayFactory;
import com.trend_kart.modules.payment.factory.PaymentGatewayFactorySelector;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/payments")
public class PaymentController {
    private final PaymentGatewayFactorySelector paymentGatewayFactorySelector;

    public PaymentController(PaymentGatewayFactorySelector paymentGatewayFactorySelector) {
        this.paymentGatewayFactorySelector = paymentGatewayFactorySelector;
    }

    @PostMapping("/initiate")
    public ResponseEntity initiate(@RequestBody PaymentDTO paymentDTO) {
        PaymentGatewayFactory factory = paymentGatewayFactorySelector.get(paymentDTO.getGateway());
        factory.getPaymentProcessor().initiatePayment();
        return ResponseEntity.ok("Payment Initiated");
    }

    @PostMapping("/enquire")
    public ResponseEntity enquire(@RequestBody PaymentDTO paymentDTO) {
        PaymentGatewayFactory factory = paymentGatewayFactorySelector.get(paymentDTO.getGateway());
        factory.getEnquiryProcessor().enquire();
        return ResponseEntity.ok("Payment Enquiry Initiated");
    }

    @PostMapping("/refund")
    public ResponseEntity refund(@RequestBody PaymentDTO paymentDTO) {
        PaymentGatewayFactory factory = paymentGatewayFactorySelector.get(paymentDTO.getGateway());
        factory.getRefundProcessor().initiateRefund();
        return ResponseEntity.ok("Payment Refund Initiated");
    }
}
