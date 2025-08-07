package com.trend_kart.modules.payment.service.gateway.plural;

import com.trend_kart.modules.payment.service.EnquiryProcessor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class PluralEnquiryProcessor implements EnquiryProcessor {
    @Override
    public void enquire() {
        log.info("PluralEnquiryProcessor::enquire()");
    }
}
