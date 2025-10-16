package calculator;

import java.util.regex.Pattern;

public class StringCalculator {
    public static int add(String input){
        // 1. null, 빈 문자열, 공백만 있을 경우 -> 0 반환
        if (input == null || input.length() == 0){
            return 0;
        }

        // 콘솔에서 입력한 "\n" 리터럴을 실제 개행으로 치환
        input = input.replace("\\n", "\n");

        String numbers = input;
        String delimiter = "[,:]";

        // 2. 커스텀 구분자로 여부 확인
        if(input.startsWith("//")){
            int newLineIndex = input.indexOf("\n"); // \n 위치 찾기
            if (newLineIndex < 0 || newLineIndex == 2) {
                throw new IllegalArgumentException("Invalid custom delimiter header");
            }
            String custom = input.substring(2, newLineIndex); // //와 /n 사이 문자 추출
            numbers = input.substring(newLineIndex + 1);
            delimiter = Pattern.quote(custom);


        }

        //3. 분리
        String[] tokens = numbers.split(delimiter);

        //4. 각 토큰 정수 여부 검사 + 변환 + 합산
        int sum = 0;
        for (String token : tokens){
            String t = token.trim();

            // check1 빈 토큰 검사
            if (t.isEmpty()){
                throw new IllegalArgumentException("Empty number token is not allowed.");
            }

            // check2 정수 형식 검사
            // [0-9]+ → 숫자 1개 이상인지 확인
            if (!t.matches("-?[0-9]+")) {  // (음수 검사도 포함)
                throw new IllegalArgumentException("Non-numeric token: " + t);
            }

            // check3 정수 변환
            int num = Integer.parseInt(t);

            // check4 음수 검사
            if (num < 0) {
                throw new IllegalArgumentException("Negative number not allowed: " + num);
            }

            sum += num;
        }

        return sum;

    }
}
