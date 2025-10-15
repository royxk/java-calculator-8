package calculator;

import camp.nextstep.edu.missionutils.Console;

public class Application {
    public static void main(String[] args) {
        // TODO: 프로그램 구현

        // 1. 사용자에게 입력 안내 출력
        System.out.println("덧셈할 문자열을 입력해 주세요.");

        // 2. 입력 예시 한줄 받기
        String input = Console.readLine();

        // result = 0 메서드 호출
        int result = StringCalculator.add(input);

        // 4. 결과 출력 형식
        System.out.println("결과 : " + result);
    }
}
