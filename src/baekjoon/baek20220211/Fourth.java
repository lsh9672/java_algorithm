package baekjoon.baek20220211;

/*
* 백준 일곱 난쟁이 2309 (브론즈2,구현연습)*/
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;


public class Fourth {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        //9명의 총 키 - 100을 빼서 남은 것으로 연산함
        int total = 0;

        //난쟁이들의 키
        int [] height = new int[9];
        for(int i = 0; i<9 ; i++){
            int temp = Integer.parseInt(br.readLine());
            total += temp;
            height[i] = temp;
        }
        //전체키 - 100
        total = total - 100;

        loop:
        for(int i =0; i<8;i++){
            for(int j = i+1;j<9;j++){
                if(total == height[i]+height[j]){
                    height[i] = 0;
                    height[j] = 0;

                    break loop;
                }
            }
        }

        //오름차순 정렬
        Arrays.sort(height);
        for(int i = 0; i<9;i++){
            if(height[i] != 0) System.out.println(height[i]);

        }
    }
}
