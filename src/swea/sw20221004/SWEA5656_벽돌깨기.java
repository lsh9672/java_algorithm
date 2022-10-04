package swea.sw20221004;


import java.io.*;
import java.util.StringTokenizer;

public class SWEA5656_벽돌깨기 {

    static int N,W,H;
    static int[][] maps;
    static int result;

    static int[][] arrayCopy(int[][] maps){

        int[][] returnMap = new int[H][W];

        for(int i = 0; i < H; i++){
            System.arraycopy(maps[i],0,returnMap[i],0,W);
        }

        return returnMap;
    }

    static void removeBrick(){

    }

    //brickCount : 현재 벽돌수
    static void recursive(int[][] map, int brickCount, int idx){

        //던질수 있는 최대 횟수에 도달했을때.
        if(idx >= 3){
            result = Math.min(result,brickCount);
        }

        for(int i = 0; i < W; i++){

        }


    }
    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("/home/leesh/lsh/my_study/java_study/algorism_java/src/swea/sw20221004/sample_input (17).txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        int T = Integer.parseInt(br.readLine());
        for(int testCase = 1; testCase <= T; testCase++){
            st = new StringTokenizer(br.readLine());

            N = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());
            H = Integer.parseInt(st.nextToken());

            maps = new int[H][W];
            for(int i = 0; i < H; i++){
                st = new StringTokenizer(br.readLine());
                for(int j = 0; j < W; j++){
                    maps[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            recursive(maps,0,0);

        }

    }

    static class Node{
        int x,y;

        Node(int x, int y){
            this.x = x;
            this.y = y;
        }
    }
}
