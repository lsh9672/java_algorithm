package programmers.prog20220912;


import java.util.Locale;
import java.util.regex.Pattern;

class 카카오공채2021_신규_아이디_추천 {
    public String solution(String new_id) {
        String answer = "";

        //1단계 - 대문자를 소문자로 치환
        answer = new_id.toLowerCase();

        //2단계 - 알파벳 소문자, 숫자, 빼기(-), 밑줄(_), 마침표(.)를 제외한 모든 문자를 제거
        answer = answer.replaceAll("[^a-z0-9-_.]","");

        //3단계 - 마침표(.)가 2번 이상 연속된 부분을 하나의 마침표(.)로 치환
        answer = answer.replaceAll("[.][.]+",".");

        //4단계 - 마침표(.)가 처음이나 끝에 위치한다면 제거
        answer = answer.replaceAll("^[.]|[.]$","");

        //5단계 - 빈 문자열이라면, new_id에 "a"를 대입
        if(answer.equals("")) answer = "a";

        //6단계 - 길이가 16자 이상이면, new_id의 첫 15개의 문자를 제외한 나머지 문자들을 모두 제거, 만약 제거 후 마침표(.)가 new_id의 끝에 위치한다면 끝에 위치한 마침표(.) 문자를 제거
        if(answer.length() >= 16) {
            answer = answer.substring(0, 15);
            answer = answer.replaceAll("[.]$","");
        }

        //7단계 - 길이가 2자 이하라면, new_id의 마지막 문자를 new_id의 길이가 3이 될 때까지 반복해서 끝에 붙입니다.
        while(answer.length() <= 2){
            answer += answer.charAt(answer.length()-1);
        }

        return answer;
    }

    public static void main(String[] args) {
        카카오공채2021_신규_아이디_추천 tt = new 카카오공채2021_신규_아이디_추천();
        String new_id1 = "...!@BaT#*..y.abcdefghijklm";
        System.out.println(tt.solution(new_id1));

        String new_id2 = "z-+.^.";
        System.out.println(tt.solution(new_id2));

        String new_id3 = "=.=";
        System.out.println(tt.solution(new_id3));

        String new_id4 = "123_.def";
        System.out.println(tt.solution(new_id4));

        String new_id5 = "abcdefghijklmn.p";
        System.out.println(tt.solution(new_id5));


    }
}