package com.trend_kart.modules.payment.service.gateway.plural;

import com.trend_kart.modules.payment.service.PaymentProcessor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class PluralPaymentProcessor implements PaymentProcessor {
    private final PluralClient pluralClient;

    public PluralPaymentProcessor(PluralClient pluralClient) {
        this.pluralClient = pluralClient;
    }

    @Override
    public void initiatePayment() {
        log.info("PluralPaymentProcessor::initiatePayment()");
        pluralClient.sendRequest();
    }
}
