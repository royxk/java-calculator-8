package calculator;

import java.util.regex.Pattern;

public class StringCalculator {
    public static int add(String input) {
        // 1. 공백 입력은 0
        if (input == null || input.isBlank()) {
            return 0;
        }

        // 콘솔에서 입력한 "\n" 리터럴을 실제 개행으로 치환
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
        return s.replace("\\n", "\n");
    }

    private static Parsed extractDelimiterAndBody(String input) {
        final String defaultDelimiterRegex = "[,:]";
        if (!input.startsWith("//")) {
            return new Parsed(defaultDelimiterRegex, input);
        }

        int nl = input.indexOf('\n');
        if (nl < 0 || nl == 2) {
            // // 뒤에 구분자 없이 곧바로 개행이거나 개행 자체가 없음
            throw new IllegalArgumentException("Invalid custom delimiter header");
        }

        String customDelimiter = input.substring(2, nl);
        String body = input.substring(nl + 1);

        // 정규식 특수문자 안전 처리
        String safeRegex = Pattern.quote(customDelimiter);

        return new Parsed(safeRegex, body);
    }

    private static String[] splitTokens(String input, String delimiterRegex) {
        return input.split(delimiterRegex);
    }

    private static final class Parsed {
        final String delimiterRegex;
        final String body;

        Parsed(String delimiterRegex, String body) {
            this.delimiterRegex = delimiterRegex;
            this.body = body;
        }
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
            validateTokenNumeric(t);

            sum += n;

        }

        return sum;
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
