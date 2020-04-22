package Level2.Lesson5.Teacher;



import java.util.ArrayList;
import java.util.Random;

public class ArrayProcessing {

    ArrayList<Double> array;
    private int iterations;
    ArrayList<CalculativeThread> threads;

    public ArrayProcessing() throws InterruptedException {

        Random random = new Random();

        this.array = new ArrayList<>();
        for (int i = 0; i < 1000000; i++) {
            array.add(random.nextDouble() * 10.0);
        }

        this.iterations = 1000;

        int proc = Runtime.getRuntime().availableProcessors();
        System.out.println("processors: " + proc);
        int arrayLengthOnThread = (int) (Math.ceil((double) this.array.size() / (double) proc));

        threads = new ArrayList<>();
        for (int i = 0; i < proc; i++) {
            CalculativeThread r;
            if (i == 0) {
                r = new CalculativeThread(this.array,
                        i * arrayLengthOnThread,
                        i * arrayLengthOnThread + arrayLengthOnThread,
                        this.iterations,
                        null
                );
            } else {
                r = new CalculativeThread(this.array,
                        i * arrayLengthOnThread,
                        i * arrayLengthOnThread + arrayLengthOnThread,
                        this.iterations,
                        threads.get(i - 1)
                );
            }
            threads.add(r);
        }

        //printArray(this.array);
    }

    private void startTask() {
        System.out.println("start");
        long timeout = System.currentTimeMillis();

        for (int i = 0; i < threads.size(); i++) {
            threads.get(i).t.start();
            System.out.println(threads.get(i).t.getName() + " поток начат");
        }

        for (int i = 0; i < threads.size(); i++) {
            try {
                threads.get(i).t.join();
            } catch (InterruptedException ex) {
            }
        }
        System.out.println("finish: " + (System.currentTimeMillis() - timeout) + "ms.");
    }

    public static void main(String[] args) throws InterruptedException {
        ArrayProcessing ap = new ArrayProcessing();
        ap.startTask();
    }
}
