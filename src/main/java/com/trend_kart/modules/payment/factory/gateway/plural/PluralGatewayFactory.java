package com.trend_kart.modules.payment.factory.gateway.plural;

import com.trend_kart.modules.payment.factory.PaymentGatewayFactory;
import com.trend_kart.modules.payment.service.EnquiryProcessor;
import com.trend_kart.modules.payment.service.PaymentProcessor;
import com.trend_kart.modules.payment.service.RefundProcessor;
import com.trend_kart.modules.payment.service.gateway.plural.PluralEnquiryProcessor;
import com.trend_kart.modules.payment.service.gateway.plural.PluralPaymentProcessor;
import com.trend_kart.modules.payment.service.gateway.plural.PluralRefundProcessor;
import org.springframework.stereotype.Component;

@Component("plural-gateway")
public class PluralGatewayFactory implements PaymentGatewayFactory {
    private final PluralPaymentProcessor pluralPaymentProcessor;
    private final PluralEnquiryProcessor pluralEnquiryProcessor;
    private final PluralRefundProcessor pluralRefundProcessor;


    public PluralGatewayFactory(PluralPaymentProcessor pluralPaymentProcessor, PluralEnquiryProcessor pluralEnquiryProcessor, PluralRefundProcessor pluralRefundProcessor) {
        this.pluralPaymentProcessor = pluralPaymentProcessor;
        this.pluralEnquiryProcessor = pluralEnquiryProcessor;
        this.pluralRefundProcessor = pluralRefundProcessor;
    }


    @Override
    public PaymentProcessor getPaymentProcessor() {
        return pluralPaymentProcessor;
    }

    @Override
    public EnquiryProcessor getEnquiryProcessor() {
        return pluralEnquiryProcessor;
    }

    @Override
    public RefundProcessor getRefundProcessor() {
        return pluralRefundProcessor;
    }
}
