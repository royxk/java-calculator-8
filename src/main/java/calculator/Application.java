package calculator;

import camp.nextstep.edu.missionutils.Console;

public class Application {
    public static void main(String[] args) {
        // TODO: 프로그램 구현

        // 1. 사용자에게 입력 안내 출력
        System.out.println("덧셈할 문자열을 입력해 주세요.");

        // 2. 입력 예시 한줄 받기
        String input = Console.readLine();

        // 3. (로직 구현 예정) -> 입출력 확인을 위해 초기 0 으로 셋팅
        int result = 0;

        // 4. 결과 출력 형식
        System.out.println("결과 : " + result);
    }
}
