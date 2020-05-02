package Level2.Lesson5_Chat;

public class Main {

        private static long a;
        private static long b;
        private static long c;
        private static final Object obj = new Object();


    private synchronized static void incAll() {
        for (int i = 0; i < 1000000; i ++){
            a = a + 1;
            b = b + 1;
            c = c + 1;
        }
        String vars = String.format("a=%d, b=%d, c=%d", a,b,c);
        System.out.println(vars);
    }
    private synchronized static void incAll1() {
        for (int i = 0; i < 1000000; i ++){
            a = a + 2;
            b = b + 2;
            c = c + 2;
        }
        String vars = String.format("a=%d, b=%d, c=%d", a,b,c);
        System.out.println(vars);
    }
    private synchronized static void incAll2() {
        for (int i = 0; i < 1000000; i ++){
            a = a + 3;
            b = b + 3;
            c = c + 3;
        }
        String vars = String.format("a=%d, b=%d, c=%d", a,b,c);
        System.out.println(vars);
    }
    private synchronized static void incAll3() {
        for (int i = 0; i < 1000000; i ++){
            a = a + 4;
            b = b + 4;
            c = c + 4;
        }
        String vars = String.format("a=%d, b=%d, c=%d", a,b,c);
        System.out.println(vars);
    }
    private synchronized static void incAll4() {
        for (int i = 0; i < 1000000; i ++){
            a = a + 5;
            b = b + 5;
            c = c + 5;
        }
        String vars = String.format("a=%d, b=%d, c=%d", a,b,c);
        System.out.println(vars);
    }

    public static void main(String[] args) {
                Runnable r = new Runnable(){
                    @Override
                    public void run(){
                        incAll();
                        incAll1();
                        incAll2();
                        incAll3();
                        incAll4();
                        System.out.println("eeeee");
                    }
                };

                new Thread(r, "Thread #1").start();
                new Thread(r, "Thread #2").start();
                new Thread(r, "Thread #3").start();
                new Thread(r, "Thread #4").start();
                new Thread(r, "Thread #5").start();
    }

    private static void threading() {
        MyThread t0 = new MyThread(1);
        MyThread t1 = new MyThread(2);

        try {
            t0.join();
            t1.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        System.out.println("5");
    }


}
