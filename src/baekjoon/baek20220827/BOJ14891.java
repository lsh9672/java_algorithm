package baekjoon.baek20220827;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ14891 {

    static int[] startLoc = new int[5]; //각 톱니바퀴의 시작 인덱스를 저장함.
    static String[] gearArray = new String[5];
    static int[] dNum = {-1,1};


    //회전 가능한 톱니바퀴 번호와 회전할 방향을 구해서 리턴
    static List<Node> rotateList(Node startNode){

        List<Node> returnList = new ArrayList<>();

        boolean[] visited = new boolean[5];
        visited[startNode.num] = true;

        Queue<Node> needVisited = new LinkedList<>();
        needVisited.add(startNode);

        while(!needVisited.isEmpty()){

            Node currentNode = needVisited.poll();
            returnList.add(currentNode);

            for(int i = 0; i < 2; i++){
                int nextNum = currentNode.num + dNum[i];

                //범위안의 톱니바퀴 번호이고 방문하지 않았다면,
                if(nextNum >= 1 && nextNum <= 4 && !visited[nextNum]){

                    char currentPole = ' ';
                    char nextPole = ' ';
                    //왼쪽에 있는 톱니바퀴라면
                    if(i == 0){

                        //현재 극정보
                        currentPole = gearArray[currentNode.num].charAt((startLoc[currentNode.num]+6)%8);
                        //인접한 톱니바퀴의 극 정보
                        nextPole = gearArray[nextNum].charAt((startLoc[nextNum]+2)%8);
                    }
                    //오른쪽에 있는 톱니바퀴라면.
                    else{
                        //현재 극정보
                        currentPole = gearArray[currentNode.num].charAt((startLoc[currentNode.num]+2)%8);
                        //인접한 톱니바퀴의 극 정보
                        nextPole = gearArray[nextNum].charAt((startLoc[nextNum]+6)%8);
                    }

                    //두개가 다르다면 회전 가능
                    if(currentPole != nextPole){
                        visited[nextNum] = true;
                        needVisited.add(new Node(nextNum,currentNode.dir * (-1)));
                    }
                }
            }

        }

        return returnList;
    }

    //회전 시킬 톱니바퀴의 번호와 회전 방향을 받아서 회전시킴
    static void gearRotate(int gearNum, int dir){

        List<Node> rotateGearList = rotateList(new Node(gearNum, dir));

        //톱나비퀴정보를 하나씩 꺼내서 회전.(회전은 인덱스만 변경.)
        for(Node tempGear : rotateGearList){
            if(tempGear.dir == -1){
                startLoc[tempGear.num]++;
                if(startLoc[tempGear.num] > 7) startLoc[tempGear.num] = 0;
            }
            else{
                startLoc[tempGear.num]--;
                if(startLoc[tempGear.num] < 0) startLoc[tempGear.num] = 7;
            }
        }

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        //각 톱니바퀴의 상태를 배열에 저장.
        for(int i = 1; i <= 4; i++){
            gearArray[i] = br.readLine();
        }

        int K = Integer.parseInt(br.readLine());

        for(int i = 0; i < K; i++){
            st = new StringTokenizer(br.readLine());
            int gearNum = Integer.parseInt(st.nextToken());
            int dir = Integer.parseInt(st.nextToken());

            gearRotate(gearNum,dir);
        }

        //톱니바퀴의 12시 방향의 값을 읽어서 점수화
        int result = 0;
        for(int i = 1; i <= 4; i++){
            int tempValue = Integer.parseInt(""+gearArray[i].charAt(startLoc[i]));

            if(i == 1){
                result += tempValue;
            }
            else if(i == 2){
                result += tempValue * 2;
            }
            else if(i == 3){
                result += tempValue * 4;
            }
            else{
                result += tempValue * 8;
            }
        }

        System.out.println(result);
    }

    static class Node{
        int num, dir;

        Node(int num, int dir){
            this.num = num;
            this.dir = dir;
        }
    }
}
