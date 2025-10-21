package calculator;

import camp.nextstep.edu.missionutils.Console;

public class Application {
    public static void main(String[] args) {
        // TODO: 프로그램 구현
        System.out.println("덧셈할 문자열을 입력해 주세요.");

        // 입력 문자열 가져오기
        String input = Console.readLine();


        try {
            int result = StringCalculator.add(input); // 예외 전파
            System.out.println("결과 : " + result); // 결과 출력
        } finally {
            // 어떤 경우든 입력 스트림 닫아서 다음 테스트로 새지 않게
            Console.close();
        }

    }
}
