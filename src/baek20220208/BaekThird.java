package baek20220208;
/*
* 백준 알고리즘 3046번- R2(브론즈5)*/
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class BaekThird {

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        //R1
        int r1 = Integer.parseInt(st.nextToken());

        //평균 - S
        int s = Integer.parseInt(st.nextToken());

        //S = (R1+R2)/2 => R2 = 2S - R1
        System.out.println(2*s -r1);

    }
}
