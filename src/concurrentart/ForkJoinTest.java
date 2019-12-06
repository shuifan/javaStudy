package concurrentart;

import java.util.concurrent.*;

/**
 * @author fandong
 * @create 2018/7/19
 */
public class ForkJoinTest {

    public static class CountTask extends RecursiveTask<Long>{
        private static final long THRESHOLD = 1000;
        private long start;
        private long end;

        public CountTask(long start, long end) {
            this.start = start;
            this.end = end;
        }

        @Override
        protected Long compute() {
            long sum = 0;
            boolean directCompute = (end - start) <= THRESHOLD;
            if (directCompute){
                for (long i = start; i <= end; i++){
                    sum += i;
                }
            }else {
                long middle = (start + end) / 2;
                CountTask t1 = new CountTask(start, middle);
                CountTask t2 = new CountTask(middle + 1, end);

                t1.fork();
                t2.fork();

                Long join1 = t1.join();
                Long join2 = t2.join();

                sum = join1 + join2;
            }
            return sum;
        }
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        long max = 100000000L;
        long sum = 0L;
        long sTime = System.currentTimeMillis();
        for (long i = 0L; i <= max; i++){
            sum += i;
        }

    }
}
