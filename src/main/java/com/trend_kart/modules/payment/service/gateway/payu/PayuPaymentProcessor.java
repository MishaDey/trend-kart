package com.trend_kart.modules.payment.service.gateway.payu;

import com.trend_kart.modules.payment.service.PaymentProcessor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class PayuPaymentProcessor implements PaymentProcessor {
    private final PayuClient payuClient;

    public PayuPaymentProcessor(PayuClient payuClient) {
        this.payuClient = payuClient;
    }

    @Override
    public void initiatePayment() {
        log.info("PayuPaymentProcessor::initiatePayment()");
        payuClient.sendRequest();
    }
}
