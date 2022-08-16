package baekjoon.baek20220223;

/*
* 백준 - 1032 명령프롬프트(자바 구현연습)*/
import java.io.BufferedReader;
import java.io.InputStreamReader;


public class Second {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        //출력할 문자
        StringBuffer sb = new StringBuffer();

        int testCase = Integer.parseInt(br.readLine());
        String[] command = new String[testCase];
        for(int i = 0; i <testCase; i++){
            command[i] = br.readLine();

        }

        for(int j = 0; j< command[0].length(); j++){
            //기준값
            char temp = command[0].charAt(j);
            boolean check = true;
            for(int k = 1; k < command.length; k++){
                if(temp == command[k].charAt(j)){
                    continue;
                }
                else{
                    check = false;
                    sb.append("?");
                    break;
                }
            }
            if(check == true){
                sb.append(temp);
            }
        }
        System.out.println(sb);
    }
}
