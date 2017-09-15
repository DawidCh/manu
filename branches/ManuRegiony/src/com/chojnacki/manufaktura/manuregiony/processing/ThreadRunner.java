package com.chojnacki.manufaktura.manuregiony.processing;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Utility class which starts tasks.
 */
public class ThreadRunner {

    private static ExecutorService executorService = Executors.newFixedThreadPool(10);

    private ThreadRunner() {
    }
    public static void run(Runnable runnable) {
        executorService.submit(runnable);
    }

    public static void shutdown() {
        executorService.shutdownNow();
    }
}
