package programmers.prog20220920;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class 카카오공채2020_기둥과_보_설치 {

    static int N;

    static boolean[][] beams;
    static boolean[][] pillars;

    // 해당 좌표가 범위를 벗어나는지 확인.
    static boolean rangeCheck(int currentX, int currentY){
        if(currentX >= 0 && currentX <= N && currentY >= 0 && currentY <=N){
            return true;
        }

        return false;
    }


    //삭제하는 경우 - 일단 삭제하고 남은 것들이 조건에 맞는 지 확인. 0 : 기둥, 1: 보
    static void removeBeamCheck(int startX,int startY, int type) {


        /*삭제*/
        //기둥
        if(type == 0) {
            pillars[startX][startY] = false;
        }
        //보
        else {

            beams[startX][startY] = false;
        }

        boolean flag = false;

        //comment : 원래 해당 건축물에 영향받는 부분만 체크하려고 했는데, 머리가 잘 안돌아서 그냥 다 확인.
        loop:
        for(int i = 0; i <= N; i++){
            for(int j = 0; j <= N; j++){

                //기둥일때
                if(pillars[i][j]){

                    //삭제후 기둥이 남아있는 부분을 탐색했는데, 해당부분에 놓을수 없다면 종료
                    if(!setPillarCheck(i,j)){
                        flag = true;
                        break loop;
                    }
                }

                //보 일때
                if(beams[i][j]){

                    if(!setBeamCheck(i,j)){
                        flag = true;
                        break loop;
                    }
                }
            }
        }

        //중간에 break가 되었다면, 해당 위치를 삭제하면 안되기 떄문에 원복
        if(flag){
            if(type == 0) {
                pillars[startX][startY] = true;
            }
            //보
            else {

                beams[startX][startY] = true;
            }
        }

    }


    //보는 한쪽 끝 부분이 기둥 위에 있거나, 또는 양쪽 끝 부분이 다른 보와 동시에 연결되어 있어야 합니다.
    //해당위치에 보를 둘 수 있는 지 확인
    static boolean setBeamCheck(int startX, int startY) {

        // 지도 범위를 벗어나는지 확인. -- 이러한 경우는 주어지지 않음.
//    	if(endX < 0 || endX >= N+1 || endY < 0 || endY >= N+1) {
//    		return false;
//    	}
//
        //1. 한쪽 끝부분이 기둥 위 인지
        if((rangeCheck(startX+1,startY) && pillars[startX+1][startY]) || (rangeCheck(startX+1,startY+1) && pillars[startX+1][startY+1])) {
            return true;
        }

        //2. 양쪽 끝부분이 다른 보와 동시에 연결되어 있는지.
        else if(rangeCheck(startX,startY-1) && beams[startX][startY-1] && rangeCheck(startX,startY+1) && beams[startX][startY+1]) {
            return true;
        }

        return false;

    }
    //기둥은 바닥 위에 있거나 보의 한쪽 끝 부분 위에 있거나, 또는 다른 기둥 위에 있어야 합니다.
    // 해당위치에 기둥을 둘 수 있는지 확인
    static boolean setPillarCheck(int startX, int startY) {



        //1. 바닥위에 있는지 확인
        if(startX == N) {
            return true;
        }

        //2. 보의 한쪽 끝 부분 위인지 확인
        if((rangeCheck(startX,startY) && beams[startX][startY]) || (rangeCheck(startX,startY-1) && beams[startX][startY-1])) {
            return true;
        }

        //3. 다른 기둥 위인지 확인
        if(rangeCheck(startX+1,startY) && pillars[startX+1][startY]) {

            return true;
        }

        return false;
    }


    //건축물 배치 - 1로 표시
    static void setBuilding(int[][] build_frame){

        for(int[] temp : build_frame){
            int x = N - temp[1];
            int y = temp[0];
            int type = temp[2]; // 방향 => 기둥을 위쪽방향, 보는 오른쪽 방향
            int setType = temp[3]; // 설치,삭제 => 1은 설치, 0은 삭제

            // 설치인 경우
            if(setType == 1){
                //기둥인 경우
                if(type == 0) {
                    //놓을수 있다면 놓기.
                    if(setPillarCheck(x,y)){
                        pillars[x][y] = true;
                    }
                }
                //보 인 경우
                else {
                    if(setBeamCheck(x,y)){

                        beams[x][y] = true;
                    }
                }
            }
            // 삭제인 경우
            else {
                removeBeamCheck(x,y, type);
            }

        }
    }

    public int[][] solution(int n, int[][] build_frame) {

        N = n;

//        maps = new int[N+1][N+1];

        beams = new boolean[N+1][N+1];
        pillars = new boolean[N+1][N+1];

        setBuilding(build_frame);

        List<int[]> result = new ArrayList<>();

        for(int i = 0; i <= N; i++) {
            for(int j = 0; j <= N; j++) {

                //보인경우
                if(beams[i][j]) {
                    result.add(new int[] {j,N-i,1});
                }

                //기둥인 경우
                if(pillars[i][j]) {
                    result.add(new int[] {j,N-i,0});
                }
            }
        }

        result.sort((o1,o2)->{

            if(o1[0]==(o2[0])) {

                if(o1[1]==(o2[1])) {
                    return o1[2] - o2[2];
                }

                return o1[1] - o2[1];
            }

            return o1[0] - o2[0];
        });


        int[][] answer =  new int[result.size()][3];
        for(int i = 0; i < result.size(); i++){

            answer[i][0] = result.get(i)[0];
            answer[i][1] = result.get(i)[1];
            answer[i][2] = result.get(i)[2];
        }

        return answer;
    }

    public static void main(String[] args) {
        카카오공채2020_기둥과_보_설치 tt = new 카카오공채2020_기둥과_보_설치();

        int n1 = 5;
        int[][] build_frame1 = {{1,0,0,1},{1,1,1,1},{2,1,0,1},{2,2,1,1},{5,0,0,1},{5,1,0,1},{4,2,1,1},{3,2,1,1}};

        int[][] sol = tt.solution(n1,build_frame1);
        for(int[] s : sol){
            System.out.println(Arrays.toString(s));
        }

        int n2 = 5;
        int[][] build_frame2 = {{0,0,0,1},{2,0,0,1},{4,0,0,1},{0,1,1,1},{1,1,1,1},{2,1,1,1},{3,1,1,1},{2,0,0,0},{1,1,1,0},{2,2,0,1}};
        int[][] sol2 = tt.solution(n2,build_frame2);
        for(int[] s : sol2){
            System.out.println(Arrays.toString(s));
        }
    }
}