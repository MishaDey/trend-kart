package com.trend_kart.modules.payment.service.gateway.payu;

import com.trend_kart.modules.payment.service.RefundProcessor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class PayuRefundProcessor implements RefundProcessor {
    @Override
    public void initiateRefund() {
        log.info("PayuRefundProcessor::initiateRefund()");
    }
}
