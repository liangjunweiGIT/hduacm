package com.ljw.acm;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * 并行执行
 *
 * @author duke.fu
 */
public class NaivelyConcurrentFileSize {

    private long getTotalSizeOfFilesInDir(final ExecutorService service, final File file) throws InterruptedException, ExecutionException, TimeoutException {
        if (file.isFile()) {
            return file.length();
        }

        final File[] children = file.listFiles();

        long total = 0;
        if (null != children) {
            final List<Future<Long>> partialTotalFutures = new ArrayList<>();
            for (final File child : children) {
                partialTotalFutures.add(service.submit(new Callable<Long>() {
                    @Override
                    public Long call() throws InterruptedException, TimeoutException, ExecutionException {
                        return getTotalSizeOfFilesInDir(service, child);
                    }
                }));
            }

            for (Future<Long> partialTotalFuture : partialTotalFutures) {
                total += partialTotalFuture.get(100, TimeUnit.SECONDS);
            }
        }
        return total;
    }

    private long getTotalSizeOfFile(final String fileName) throws InterruptedException, ExecutionException, TimeoutException {
        final ExecutorService service = Executors.newFixedThreadPool(100);
        try {
            final long totalSizeOfFilesInDir = getTotalSizeOfFilesInDir(service, new File(fileName));
            return totalSizeOfFilesInDir;
        } finally {
            service.shutdown();
        }
    }

    public static void main(String[] args) throws InterruptedException, ExecutionException, TimeoutException {
        final long start = System.nanoTime();
        final long total = new NaivelyConcurrentFileSize().getTotalSizeOfFile("F:\\home");
        final long end = System.nanoTime();
        System.out.println("total size: " + total);
        System.out.println("time taken: " + (end - start) / 1.0e9);
    }

}
