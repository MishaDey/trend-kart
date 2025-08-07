package com.trend_kart.practice;

import com.trend_kart.modules.audit.entity.AuditLog;
import com.trend_kart.modules.audit.service.AuditLogService;
import com.trend_kart.practice.multithreading.TestRunnable;
import com.trend_kart.practice.multithreading.TestThread;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@Slf4j
public class TestRunner implements CommandLineRunner {
    public final AuditLogService auditLogService;

    TestRunner(final AuditLogService auditLogService) {
        this.auditLogService = auditLogService;
    }

    public void run(String... args) throws Exception {
        log.info("TestRunner::run::Triggered");
    }

    public void testAuditLogCreation() {
        AuditLog auditLog = AuditLog.builder()
                .action("CREATE")
                .userId("12345")
                .userName("test_user")
                .entityType("Product")
                .entityId("67890")
                .previousVersion(Map.of("name", "Test Product", "price", "19.99"))
                .currentVersion(Map.of("name", "Test Product1", "price", "20.00"))
                .build();
        auditLogService.saveAuditLog(auditLog);
    }

    public void testThreadCreation() throws InterruptedException {
        Thread thread1 = new TestThread();
        Thread thread2 = new Thread(new TestRunnable());
        // Up to this point there will be no output

        log.info("CurrentThread::{}", Thread.currentThread().getName());
        // .run() will also be triggered from the current thread.
        thread1.run();
        thread2.run();

        // .start() - will spin-off a new thread and run it
        thread1.start();
        thread2.start();

        // Example of how different threads pick up tasks using delay
        Runnable task = () -> {
            log.info("Started::CurrentThread::{}", Thread.currentThread().getName());
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            log.info("Finished::CurrentThread::{}", Thread.currentThread().getName());
        };
        // Nothing will happen till this point

        for (int ittr = 0; ittr < 5; ittr++) {
            new Thread(task).start();
        }

        // Testing .join() -> Stopping the Execution
        log.info("Testing .join() -> Stopping the Execution");
        thread1.start();
        thread1.join();
        thread2.start();

        // setting a daemon thread - means it will not block the JVM from exiting
        thread2.setDaemon(true);

        // interrupt() -> safe alternative for stop()
        thread1.interrupt();
    }
}
