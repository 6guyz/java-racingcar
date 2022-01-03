package racingcar;

import java.util.Scanner;

public class Application {
    static final Scanner scanner = new Scanner(System.in);
    static final CarRacing carRacing = CarRacing.getInstance(scanner);

    public static void main(String[] args) {
        carRacing.play();
    }
}
