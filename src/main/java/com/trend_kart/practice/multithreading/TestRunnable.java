package com.trend_kart.practice.multithreading;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TestRunnable implements Runnable {
    public void run() {
        log.info("TestRunnable::run::Triggered::{}", Thread.currentThread().getName());
    }
 }
