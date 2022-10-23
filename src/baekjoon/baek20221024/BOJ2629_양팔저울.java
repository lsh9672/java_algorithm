package baekjoon.baek20221024;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * @author : sh Lee
 * @date : 22. 10. 24.
 */
public class BOJ2629_양팔저울 {

    static boolean[][] dp;
    static int[] chuArray;

    static void dfs(int idx, int weight){

        //이미 방문했던 무게라면 패스(무게에 선택한 추까지 같다면 패스)
        if(dp[idx][weight]) return;

        //위에서 return 되지 않았다면, 해당 무게 체크
        dp[idx][weight] = true;

        //만약 마지막 추까지 다 탐색했다면 종료
        if(idx == chuArray.length) return;


        //한쪽에 추를 추가하는 경우(구슬의 반대쪽) - 이쪽의 무게가 곧 weight이자 측정가능한 무게
        dfs(idx+1,weight + chuArray[idx]);

        //해당 추를 선택하지 않는 경우
        dfs(idx+1,weight);

        //구슬쪽에 추를 추가하는 경우(총 무게에서 추 무게만큼 빼야됨.)
        dfs(idx+1,Math.abs(weight - chuArray[idx]));

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        int chuNum = Integer.parseInt(br.readLine());

        chuArray = new int[chuNum];

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < chuNum; i++){
            chuArray[i] = Integer.parseInt(st.nextToken());
        }

        //측정 가능한 무게 체크 - 추만 사용해서 측정함.
        dp = new boolean[chuNum+1][15001]; //추의 최대 무게 30*500

        dfs(0,0);

        int beadNum = Integer.parseInt(br.readLine());


        StringBuilder result = new StringBuilder();
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < beadNum; i++){
            int beadWeight = Integer.parseInt(st.nextToken());


            //해당 무게가 15000을 넘어가면 측정불가(추는 최대 15000까지 가능)
            if(beadWeight > 15000){
                result.append("N").append(" ");
            }
            //15000을 넘어가지 않았다면, 측정가능한 무게 인지 확인.
            else{
                //측정가능한경우
                if(dp[chuNum][beadWeight]){
                    result.append("Y").append(" ");
                }
                //측정 불가능 한 경우.
                else{
                    result.append("N").append(" ");
                }
            }
        }
        for(boolean[] temp : dp){
            System.out.println(Arrays.toString(temp));
        }
        System.out.println(result);


    }
}
