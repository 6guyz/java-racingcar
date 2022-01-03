package racingcar;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class CarRacing {
    private static CarRacing carRacing;
    private Scanner scanner;
    private CarUtils carUtils;
    private List<Car> cars = new ArrayList<Car>();
    private List<String> winners = new ArrayList<String>();


    private CarRacing(Scanner scanner) {
        this.scanner = scanner;
        this.carUtils = new CarUtils();
    }

    public static synchronized CarRacing getInstance(Scanner scanner) {
        if (carRacing == null) {
            carRacing = new CarRacing(scanner);
        }
        return carRacing;
    }

    public void play() {
        String[] names = {};
        int cnt = 0;
        Boolean isFinished = false;
        Boolean isNameValid = false;

        // 이름, 횟수
        while (!isFinished) {
            if (!isNameValid) {
                names = getCarNames();
                if (names.length != 0) {
                    isNameValid = true;
                }
            }

            cnt = getUserCnt();
            if (cnt != 0) {
                isFinished = true;
            }
        }

        // set cars
        setCars(names);

        // start
        for (Car car : cars) {
            for (int k = 0; k < cnt; k++) {
                car.goForward(carUtils.getRandomNumber());
            }
        }

        // 우승자 선발
        List<Integer> cnts = new ArrayList();
        for (Car car : cars) {
            cnts.add(car.getPosition());
        }
        int max = Collections.max(cnts);
        for (Car car : cars) {
            if (car.getPosition() == max) {
                winners.add(car.getName());
            }
        }

        // 결과 출력
        String tmp = String.join(",", winners);
        System.out.print("최종 우승자 : " + tmp);

    }

    private void setCars(String[] names) {
        for (int i = 0; i < names.length; i++) {
            Car car = new Car(names[i]);
            cars.add(car);
        }
    }

    private int getUserCnt() {
        int cnt = 0;
        try {
            System.out.println("시도할 회수는 몇회인가요?");
            String cnt_str = scanner.nextLine();
            carUtils.getCountValid(cnt_str);
            cnt = Integer.parseInt(cnt_str);
        } catch(IllegalArgumentException e) {
            e.printStackTrace();
        }

        return cnt;
    }

    private String[] getCarNames() {
        String[] names = {};
        try {
            System.out.println("경주할 자동차 이름을 입력하세요.(이름은 쉼표(,) 기준으로 구분)");
            String names_str = scanner.nextLine();
            names = names_str.split(",");
            carUtils.getNameValid(names);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }

        return names;
    }


}
