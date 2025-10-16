package calculator;

public class StringCalculator {
    public static int add(String input){
        // 1. null, 빈 문자열, 공백만 있을 경우 -> 0 반환
        if (input == null || input.length() == 0){
            return 0;
        }

        String numbers = input; // 실제 숫자 부분만
        String delimiter = "[,:]";

        // 2. 커스텀 구분자로 여부 확인
        if(input.startsWith("//")){
            int newLineIndex = input.indexOf("\n"); // \n 위치 찾기
            delimiter = input.substring(2, newLineIndex); // //와 /n 사이 문자 추출
            numbers = input.substring(newLineIndex + 1);

            // 정규식 특수문자를 구분자로 사용할 떄 안전하게 정리.
            delimiter = java.util.regex.Pattern.quote(delimiter);
        }

        //3. 분리
        String[] tokens = numbers.split(delimiter);

        //4. 합산
        int sum = 0;
        for (String token : tokens){
            String t = token.trim();
            sum += Integer.parseInt(t);
        }

        return sum;

    }
}
