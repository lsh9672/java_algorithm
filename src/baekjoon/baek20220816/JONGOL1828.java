package baekjoon.baek20220816;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


public class JONGOL1828 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        int N = Integer.parseInt(br.readLine());


        //냉장고 수 - 초기에 1개는 필요하므로 1로 잡음
        int result = 1;

        Temperate[] temp = new Temperate[N];

        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            temp[i] = new Temperate(x,y);

        }

        Arrays.sort(temp,(o1, o2)->{
            return o1.x == o2.x? o1.y - o2.y : o1.x- o2.x;
        });

        //최고온도로 셋팅
        int currentMax = temp[0].y;

        //반복문 돌면서 현재 최대값이랑 각 값들의 최소 값이랑 비교
        for(int i = 1; i < N; i++){

            //현 냉장고의 최대온도보다 새로운 물질의 최소온도가 높다면 새로 냉장고를 만들어야 됨.
            if(currentMax < temp[i].x){
                currentMax = temp[i].y;
                result++;
            }
        }

        System.out.println(result);
    }

    static class Temperate{
        int x;
        int y;

        Temperate(int x, int y){
            this.x = x;
            this.y = y;
        }
    }

}
