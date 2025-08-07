package com.trend_kart.modules.payment.service.gateway.plural;

import com.trend_kart.modules.payment.service.RefundProcessor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class PluralRefundProcessor implements RefundProcessor {
    @Override
    public void initiateRefund() {
        log.info("PluralRefundProcessor::initiateRefund()");
    }
}
