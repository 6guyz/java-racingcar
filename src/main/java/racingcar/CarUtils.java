package racingcar;

public class CarUtils {

    public boolean getNameValid(String[] names) {
        for (String name : names) {
            if (name.length() > 5) {
                throw new IllegalArgumentException("[ERROR] 자동차 이름은 5자 이하만 가능하다.");
            }
        }
        return true;
    }

    public boolean getCountValid(String cnt) {
        try {
            Integer.parseInt(cnt);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("[ERROR] 시도 횟수는 숫자여야 한다.");
        }

        return true;
    }

    public int getRandomNumber() {
        return (int)(Math.random() * 10);
    }
}
