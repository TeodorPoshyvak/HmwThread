package FizzBazz;
import java.util.List;


public class FizzBazzTest {
    public static void main(String[] args) throws InterruptedException {
        Object monitor = new Object();

        FizzBazz npFizz = new FizzBazz((n) -> FizzBazz.fizz());
        FizzBazz npBazz = new FizzBazz((n) -> FizzBazz.bazz());
        FizzBazz npFizzBAzz = new FizzBazz((n) -> FizzBazz.fizzBazz());
        FizzBazz npNotFizzBAzz = new FizzBazz((n) -> FizzBazz.write());

        List<FizzBazz> threads = List.of(npBazz, npFizz, npFizzBAzz, npNotFizzBAzz);

        for (FizzBazz t : threads) {
            t.start();
        }


        for (int i = 0; i < 100; i++) {
            for (FizzBazz t : threads) {
                t.number(i);
            }

            while (true) {
                int processed = 0;
                for (FizzBazz t : threads) {
                    if (t.isNProcessed()) {
                        processed++;
                    }
                }
                if (processed == 4) {
                    break;
                }
            }
        }
    }
}
