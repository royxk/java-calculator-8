package calculator;

public class StringCalculator {
    public static int add(String input){
        // 1. null, 빈 문자열, 공백만 있을 경우 -> 0 반환
        if (input == null || input.length() == 0){
            return 0;
        }

        // 2. 기본 구분자 (, or :)로 분리
        String[] tokens = input.split("[,:]");

        //3. 정수로 변환해 합산 (검증은 다음에서 강화)
        int sum = 0;
        for (String token : tokens){
            String t = token.trim();
            sum += Integer.parseInt(t);
        }

        return sum;

    }
}
