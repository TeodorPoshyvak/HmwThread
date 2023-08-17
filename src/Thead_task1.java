public class Thead_task1 {

    public static void main(String[] args) throws InterruptedException {
        int time = 0;
        Thread thread1 = new Thread(() ->
        {
            try {
                while (true) {
                    Thread.sleep(5200);
                    System.out.println("Минуло 5 секунд");
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        thread1.start();

        while (true) {
            System.out.println("time work thread_" + Thread.currentThread().getName() + " " + time + "sec");
            Thread.sleep(1000);
            time++;
        }
    }

}
