package baekjoon.baek20221006;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * @author : sh Lee
 * @date : 22. 10. 6.
 */
public class BOJ9205_맥주_마시면서_걸어가기 {

    //맨해튼 거리.
    static int manHattanDis(Location startLoc, Location endLoc){

        return Math.abs(startLoc.x - endLoc.x) + Math.abs(startLoc.y - endLoc.y);
    }

    static String bfs(int n, Location startNode, Location endNode, Location[] store){

        Queue<Location> needVisited = new LinkedList<>();
        needVisited.add(startNode);

        boolean[] visited = new boolean[n+1];

        while(!needVisited.isEmpty()){

            Location currentNode = needVisited.poll();

            //목적지와의 거리가 1000이하이면 happy리턴
            if(manHattanDis(currentNode,endNode) <= 1000){
                return "happy";
            }

            for(int i = 1; i <= n; i++){
                if(!visited[i] && manHattanDis(currentNode, store[i]) <= 1000){
                    visited[i] = true;
                    needVisited.add(store[i]);

                }
            }
        }

        return "sad";
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        int t = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();

        for(int testCase = 0; testCase < t; testCase++){
            int n = Integer.parseInt(br.readLine());

            Location[] store = new Location[n+1];
            Location startNode = null;
            Location endNode = null;
            for(int i = 0; i < n+2; i++){
                st = new StringTokenizer(br.readLine());

                if(i == 0){
                    startNode = new Location(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
                }

                else if(i == n+1){
                    endNode = new Location(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
                }
                else{
                    store[i] = new Location(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
                }
            }

            //바로 페스티벌로 갈수 있는지 확인. - 1000이내랑
            if(manHattanDis(startNode,endNode) <= 1000){
                sb.append("happy").append("\n");
            }
            else{
                sb.append(bfs(n,startNode, endNode, store)).append("\n");
            }
        }
        System.out.println(sb);

    }

    static class Location {
        int x, y;

        Location(int x, int y){
            this.x = x;
            this.y = y;
        }
    }
}
