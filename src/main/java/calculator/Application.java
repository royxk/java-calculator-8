package calculator;

import camp.nextstep.edu.missionutils.Console;

public class Application {
    public static void main(String[] args) {
        // TODO: 프로그램 구현

        // 1. 사용자에게 입력 안내 출력
        System.out.println("덧셈할 문자열을 입력해 주세요.");

        // 2. 입력 예시 한줄 받기
        String input = Console.readLine();

        try {
            int result = StringCalculator.add(input);
            System.out.println("결과 : " + result);
        } catch (IllegalArgumentException e) {
            // 요구사항: 잘못된 값이면 종료 (메시지는 선택)
            System.out.println(e.getMessage()); // 필요 없으면 이 줄 삭제해도 됨
            // 그냥 return 해서 정상 종료 (exit code 0). System.exit()는 금지!

        } catch (Exception e) {
            // 예기치 못한 오류도 빌드 실패 막기 위해 메시지 출력 후 종료
            System.out.println("Unexpected error: " + e.getMessage());

        }
    }
}
