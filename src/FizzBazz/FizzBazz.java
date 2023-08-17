package FizzBazz;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Consumer;

public class FizzBazz extends Thread {
    private static volatile int n;
    private Consumer<Integer> processor;
    private AtomicBoolean isNProcessed = new AtomicBoolean(true);
    static List<String> ls = new CopyOnWriteArrayList<>();

    public FizzBazz(Consumer<Integer> processor) {
        this.processor = processor;
    }

    public boolean isNProcessed() {
        return isNProcessed.get();
    }

    public void number(int n) {
        this.n = n;
        isNProcessed.set(false);
    }

    public static void fizz() {
        if (n % 3 == 0 && n != 0) {
            ls.add("fizz");
        }
    }

    public static void bazz() {
        if (n % 5 == 0 && n != 0) {
            ls.add("buzz");
        }
    }

    public static void fizzBazz() {
        if (n % 3 == 0 && n % 5 == 0 && n != 0) {
            ls.add("fizzbuzz");
        }
    }

    public static void write() {
        if (ls.isEmpty()) {
            System.out.println(n);
        } else {
            for (String result : ls) {
                System.out.println(result);
            }
        }
        ls.clear();

    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            if (isNProcessed.get()) {
                continue;
            }

            processor.accept(n);
            isNProcessed.set(true);

        }
    }
}
