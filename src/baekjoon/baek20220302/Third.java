package baekjoon.baek20220302;

/*
* 백준 2167번(자바 구현연습, 브론즈1)*/
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class Third {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[][] numArray = new int[n][m];

        StringTokenizer st2 = null;
        for(int i = 0; i<n; i++){
             st2 = new StringTokenizer(br.readLine());
            for(int j = 0; j < m; j++){
                numArray[i][j] = Integer.parseInt(st2.nextToken());
            }
        }

        //합을 구할 좌표
        int testCase = Integer.parseInt(br.readLine());

        StringTokenizer st3 = null;
        for(int i= 0; i<testCase; i++){
            st3 = new StringTokenizer(br.readLine());

            //좌표 (a,b) ~ (c,d) - 0,0부터 시작하기 때문에 -1함.
            int a = Integer.parseInt(st3.nextToken()) - 1;
            int b = Integer.parseInt(st3.nextToken()) - 1;
            int c = Integer.parseInt(st3.nextToken()) - 1;
            int d = Integer.parseInt(st3.nextToken()) - 1;

            int total = 0;

            for(int x = a; x <= c; x++){
                for(int y = b; y <= d; y++){
                    total+=numArray[x][y];
                }

            }
            System.out.println(total);
        }

    }
}
