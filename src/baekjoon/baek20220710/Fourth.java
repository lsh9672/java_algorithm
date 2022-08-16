package baekjoon.baek20220710;

/**
 * 백준 2660번 (그래프, 골드5)
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class Fourth {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        StringTokenizer st = null;

        StringBuilder result1 = new StringBuilder();
        StringBuilder result2 = new StringBuilder();

        Integer[][] graph = new Integer[n+1][n+1];
        for(int i = 1; i <= n; i++){
            for(int j = 1; j <= n; j++){
                if(i == j){
                    graph[i][j] = 0;
                }
                else{
                    graph[i][j] = 1000;
                }
            }
        }

        while(true){
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            if(a == -1 && b == -1) break;

            graph[a][b] = graph[b][a] = 1;

        }

        //플로이드 워셜
        for(int k = 1; k < n+1; k++){
            for(int i = 1; i < n+1; i++){
                for(int j = 1; j < n+1; j++){
                    if(graph[i][j] > graph[i][k] + graph[k][j]){
                        graph[i][j] = graph[i][k] + graph[k][j];
                    }
                }
            }
        }

        Integer[] scoreArray = new Integer[n+1];

        int minValue = 100000;

        for(int i = 1; i <= n; i++){
            int temp = 0;
            for (int j = 1; j <= n; j++){
                if(graph[i][j] < 1000){
                    temp = Math.max(temp,graph[i][j]);
                }
            }

            scoreArray[i] = temp;

            //회장 후보가 되기 위해 가장 작은 점수
            minValue = Math.min(scoreArray[i],minValue);
        }

        result1.append(minValue).append(" ");

        int count = 0;
        for (int i = 1; i <= n; i++){
            if(scoreArray[i] == minValue){
                count++;
                result2.append(i).append(" ");

            }
        }
        result1.append(count);

        result2.setLength(result2.length()-1);

        System.out.println(result1);
        System.out.println(result2);
    }
}
