package swea.sw20221013;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * @author : sh Lee
 * @date : 22. 10. 13.
 */
public class SWEA8458_원점으로_집합 {

    static int N;

    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};


//    static int dfs(){
//
//    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        int T = Integer.parseInt(br.readLine());

        for(int testCase = 1; testCase <= T; testCase++){

            N = Integer.parseInt(br.readLine());
            st = new StringTokenizer(br.readLine());

            Node[] nodeArray = new Node[N];




        }
    }

    static class Node{
        int x, y;

        Node(int x, int y){
            this.x = x;
            this.y = y;
        }
    }
}
