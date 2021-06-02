package main;

import java.util.Random;

public class RedisTest {
    private static final int USERS = 20;

    private static final int SLEEP = 1000; // 1 секунда

    public static void main(String[] args) throws InterruptedException {

        RedisStorage redis = new RedisStorage();
        redis.init();

        for(;;) {
            for (int count = 1; count < 10; count++) {
                String user = redis.getUsers().pollFirst();
                System.out.println("На главной странице показываем пользователя " + user);
                Thread.sleep(SLEEP);
                redis.addToEndOfList(user);
            }
            String user = String.valueOf(new Random().nextInt(USERS));
            System.out.println("Пользователь " + user + " оплатил услугу");
            Thread.sleep(SLEEP);
            redis.addToEndOfList(user);
        }
    }
}
