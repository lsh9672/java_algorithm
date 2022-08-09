package baek20220805;

/**
 * 21608번 상어 초등학교
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;


public class First {

    static int N;
    static int[][] map;
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};
    static List<Node> sortList;
    static int result;
    // 각 학생별 좋아하는 사람.
    static Set<Integer>[] bestList;

    static int[] score = {0,1,10,100,1000};


    //조건에 맞게 객체 업데이트 해서 리스트에 추가.
    static void addList(Node node, Set<Integer> bestStudent) {


        // 주위의 빈칸과 좋아하는 학생의 수를 계산
        for(int i = 0; i< 4; i++) {
            int nextX = node.x + dx[i];
            int nextY = node.y + dy[i];

            if(nextX >= 0 && nextX < N && nextY >= 0 && nextY < N) {

                //빈칸
                if(map[nextX][nextY] == 0) {
                    node.emptyCount++;
                }
                // 좋아하는 사람의 수
                if(bestStudent.contains(map[nextX][nextY])) {
                    node.bestCount++;
                }
            }
        }
        sortList.add(node);
    }

    static void satisfyScore() {

        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                //System.out.println(map[i][j]+ " " + bestList[map[i][j]].toString());
                // 좋아하는 학생의 수 누적
                int temp = 0;
                for(int d = 0; d < 4; d++) {
                    int nextX = i + dx[d];
                    int nextY = j + dy[d];

                    if(nextX >= 0 && nextX < N && nextY >= 0 && nextY < N) {
                        if(bestList[map[i][j]].contains(map[nextX][nextY])) {
                            temp++;
                        }
                    }
                }
                //System.out.println(map[i][j] + "--" + temp);
                result += score[temp];

            }
        }

    }



    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        map = new int[N][N]; //교실 배치 초기화

        bestList = new Set[N*N+1];

        StringTokenizer st = null;
        for(int i = 0; i < N*N; i++){
            st = new StringTokenizer(br.readLine());
            int studentNum = Integer.parseInt(st.nextToken());
            //좋아하는 학생의 번호
            Set<Integer> bestStudent = new HashSet<>();
            for(int j = 0; j < 4; j++){
                bestStudent.add(Integer.parseInt(st.nextToken()));
            }

            //각 학생별로 좋아하는 사람을 배열에 저장 해둠
            bestList[studentNum] = bestStudent;
            //System.out.println(bestList[4].toString());

            sortList = new ArrayList<>();
            //교실 배치 돌면서 빈칸 찾고, 조건에 맞게 객체 채움
            for(int x = 0; x < N; x++){
                for(int y = 0; y < N; y++){
                    if(map[x][y] == 0){
                        addList(new Node(x,y,0,0),bestStudent);
                    }
                }
            }

            //조건순서대로 리스트 정렬
            sortList.sort((node1, node2) -> {

                //좋아하는 학생수가 같다면.
                if(node1.bestCount == node2.bestCount) {

                    // 비어있는 칸수가 같다면
                    if(node1.emptyCount == node2.emptyCount) {

                        // 행번호가 같다면
                        if(node1.x == node2.x) {
                            //열번호 순으로
                            return node1.y - node2.y;
                        }
                        //행번호가 작은 쪽으로
                        return node1.x - node2.x;
                    }

                    //비어있는 칸수가 많은 쪽으로
                    return node2.emptyCount - node1.emptyCount;
                }

                // 종아하는 학생수가 많은 쪽으로
                return node2.bestCount - node1.bestCount;
            });


            //정렬했을때, 첫번째 값에 위치에 넣기
            map[sortList.get(0).x][sortList.get(0).y] = studentNum;
        }

        //만족도 구하기
        result =0;

        satisfyScore();
        System.out.println(result);



    }

    static class Node{

        int x;
        int y;

        //인접칸의 좋아하는 학생수
        int bestCount;

        //비어있는 칸의 수
        int emptyCount;

        Node(int x, int y, int bestCount, int emptyCount){
            this.x = x;
            this.y = y;
            this.bestCount = bestCount;
            this.emptyCount = emptyCount;
        }
    }


}