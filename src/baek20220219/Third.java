package baek20220219;
/*
* 백준 1789번 수들의 합(실버5, 구현)*/
import java.io.BufferedReader;
import java.io.InputStreamReader;


public class Third {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        Long n = Long.parseLong(br.readLine());

        int i =1;
        int count = 0;
        long sum = 0;

        while(true){
            if(sum > n) break;
            sum+=i;
            count++;
            i++;

        }
        System.out.println(count-1);
    }
}
