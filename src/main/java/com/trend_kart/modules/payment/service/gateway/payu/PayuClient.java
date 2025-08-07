package com.trend_kart.modules.payment.service.gateway.payu;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class PayuClient {
    public void sendRequest() {
      log.info("PayuClient::sendRequest()");
    }
}
