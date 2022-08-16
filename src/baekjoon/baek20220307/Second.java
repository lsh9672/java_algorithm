package baekjoon.baek20220307;

/*
* 백준- 1120번(자바 구현연습, 문자열)*/
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class Second {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        String a = st.nextToken();
        String b = st.nextToken();


        int min = 51;
        for(int i = 0; i<= b.length()-a.length(); i++){
            String temp = b.substring(i,i+a.length());
            int tempCount = 0;
            for(int j = 0; j<a.length(); j++){
                if(temp.charAt(j) != a.charAt(j)){
                    tempCount+=1;
                }
            }

            if(min > tempCount){
                min = tempCount;
            }
        }

        System.out.println(min);

    }
}
