package com.trend_kart.practice.multithreading;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TestThread extends Thread {
    public void run() {
        if (!Thread.currentThread().isInterrupted()) {
            log.info("TestThread::run::Triggered::{}", Thread.currentThread().getName());
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                throw new RuntimeException(e);
            }
        }
        log.info("TestThread::run::ExceutionCompleted::{}", Thread.currentThread().getName());
    }
}
