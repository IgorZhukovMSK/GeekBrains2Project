package Level2.Lesson5.Teacher;

import java.util.ArrayList;

public class CalculativeThread implements Runnable {

    public static int numCalcThreads = 0;
    private CalculativeThread previousThread;

    private ArrayList<Double> array;
    private int startPoint;
    private int endPoint;
    private int iterations;
    public Thread t;
    private int currentIteration;

    public CalculativeThread(ArrayList array, int startPoint, int endPoint, int iterations, CalculativeThread previousThread) {
        numCalcThreads++;

        this.array = array;
        this.startPoint = startPoint;
        this.endPoint = endPoint;
        this.iterations = iterations;
        this.previousThread = previousThread;

        this.t = new Thread(this, Integer.toString(numCalcThreads));
    }

    @Override
    public void run() {
        //System.out.println(this.startPoint+"; "+this.endPoint);
        smooth(this.array, this.startPoint, this.endPoint, this.iterations);
    }

    public int gerCurrentIteration() {
        return this.currentIteration;
    }

    private void smooth(ArrayList<Double> arr, int sP, int eP, int iter) {
        int operations = 0;
        double result;
        for (this.currentIteration = 0; this.currentIteration < iter; this.currentIteration++) {
            if (this.previousThread != null) {
                while (previousThread.gerCurrentIteration() <= this.currentIteration) {
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException ex) {
                    }
                }
            }

            for (int pos = sP; pos < eP; pos++) {
                if ((pos > 0) && (pos < this.array.size() - 1)) {
                    result = (arr.get(pos - 1) + arr.get(pos) + arr.get(pos + 1)) / 3.0;
                    arr.set(pos, result);
                    operations++;
                }
            }
        }
        System.out.println(this.t.getName() + " поток завершён. " + operations + " операций");
    }
}