package baek20220307;

/*
* 백준 - 1417번(국회의원 선거, 실버5, 구현연습)*/
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;


public class First {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int testCase = Integer.parseInt(br.readLine());



        int first = Integer.parseInt(br.readLine());

        int [] candidate = new int[testCase-1];

        for(int i = 0; i < testCase-1; i++){
            candidate[i] = Integer.parseInt(br.readLine());
        }

        if(testCase == 1){
            System.out.println(0);
            return;
        }

        int count = 0;

        while(true){
            Arrays.sort(candidate);
            if(first > candidate[testCase-2]) break;

            candidate[testCase-2] -= 1;
            first++;
            count++;
        }

        System.out.println(count);

    }
}
