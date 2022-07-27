package baek20220727;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Third {

    static int N;
    static int L;
    static int R;
    //상,하,좌,우
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};

    static boolean[][] visited;

    //그래프 정의
    static int[][] graph;

    //국경 개방시킴 - 국경이 개방된 위치들 리스트로 반환.
    static List<Location> bfs(int startX, int startY){
        List<Location> openLoc = new ArrayList<>();

        Queue<Location> needVisited = new ArrayDeque<>();
        needVisited.add(new Location(startX,startY));

        visited[startX][startY] =true;

        while(!needVisited.isEmpty()){

            Location currentLoc = needVisited.poll();

            openLoc.add(currentLoc);

            for(int i = 0; i < 4; i++){
                int nextX = currentLoc.x + dx[i];
                int nextY = currentLoc.y + dy[i];

                if(nextX >= 0 && nextX < N && nextY >= 0 && nextY < N && visited[nextX][nextY] == false){

                    //국경을 개방하는 조건에 맞는 지 확인.
                    if(Math.abs(graph[currentLoc.x][currentLoc.y] - graph[nextX][nextY]) >= L && Math.abs(graph[currentLoc.x][currentLoc.y] - graph[nextX][nextY]) <= R){
                        visited[nextX][nextY] = true;
                        needVisited.add(new Location(nextX,nextY));

                    }
                }
            }
        }

        return openLoc;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        graph  = new int[N][N];

        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++){
                graph[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        //일수 세기
        int count = 0;
        while (true) {
            visited = new boolean[N][N];

            List<Location> openLoc;

            //인구이동이 일어났는지 유무 확인
            boolean flag = false;
            for(int i = 0; i < N; i++){
                for(int j = 0; j < N; j++){
                    //탐색하지 않은(국경을 개방하지 않은 곳이라면 bfs탐색을 돌면서 국경을 개방함.)
                    if(visited[i][j] == false){
                        openLoc = bfs(i,j);

                        //이동후 인구수를 위해 총 합 구하기.
                        int total = 0;

                        //국경을 여는 나라는 두개이상이여야 됨.
                        if(openLoc.size() >= 2){
                            flag = true;
                            //총합구하기
                            for (Location location : openLoc) {
                                total += graph[location.x][location.y];
                            }

                            //저장할 인구
                            total = total / openLoc.size();

                            //인구이동
                            for (Location location : openLoc) {
                                graph[location.x][location.y] = total;
                            }
                        }


                    }
                }
            }
            if(flag == false){
                break;
            }
            else{
                count++;
            }

        }

        System.out.println(count);


    }

    static class Location{
        int x;
        int y;

        Location(int x, int y){
            this.x = x;
            this.y = y;
        }
    }
}
