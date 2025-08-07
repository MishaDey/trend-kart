package com.trend_kart.modules.payment.factory;

import org.springframework.stereotype.Component;

import java.util.Map;

// This is the abstract factory class
@Component
public class PaymentGatewayFactorySelector {
    private final Map<String, PaymentGatewayFactory> paymentGatewayFactoryMap;

    public PaymentGatewayFactorySelector(Map<String, PaymentGatewayFactory> paymentGatewayFactoryMap) {
        this.paymentGatewayFactoryMap = paymentGatewayFactoryMap;
    }

    public PaymentGatewayFactory get(String gateway) {
        return paymentGatewayFactoryMap.get(gateway + "-gateway");
    }
}
