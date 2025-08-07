package com.trend_kart.modules.payment.service.gateway.plural;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class PluralClient {
    public void sendRequest() {
        log.info("PluralClient::sendRequest()");
    }
}
