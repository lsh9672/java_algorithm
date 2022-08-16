package baekjoon.baek20220219;

/*
* 백준 6679번 싱기한 네자리 숫자*/

public class First {

    //12진수 변환
    static String convert(int num){
        StringBuilder sb = new StringBuilder();
        while(num > 0){
            int temp = num%12;

            if(temp==10){
                sb.append("a");
            }
            else if(temp==11){
                sb.append("b");
            }
            else{
                sb.append(String.valueOf(temp));
            }
            num /= 12;

        }
        sb.reverse();

        return sb.toString();
    }

    static int totalNum(String num){
        int result = 0;

        for(int i= 0; i<num.length();i++){
            String temp = num.substring(i,i+1);
            if(temp.equals("a")){
                temp = "10";
            }
            else if(temp.equals("b")){
                temp = "11";
            }
            else if(temp.equals("c")){
                temp = "12";
            }
            else if(temp.equals("d")){
                temp = "13";
            }
            else if(temp.equals("e")){
                temp = "14";
            }
            else if(temp.equals("f")){
                temp = "15";
            }
            result += Integer.parseInt(temp);

        }
        return result;
    }
    public static void main(String[] args) {
        //1000~9999

        for(int i = 1000; i<=9999 ; i++){
            //10진수 문자열 변환
            String decNum = Integer.toString(i);
            //12진수
            String twNum = convert(i);
            //16진수
            String hexNum = Integer.toHexString(i);

            if(totalNum(decNum) == totalNum(twNum) && totalNum(twNum) == totalNum(hexNum)){
                System.out.println(i);
            }

        }
    }
}
