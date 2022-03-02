package baek20220302;
/*
* 백준 3059번(자바 구현연습)*/
import java.io.BufferedReader;
import java.io.InputStreamReader;


public class First {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));


        int testCase = Integer.parseInt(br.readLine());

        int[] result = new int[testCase];

        for(int i = 0; i <testCase; i++){
            String temp = br.readLine().strip();
            //안나온 알파벳 체크 - 디폴트는 false
            boolean[] check = new boolean[26];


            for(int j = 0; j< temp.length(); j++){
                check[(int)temp.charAt(j) - 65] = true;
            }

            for(int k = 0; k<26; k++){
                if(check[k] == false){
                    result[i] += k+65;
                }
            }

        }

        for (int m : result) {
            System.out.println(m);
        }

    }
}
