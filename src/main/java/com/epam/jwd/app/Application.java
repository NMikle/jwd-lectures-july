package com.epam.jwd.app;

import com.epam.jwd.concurrency.RaceCounter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class Application {

    private static final Logger LOG = LogManager.getLogger(Application.class);

    static int x = 0;
    static int y = 0;
    static int a = 0;
    static int b = 0;

    public static void main(String[] args) {
//        List<Employee> employees = new ArrayList<>();
//        final List<Employee> synchronizedEmployees = Collections.synchronizedList(employees);
//        new ConcurrentLinkedQueue<>().poll(); // returns null if empty

        final ExecutorService executorService = Executors.newFixedThreadPool(2);

        final Future<Integer> nResult = executorService.submit(() -> workExceptionally(false));
        final Future<Integer> eResult = executorService.submit(() -> workExceptionally(true));
        //some other work

        boolean finished = false;
        try {
            while (!finished) {
                Thread.sleep(100);
                finished = logResult(nResult);
                finished |= logResult(eResult);
            }

//        executorService.shutdownNow();
            executorService.shutdown(); //new tasks not accepted
            if (!executorService.awaitTermination(5, TimeUnit.SECONDS)) {
                LOG.info("Tasks were not terminated. Shutting down now.");
                executorService.shutdownNow();
            }
            LOG.info("Shutdown success");
        } catch (InterruptedException e) {
            LOG.warn("interrupted main thread", e);
            Thread.currentThread().interrupt();
            executorService.shutdownNow();
        }
    }

    private static boolean logResult(Future<Integer> possibleResult) throws InterruptedException {
        if (possibleResult.isDone()) {
            try {
                final Integer result = possibleResult.get();
                LOG.info("Execution properly finished. Result: {}", result);
            } catch (ExecutionException e) {
                LOG.error("Execution finished exceptionally", e);
            }
            return true;
        } else {
            LOG.info("still not completed");
            return false;
        }
    }

    private static int workExceptionally(boolean throwExc) throws InterruptedException {
        final RaceCounter counter = new RaceCounter();
        for (int i = 0; i < 1_000_000; i++) {
            if (Thread.currentThread().isInterrupted()) {
                LOG.warn("Received interruption request, interrupting the thread.");
                throw new InterruptedException();
            }
            counter.increment();
        }
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            LOG.warn("Interrupted exception occurred", e);
            Thread.currentThread().interrupt();
            throw e;
        }
        LOG.info("task completed");
        if (throwExc) {
            throw new IllegalArgumentException("argument should be false");
        }
        return counter.getCount();
    }

}
