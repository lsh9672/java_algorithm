package baek20220731;

/**
 * 백준 9328번 (스터디 대비)
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Third {

    //높이
    static int h;
    //넓이
    static int w;
    //건물 지도
    static String[][] map;
    //보유 키 -> set으로 수정필요
//    static String keys;
    static Set<String> keys;
    //문서 개수
    static int documentCount;
    //방향 - 상하좌우
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};

    //시작 시점이 빈칸이 아니라 문, 열쇠, 문서일때 처리가 필요함
   static boolean bfs(Location startNode){
//       System.out.println("key : " + keys);
       boolean[][] visited = new boolean[h][w];

       //키를 획득했는지 확인.
       boolean updateKey = false;

       Queue<Location> needVisited = new ArrayDeque<>();

        //서류일때
       if(map[startNode.x][startNode.y].equals("$")){
            map[startNode.x][startNode.y] = ".";
            documentCount++;
            needVisited.add(new Location(startNode.x,startNode.y));
            visited[startNode.x][startNode.y] = true;
       }
        //문일때
       else if(Character.isUpperCase(map[startNode.x][startNode.y].charAt(0))){
            if(keys.contains(map[startNode.x][startNode.y].toLowerCase())){
                map[startNode.x][startNode.y] = ".";
                needVisited.add(new Location(startNode.x,startNode.y));
                visited[startNode.x][startNode.y] = true;
            }
       }
        //열쇠
       else if(Character.isLowerCase(map[startNode.x][startNode.y].charAt(0))){
           updateKey = true;
           keys.add(map[startNode.x][startNode.y]);
           map[startNode.x][startNode.y] = ".";
           needVisited.add(new Location(startNode.x,startNode.y));
           visited[startNode.x][startNode.y] = true;
       }
        //빈칸
        else{
            //방문처리
           needVisited.add(new Location(startNode.x,startNode.y));
           visited[startNode.x][startNode.y] = true;
        }

        //시작위치가 빈칸이 아니라면 처리가 필요함


        while(!needVisited.isEmpty()){

            Location currentLocation = needVisited.poll();

//            System.out.println(currentLocation.x + " , " + currentLocation.y);
            for(int i = 0; i < 4; i++){
                int nextX = currentLocation.x + dx[i];
                int nextY = currentLocation.y + dy[i];

                //맵을 벗어나지 않고 벽이 아니면
                if(nextX >= 0 && nextX < h && nextY >= 0 && nextY < w && visited[nextX][nextY] == false && !map[nextX][nextY].equals("*")){
                    //빈공간이라면 이동가능
                    if(map[nextX][nextY].equals(".")){
                        needVisited.add(new Location(nextX,nextY));
                        visited[nextX][nextY] = true;
                    }
                    //문서라면
                    else if(map[nextX][nextY].equals("$")){
                        map[nextX][nextY] = ".";
                        documentCount ++;
                        needVisited.add(new Location(nextX,nextY));
                        visited[nextX][nextY] = true;

                    }
                    //문이라면(대문자)
                    else if(Character.isUpperCase(map[nextX][nextY].charAt(0))){

                        //열쇠로 딸 수 있는 지 확인
//                        System.out.println(""+ currentLocation.x + " , " +currentLocation.y);
                        //딸 수 있다면 빈공간으로 만들어버림.
                        if(keys.contains(map[nextX][nextY].toLowerCase())){
                            map[nextX][nextY] = ".";
                            needVisited.add(new Location(nextX,nextY));
                            visited[nextX][nextY] = true;
                        }
                    }
                    //열쇠라면(소문자)
                    else{
                        //획득
                        needVisited.add(new Location(nextX,nextY));
                        visited[nextX][nextY] = true;
                        updateKey = true;
                        keys.add(map[nextX][nextY]);
                        map[nextX][nextY] = ".";
                    }
                }
            }

        }
        return updateKey;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        StringTokenizer st;
        for(int testCase = 0; testCase < T; testCase++){
            st = new StringTokenizer(br.readLine());
            h = Integer.parseInt(st.nextToken());
            w = Integer.parseInt(st.nextToken());

            map = new String[h][w];

            for(int i = 0; i < h; i++){
                map[i] = br.readLine().split("");
            }

            //보유하고 있는 키 저장 - contains를 이용하기 위해서 문자열로 저장함.
            String tempKeys = br.readLine();
            keys = new HashSet<>();

            if(!tempKeys.equals("0")){
                for(String temp : tempKeys.split("")){
                    keys.add(temp);
                }
            }


            //가장 자리 찾기()
            List<Location> inputLocs = new ArrayList<>();


            //왼쪽 끝과, 오른쪽 끝
            for(int i = 0; i < h; i++){
                //벽이 아닌 경우는 모두 입구의 후보가 될 수 있음.
                if(!map[i][0].equals("*")){
                    inputLocs.add(new Location(i,0));
                }
                if(!map[i][w-1].equals("*")){
                    inputLocs.add(new Location(i,w-1));
                }
            }
            //맨위와 맨 아래
            for(int i = 0; i < w; i++){
                if(!map[0][i].equals("*")){
                    inputLocs.add(new Location(0,i));
                }
                if(!map[h-1][i].equals("*")){
                    inputLocs.add(new Location(h-1,i));
                }
            }

            //찾아야 하는 문서 개수 초기화
            documentCount = 0;

            //열쇠를 한개이상 얻었는지 확인.
            boolean flag;
            while(true){

                //각 입구에서 출발해보고 열쇠를 얻었다면 모든 입구에서 다 탐색해봄
                //만약 탐색이 다 끝났는데도 열쇠를 얻지 않았다면  탐색 끝
                //열쇠를 얻었다면 열쇠 목록을 새로 얻은 열쇠로 업데이트
                flag = false;
                boolean tempFlag = false;

                for(Location temp : inputLocs){
                    //획득한 열쇠가 있는지 확인.
                    tempFlag = bfs(temp);
                    if(tempFlag){
                        flag = true;
                    }
//                    System.out.println(keys.toString());
//                    System.out.println("update : " + updateKey);
                }
                //열쇠를 한개도 못얻었다면 탐색 종료
                if(flag == false) break;
            }

            //최종적으로 수거한 문서수 출력
            System.out.println(documentCount);
        }
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
