package com.trend_kart.modules.payment.factory.gateway.payu;


import com.trend_kart.modules.payment.factory.PaymentGatewayFactory;
import com.trend_kart.modules.payment.service.EnquiryProcessor;
import com.trend_kart.modules.payment.service.PaymentProcessor;
import com.trend_kart.modules.payment.service.RefundProcessor;
import com.trend_kart.modules.payment.service.gateway.payu.PayuEnquiryProcessor;
import com.trend_kart.modules.payment.service.gateway.payu.PayuPaymentProcessor;
import com.trend_kart.modules.payment.service.gateway.payu.PayuRefundProcessor;
import org.springframework.stereotype.Component;

@Component("payu-gateway")
public class PayuGatewayFactory implements PaymentGatewayFactory {
    private final PayuPaymentProcessor payuPaymentProcessor;
    private final PayuEnquiryProcessor payuEnquiryProcessor;
    private final PayuRefundProcessor payuRefundProcessor;

    public PayuGatewayFactory(PayuPaymentProcessor payuPaymentProcessor, PayuEnquiryProcessor payuEnquiryProcessor, PayuRefundProcessor payuRefundProcessor) {
        this.payuPaymentProcessor = payuPaymentProcessor;
        this.payuEnquiryProcessor = payuEnquiryProcessor;
        this.payuRefundProcessor = payuRefundProcessor;
    }

    @Override
    public PaymentProcessor getPaymentProcessor() {
        return payuPaymentProcessor;
    }

    @Override
    public EnquiryProcessor getEnquiryProcessor() {
        return payuEnquiryProcessor;
    }

    @Override
    public RefundProcessor getRefundProcessor() {
        return payuRefundProcessor;
    }
}
