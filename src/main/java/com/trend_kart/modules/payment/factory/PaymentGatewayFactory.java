package com.trend_kart.modules.payment.factory;

import com.trend_kart.modules.payment.service.EnquiryProcessor;
import com.trend_kart.modules.payment.service.PaymentProcessor;
import com.trend_kart.modules.payment.service.RefundProcessor;

public interface PaymentGatewayFactory {
    PaymentProcessor getPaymentProcessor();
    EnquiryProcessor getEnquiryProcessor();
    RefundProcessor getRefundProcessor();
}
