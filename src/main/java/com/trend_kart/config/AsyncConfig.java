package com.trend_kart.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

@Configuration
@EnableAsync
public class AsyncConfig implements AsyncConfigurer {
    @Override
    public Executor getAsyncExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(10); // Min number of threads that the pool will keep alive even if they are idle
        executor.setMaxPoolSize(50);  // Max threads in the pool
        executor.setQueueCapacity(100); // Queue if all the threads are busy
        executor.setThreadNamePrefix("TREND-KART-THREAD-");
        executor.initialize();
        return executor;
    }
}
