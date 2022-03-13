package baek20220308;
/*
* 백준 1436번(실버5,)*/
import java.io.BufferedReader;
import java.io.InputStreamReader;


public class Second {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int count = 1;

        int num = 666;

        while(count != n){
            num++;
            if(String.valueOf(num).contains("666")){
                count++;
            }

        }
        System.out.println(num);

    }
}
