package baek20220216;
/*
* 백준 알파벳 개수 - 10808번 구현연습*/
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class First {

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String inputAlpha = br.readLine();

        //알파벳 배열 만들기 - 26개, 소문자의 경우 97번부터 122
        int[] alphaList = new int[26];

        //카운트를 위해 초기값을 0으로 초기화
        Arrays.fill(alphaList,0);

        for(int i = 0;i < inputAlpha.length();i++){
            int temp = (byte)inputAlpha.charAt(i)-97;
            alphaList[temp] += 1;
        }

        for(int j = 0; j < alphaList.length;j++){
            System.out.print(alphaList[j]+" ");
        }

    }
}
