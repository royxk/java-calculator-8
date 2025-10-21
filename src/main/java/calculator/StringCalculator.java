package calculator;

import java.util.regex.Pattern;

public class StringCalculator {

    // Add function.
    public static int add(String input) {

        // 1. 공백 입력은 0
        if (input == null || input.isBlank()) {
            return 0;
        }

        // 콘솔에서 입력한 "\n" 리터럴을 실제 개행으로 치환 -> (\n을 입력하면 실제로는 \\n 으로 드러오기 때문)
        input = normalizeInput(input);

        // 2. 구분자/본문 분리
        Parsed parsed = extractDelimiterAndBody(input);

        // 3) 토큰 분리
        String[] tokens = splitTokens(parsed.body, parsed.delimiterRegex);

        // 4) 검증 + 변환 + 합산
        return sumTokens(tokens);

    }

    // -----------------HELPERS-------------------

    // 콘솔에서 입력한 "\n" 리터럴을 실제 개행으로 치환
    private static String normalizeInput(String s) {
        // 실제로 들어오는 \\n을 \n으로 변경하기, 윈도우에서는 \r\n으로 들어오기떄문에 표현을 동일하게 변경
        return s.replace("\\n", "\n").replace("\r\n", "\n");
    }

    // 커스텀 구분자 추출
    private static Parsed extractDelimiterAndBody(String input) {
        // 커스텀 구분자를 사용하지 않을떄는 기본적으로 ,; split
        final String defaultDelimiterRegex = "[,:]";

        // 시작이 // 아니면 사용하지 않을 경우 -> 커스텀 구분자 사용x
        if (!input.startsWith("//")) {
            return new Parsed(defaultDelimiterRegex, input);
        }

        // \n 위치 찾아보기
        int nl = input.indexOf('\n');

        // // 뒤에 구분자 없이 곧바로 개행이거나 개행 자체가 없음
        if (nl < 0 || nl == 2) {
            throw new IllegalArgumentException("Invalid custom delimiter header");
        }

        String customDelimiter = input.substring(2, nl); // 커스텀 구분자 추출하기
        String body = input.substring(nl + 1); // 본문 출력

        // 정규식 특수문자 안전 처리
        String safeRegex = Pattern.quote(customDelimiter);

        return new Parsed(safeRegex, body);
    }

    // 입력 문자열을 구분자를 기준으로 잘라 -> 조각 배열(String[]) 돌려주는 function.
    private static String[] splitTokens(String input, String delimiterRegex) {
        return input.split(delimiterRegex, -1);
    }

    // 각 토큰 검증 하고 변환 and 합산
    private static int sumTokens(String[] tokens){
        int sum = 0;
        for (String token : tokens){
            String t = token.trim();

            // 1) 빈 토큰 검증하기
            validateTokenNotEmpty(t);

            // 2) 정수 변환 (숫자x -> IllegalArgumentException)
            final int n;
            try{
                n = Integer.parseInt(t);
            } catch (NumberFormatException e){
                throw new IllegalArgumentException("Non-numeric token: " + t);
            }

            // 3) 음수 검증
            validateNonNegative(n);

            sum += n;
        }

        return sum;
    }

    // 구분자와 본문을 한번에 가지고있는 객체를 출력하게 하기 위해서
    private static final class Parsed {
        final String delimiterRegex;
        final String body;

        Parsed(String delimiterRegex, String body) {
            this.delimiterRegex = delimiterRegex;
            this.body = body;
        }
    }

    private static void validateTokenNumeric(String token){
        if(token.isEmpty()){
            throw new IllegalArgumentException("Empty number token");
        }
    }

    private static void validateTokenNotEmpty(String token){
        if(token.isEmpty()){
            throw new IllegalArgumentException("Empty number token");
        }
    }

    private static void validateNonNegative(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("Negative number not allowed: " + n);
        }
    }
}
